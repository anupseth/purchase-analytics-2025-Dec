package com.anup.receipt_analytics.repository;


import com.anup.receipt_analytics.domain.ReceiptItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptItemRepository extends JpaRepository<ReceiptItem, Long> {
}

