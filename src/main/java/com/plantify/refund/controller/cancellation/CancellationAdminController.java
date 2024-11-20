package com.plantify.refund.controller.cancellation;

import com.plantify.refund.domain.dto.request.CancellationStatusRequest;
import com.plantify.refund.domain.dto.response.CancellationAdminResponse;
import com.plantify.refund.global.response.ApiResponse;
import com.plantify.refund.service.cancellation.CancellationAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/refunds/cancellations")
public class CancellationAdminController {

    private final CancellationAdminService cancellationAdminService;

    // 모든 취소 요청 조회
    @GetMapping
    public ResponseEntity<ApiResponse<List<CancellationAdminResponse>>> getAllCancellations() {
        List<CancellationAdminResponse> response = cancellationAdminService.getAllCancellations();
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    // 특정 취소 요청 조회
    @GetMapping("/{cancellationId}")
    public ResponseEntity<ApiResponse<CancellationAdminResponse>> getCancellationById(
            @PathVariable Long cancellationId) {
        CancellationAdminResponse response = cancellationAdminService.getCancellationById(cancellationId);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    // 특정 결제 취소 승인/거절
    @PutMapping("/{cancellationId}")
    public ResponseEntity<ApiResponse<CancellationAdminResponse>> updateCancellationStatus(
            @PathVariable Long cancellationId, @RequestBody CancellationStatusRequest request) {
        CancellationAdminResponse response = cancellationAdminService.updateCancellationStatus(cancellationId, request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    // 특정 사용자 취소 요청 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<List<CancellationAdminResponse>>> getCancellationsByUserId(
            @PathVariable Long userId) {
        List<CancellationAdminResponse> response = cancellationAdminService.getCancellationsByUserId(userId);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}
