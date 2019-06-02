package com.test.billing.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.billing.service.discount.DiscountInfo;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderBody {
    private Long id;
    private Long customerId;
    private Float total;
    private Float discount;
    private Float netTotal;
    private List<OrderItemBody> items;
    private List<DiscountInfo> discountDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(Float netTotal) {
        this.netTotal = netTotal;
    }

    public List<OrderItemBody> getItems() {
        return items;
    }

    public void setItems(List<OrderItemBody> items) {
        this.items = items;
    }

    public List<DiscountInfo> getDiscountDetails() {
        return discountDetails;
    }

    public void setDiscountDetails(List<DiscountInfo> discountDetails) {
        this.discountDetails = discountDetails;
    }
}
