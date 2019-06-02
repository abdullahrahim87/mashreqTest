package com.test.billing.service.discount;

import com.test.billing.entity.CustomerOrder;

public interface DiscountCalculator {
    DiscountInfo calculate(CustomerOrder order, Float netTotal);
}
