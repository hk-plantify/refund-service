package com.plantify.refund.controller.refund;

import com.plantify.refund.domain.dto.request.RefundStatusRequest;
import com.plantify.refund.domain.dto.response.RefundAdminResponse;
import com.plantify.refund.global.response.ApiResponse;
import com.plantify.refund.service.refund.RefundAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/refunds")
public class RefundAdminController {

    private final RefundAdminService refundAdminService;

    // 모든 환불 조회
    @GetMapping
    public ResponseEntity<ApiResponse<List<RefundAdminResponse>>> getAllRefunds() {
        List<RefundAdminResponse> response = refundAdminService.getAllRefunds();
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    // 특정 환불 조회
    @GetMapping("/{refundId}")
    public ResponseEntity<ApiResponse<RefundAdminResponse>> getRefundById(@PathVariable Long refundId) {
        RefundAdminResponse response = refundAdminService.getRefundById(refundId);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    // 환불 상태 변경
    @PutMapping("/{refundId}")
    public ResponseEntity<ApiResponse<RefundAdminResponse>> updateRefundStatus(
            @PathVariable Long refundId, @RequestBody RefundStatusRequest request) {
        RefundAdminResponse response = refundAdminService.updateRefundStatus(refundId, request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    // 특정 사용자 환불 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<List<RefundAdminResponse>>> getRefundsByUserId(@PathVariable Long userId) {
        List<RefundAdminResponse> response = refundAdminService.getRefundsByUserId(userId);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}