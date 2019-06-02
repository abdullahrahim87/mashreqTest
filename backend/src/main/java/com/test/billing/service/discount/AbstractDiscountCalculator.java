package com.test.billing.service.discount;

import com.test.billing.entity.Customer;
import com.test.billing.entity.CustomerOrder;
import com.test.billing.entity.OrderDetail;

import java.util.List;

abstract public class AbstractDiscountCalculator implements DiscountCalculator {

    List<OrderDetail> filterItems(List<OrderDetail> orderDetails) {
        return orderDetails;
    }

    abstract DiscountInfo calculate(CustomerOrder order, List<OrderDetail> orderDetails, Float netTotal);

    protected float getTotal(List<OrderDetail> orderDetails) {
        return (float) orderDetails.stream().mapToDouble(o -> o.getUnitPrice() * o.getQuantity()).sum();
    }

    @Override
    public DiscountInfo calculate(CustomerOrder order, Float netTotal) {
        return calculate(order, filterItems(order.getOrderDetails()), netTotal);
    }
}
