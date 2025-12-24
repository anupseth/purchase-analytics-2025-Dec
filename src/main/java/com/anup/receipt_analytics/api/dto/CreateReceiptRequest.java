package com.anup.receipt_analytics.api.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CreateReceiptRequest {

    private String storeName;
    private String storeCountry;

    private LocalDateTime purchaseTime;
    private BigDecimal totalAmount;

    private List<CreateReceiptItemRequest> items;

    public String getStoreName() {
        return storeName;
    }

    public String getStoreCountry() {
        return storeCountry;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public List<CreateReceiptItemRequest> getItems() {
        return items;
    }
}
