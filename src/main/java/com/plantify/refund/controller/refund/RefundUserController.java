package com.plantify.refund.controller.refund;

import com.plantify.refund.domain.dto.request.RefundReasonRequest;
import com.plantify.refund.domain.dto.request.RefundUserRequest;
import com.plantify.refund.domain.dto.response.RefundUserResponse;
import com.plantify.refund.global.response.ApiResponse;
import com.plantify.refund.service.refund.RefundUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/refunds")
public class RefundUserController {

    private final RefundUserService refundUserService;

    // 자신의 모든 환불 요청 조회
    @GetMapping
    public ResponseEntity<ApiResponse<Page<RefundUserResponse>>> getAllRefunds(Pageable pageable) {
        Page<RefundUserResponse> response = refundUserService.getAllRefundsByUser(pageable);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    // 자신의 특정 환불 요청 조회
    @GetMapping("/{refundId}")
    public ResponseEntity<ApiResponse<RefundUserResponse>> getRefundById(@PathVariable Long refundId) {
        RefundUserResponse response = refundUserService.getRefundById(refundId);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    // 환불 요청 생성
    @PostMapping
    public ResponseEntity<ApiResponse<RefundUserResponse>> createRefund(@RequestBody RefundUserRequest request) {
        RefundUserResponse response = refundUserService.createRefund(request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    // 자신의 특정 환불 요청 사유 변경
    @PutMapping("/{refundId}/reason")
    public ResponseEntity<ApiResponse<RefundUserResponse>> updateRefundReason(
            @PathVariable Long refundId, @RequestBody RefundReasonRequest request) {
        RefundUserResponse response = refundUserService.updateRefundReasonById(refundId, request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}