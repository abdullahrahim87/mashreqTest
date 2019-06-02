package com.test.billing.service.discount;

import com.test.billing.entity.Customer;
import com.test.billing.entity.CustomerOrder;
import com.test.billing.entity.OrderDetail;
import com.test.billing.entity.ProductType;

import java.util.*;
import java.util.stream.Collectors;

public class PercentageBasedDiscountCalculator extends AbstractDiscountCalculator {

    @Override
    List<OrderDetail> filterItems(List<OrderDetail> orderDetails) {
        return orderDetails != null?orderDetails.stream().filter(o -> o.getProduct().getProductType() != ProductType.GROCERY)
                .collect(Collectors.toList()): new ArrayList();
    }

    public int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
                (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    public Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

    @Override
    DiscountInfo calculate(CustomerOrder order, List<OrderDetail> orderDetails, Float netTotal) {
        float percentage = 0F;
        DiscountType discountType = null;
        DiscountInfo discountInfo = new DiscountInfo();
        if(order.getCustomer().getPerson().getEmployeeProfile() != null) {
            percentage = 30F;
            discountType = DiscountType.EMPLOYEE;
        } else if(order.getCustomer().getPerson().getAffiliate()) {
            percentage = 10F;
            discountType = DiscountType.AFFILIATION;
        } else if(order.getCustomer().getFirstOrder() != null &&
                getDiffYears(order.getOrderDateTime(), order.getCustomer().getFirstOrder()) >= 2) {
            percentage = 5F;
            discountType = DiscountType.CUSTOMER_LOYALTY;
        }
        if(discountType != null) {
            Float total = getTotal(orderDetails);
            Float discount = (total * percentage / 100F);
            discountInfo.setAmount(discount);
            discountInfo.setDiscountType(discountType);
            discountInfo.setPercent(percentage);
            discountInfo.setProductIds(orderDetails.stream().map(o -> o.getProduct().getId()).collect(Collectors.toList()));
        }
        return discountInfo;
    }
}
