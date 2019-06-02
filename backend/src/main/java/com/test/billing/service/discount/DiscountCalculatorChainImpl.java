package com.test.billing.service.discount;

import com.test.billing.entity.CustomerOrder;
import com.test.billing.entity.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class DiscountCalculatorChainImpl implements DiscountCalculatorChain {
    private List<DiscountCalculator> discountCalculators;

    public DiscountCalculatorChainImpl(List<DiscountCalculator> discountCalculators) {
        this.discountCalculators = discountCalculators;
    }

    protected float getTotal(List<OrderDetail> orderDetails) {
        return (float) orderDetails.stream().mapToDouble(o -> o.getUnitPrice() * o.getQuantity()).sum();
    }
    public List<DiscountInfo> calculate(CustomerOrder order) {
        List<DiscountInfo> discountInfos = new ArrayList<>();
        if(discountCalculators != null) {
            Float netTotal = getTotal(order.getOrderDetails());
            for (DiscountCalculator discountCalculator:
                  discountCalculators) {
                DiscountInfo discountInfo = discountCalculator.calculate(order, netTotal);
                if(discountInfo.discountType != null) {
                    netTotal -= discountInfo.amount;
                    discountInfos.add(discountInfo);
                }
            }
        }
        return discountInfos;
    }
}
