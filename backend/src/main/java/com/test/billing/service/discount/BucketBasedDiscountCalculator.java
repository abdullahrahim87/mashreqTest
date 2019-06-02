package com.test.billing.service.discount;

import com.test.billing.entity.CustomerOrder;
import com.test.billing.entity.OrderDetail;

import java.util.List;

public class BucketBasedDiscountCalculator extends AbstractDiscountCalculator {
    int bucketSize = 100;
    @Override
    DiscountInfo calculate(CustomerOrder order, List<OrderDetail> orderDetails, Float netTotal) {
        DiscountInfo discountInfo = new DiscountInfo();
        discountInfo.setPercent(null);
        Float total = netTotal;

        int bucketsCount = Math.round(total)/bucketSize;
        if(bucketsCount > 0) {
            Float discount = (bucketsCount * 5F);
            discountInfo.setAmount(discount);
            discountInfo.setDiscountType(DiscountType.QUANTITY);
        }
        return discountInfo;
    }
}
