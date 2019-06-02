package com.test.billing.service;

import com.test.billing.body.OrderBody;
import com.test.billing.body.OrderItemBody;
import com.test.billing.entity.*;
import com.test.billing.repository.CustomerRepository;
import com.test.billing.repository.OrderRepository;
import com.test.billing.repository.ProductRepository;
import com.test.billing.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BillingService billingService;

    @Transactional
    public OrderBody addOrder(OrderBody orderBody) throws Exception {
        List<Long> productIds = orderBody.getItems().stream().map(OrderItemBody::getProductId).collect(Collectors.toList());
        List<Product> products = productRepository.findByProductIds(productIds);
        Map<Long, Integer> quantityByProduct = orderBody.getItems().stream().collect(Collectors.toMap(OrderItemBody::getProductId, OrderItemBody::getQuantity));
        Customer customer = customerRepository.findById(orderBody.getCustomerId()).orElse(null);
        if(customer != null && products.size() > 0) {
            CustomerOrder customerOrder = new CustomerOrder();
            customerOrder.setCustomer(customer);
            customerOrder.setOrderDateTime(new Date());
            customerOrder.setOrderDetails(new ArrayList<>());
            customerOrder.getOrderDetails().addAll(
                    products.stream().map(p -> {
                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setProduct(p);
                        orderDetail.setUnitPrice(p.getPrice());
                        orderDetail.setQuantity(quantityByProduct.get(p.getId()));
                        orderDetail.setCustomerOrder(customerOrder);
                        return orderDetail;
                    }).collect(Collectors.toList())
            );
            orderRepository.save(customerOrder);
            return billingService.getBillingDetail(customerOrder);
        }
        throw new Exception("Order Not Placed");
    }
}
