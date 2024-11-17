package com.plantify.refund.controller;

import com.plantify.refund.domain.dto.request.RefundRequest;
import com.plantify.refund.domain.dto.request.RefundUpdateRequest;
import com.plantify.refund.domain.dto.response.RefundResponse;
import com.plantify.refund.global.response.ApiResponse;
import com.plantify.refund.service.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/refunds")
public class RefundController {

    private final RefundService refundService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RefundResponse>>> getAllRefunds() {
        List<RefundResponse> refunds = refundService.getAllRefunds();
        return ResponseEntity.ok(ApiResponse.ok(refunds));
    }

    @GetMapping("/{refundId}")
    public ResponseEntity<ApiResponse<RefundResponse>> getRefund(@PathVariable Long refundId) {
        RefundResponse refund = refundService.getRefund(refundId);
        return ResponseEntity.ok(ApiResponse.ok(refund));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RefundResponse>> createRefund(@RequestBody RefundRequest request) {
        RefundResponse refund = refundService.createRefund(request);
        return ResponseEntity.ok(ApiResponse.ok(refund));
    }

    @PutMapping("/{refundId}/reason")
    public ResponseEntity<ApiResponse<RefundResponse>> updateRefundReason(
            @PathVariable Long refundId,
            @RequestBody String reason) {
        RefundResponse refund = refundService.updateRefundReason(refundId, reason);
        return ResponseEntity.ok(ApiResponse.ok(refund));
    }

    @PutMapping("/{refundId}")
    public ResponseEntity<ApiResponse<RefundResponse>> updateRefundStatus(
            @PathVariable Long refundId,
            @RequestBody RefundUpdateRequest request) {
        RefundResponse refund = refundService.updateRefundStatus(refundId, request);
        return ResponseEntity.ok(ApiResponse.ok(refund));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<List<RefundResponse>>> getRefundsByUser(@PathVariable Long userId) {
        List<RefundResponse> refunds = refundService.getRefundsByUser(userId);
        return ResponseEntity.ok(ApiResponse.ok(refunds));
    }
}

