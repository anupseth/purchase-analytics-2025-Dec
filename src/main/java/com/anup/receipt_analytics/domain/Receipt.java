package com.anup.receipt_analytics.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receipts")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(nullable = false)
    private LocalDateTime purchaseTime;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceiptItem> items = new ArrayList<>();

    protected Receipt() {}

    public Receipt(Store store, LocalDateTime purchaseTime, BigDecimal totalAmount) {
        this.store = store;
        this.purchaseTime = purchaseTime;
        this.totalAmount = totalAmount;
    }

    public void addItem(ReceiptItem item) {
        items.add(item);
        item.setReceipt(this);
    }

    public Long getId() {
        return id;
    }

    public Store getStore() {
        return store;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }
}