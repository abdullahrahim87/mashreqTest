package com.test.billing.service.discount;

import com.test.billing.entity.CustomerOrder;

import java.util.List;

public interface DiscountCalculatorChain {
    List<DiscountInfo> calculate(CustomerOrder order);
}
