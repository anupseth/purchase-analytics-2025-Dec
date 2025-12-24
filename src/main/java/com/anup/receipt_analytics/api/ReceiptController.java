package com.anup.receipt_analytics.api;


import com.anup.receipt_analytics.api.dto.CreateReceiptRequest;
import com.anup.receipt_analytics.service.ReceiptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping
    public ResponseEntity<Long> createReceipt(
            @RequestBody CreateReceiptRequest request
    ) {
        Long receiptId = receiptService.createReceipt(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(receiptId);
    }
}
