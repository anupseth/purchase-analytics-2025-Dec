package com.anup.receipt_analytics.service;

import com.anup.receipt_analytics.api.dto.CreateReceiptRequest;
import com.anup.receipt_analytics.api.dto.CreateReceiptItemRequest;
import com.anup.receipt_analytics.domain.Receipt;
import com.anup.receipt_analytics.domain.ReceiptItem;
import com.anup.receipt_analytics.domain.Store;
import com.anup.receipt_analytics.repository.ReceiptRepository;
import com.anup.receipt_analytics.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReceiptService {

    private final StoreRepository storeRepository;
    private final ReceiptRepository receiptRepository;

    public ReceiptService(StoreRepository storeRepository,
                          ReceiptRepository receiptRepository) {
        this.storeRepository = storeRepository;
        this.receiptRepository = receiptRepository;
    }

    @Transactional
    public Long createReceipt(CreateReceiptRequest request) {

        Store store = storeRepository.findByName(request.getStoreName())
                .orElseGet(() ->
                        storeRepository.save(
                                new Store(
                                        request.getStoreName(),
                                        request.getStoreCountry()
                                )
                        )
                );

        Receipt receipt = new Receipt(
                store,
                request.getPurchaseTime(),
                request.getTotalAmount()
        );

        for (CreateReceiptItemRequest itemRequest : request.getItems()) {
            ReceiptItem item = new ReceiptItem(
                    itemRequest.getOriginalName(),
                    itemRequest.getPrice(),
                    itemRequest.getQuantity()
            );
            receipt.addItem(item);
        }

        Receipt saved = receiptRepository.save(receipt);
        return saved.getId();
    }
}
