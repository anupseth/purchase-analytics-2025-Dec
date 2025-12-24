package com.anup.receipt_analytics.api.dto;

import java.math.BigDecimal;

public class CreateReceiptItemRequest {

    private String originalName;
    private BigDecimal price;
    private Integer quantity;

    public String getOriginalName() {
        return originalName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
