package com.anup.receipt_analytics.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "receipt_items")
public class ReceiptItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private String originalName;

    private String normalizedName;

    @Column(nullable = false)
    private BigDecimal price;

    private Integer quantity;

    protected ReceiptItem() {}

    public ReceiptItem(String originalName, BigDecimal price, Integer quantity) {
        this.originalName = originalName;
        this.price = price;
        this.quantity = quantity;
    }

    void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getNormalizedName() {
        return normalizedName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}