package com.plantify.refund.controller.cancellation;

import com.plantify.refund.domain.dto.request.CancellationReasonRequest;
import com.plantify.refund.domain.dto.request.CancellationUserRequest;
import com.plantify.refund.domain.dto.response.CancellationUserResponse;
import com.plantify.refund.global.response.ApiResponse;
import com.plantify.refund.service.cancellation.CancellationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/refunds/cancellations")
public class CancellationUserController {

    private final CancellationUserService cancellationUserService;

    // 자신의 모든 취소 조회
    @GetMapping
    public ResponseEntity<ApiResponse<Page<CancellationUserResponse>>> getAllCancellations(Pageable pageable) {
        Page<CancellationUserResponse> response = cancellationUserService.getAllCancellations(pageable);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    // 자신의 특정 취소 조회
    @GetMapping("/{cancellationId}")
    public ResponseEntity<ApiResponse<CancellationUserResponse>> getCancellationById(@PathVariable Long cancellationId) {
        CancellationUserResponse response = cancellationUserService.getCancellationById(cancellationId);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    // 취소 요청
    @PostMapping
    public ResponseEntity<ApiResponse<CancellationUserResponse>> createCancellation(
            @RequestBody CancellationUserRequest request) {
        CancellationUserResponse response = cancellationUserService.createCancellation(request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    // 자신의 취소 요청 사유 변경
    @PutMapping("/{cancellationId}/reason")
    public ResponseEntity<ApiResponse<CancellationUserResponse>> updateCancellationReason(
            @PathVariable Long cancellationId, @RequestBody CancellationReasonRequest request) {
        CancellationUserResponse response = cancellationUserService.updateCancellationReason(cancellationId, request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

}
