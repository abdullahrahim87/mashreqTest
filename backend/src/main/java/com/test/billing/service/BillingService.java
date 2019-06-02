package com.test.billing.service;

import com.test.billing.body.OrderBody;
import com.test.billing.body.OrderItemBody;
import com.test.billing.entity.CustomerOrder;
import com.test.billing.entity.OrderDetail;
import com.test.billing.service.discount.DiscountCalculatorChain;
import com.test.billing.service.discount.DiscountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillingService {
    @Autowired
    private DiscountCalculatorChain discountCalculatorChain;

    private float getTotal(List<OrderDetail> orderDetails) {
        return (float) orderDetails.stream().mapToDouble(o -> o.getUnitPrice() * o.getQuantity()).sum();
    }
    public OrderBody getBillingDetail(CustomerOrder customerOrder) {
        List<DiscountInfo> discountInfos = discountCalculatorChain.calculate(customerOrder);
        float total = getTotal(customerOrder.getOrderDetails());
        float discount = (float) discountInfos.stream().mapToDouble(DiscountInfo::getAmount).sum();
        float netTotal = total - discount;
        OrderBody orderBody = new OrderBody();
        orderBody.setCustomerId(customerOrder.getCustomer().getPerson().getId());
        orderBody.setTotal(total);
        orderBody.setDiscount(discount);
        orderBody.setNetTotal(netTotal);
        orderBody.setItems(
                customerOrder.getOrderDetails().stream().map(od -> {
                    OrderItemBody item = new OrderItemBody();
                    item.setAmount(od.getQuantity() * od.getUnitPrice());
                    item.setQuantity(od.getQuantity());
                    item.setUnitPrice(od.getUnitPrice());
                    item.setProductId(od.getProduct().getId());
                    return item;
                }).collect(Collectors.toList())
        );
        orderBody.setId(customerOrder.getId());
        orderBody.setDiscountDetails(discountInfos);
        return orderBody;
    }

}
