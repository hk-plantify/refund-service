package com.plantify.refund.controller;

import com.plantify.refund.domain.dto.request.CancellationRequest;
import com.plantify.refund.domain.dto.request.CancellationUpdateRequest;
import com.plantify.refund.domain.dto.response.CancellationResponse;
import com.plantify.refund.global.response.ApiResponse;
import com.plantify.refund.service.CancellationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/refunds/cancellations")
public class CancellationController {

    private final CancellationService cancellationService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CancellationResponse>>> getAllCancellations() {
        List<CancellationResponse> cancellations = cancellationService.getAllCancellations();
        return ResponseEntity.ok(ApiResponse.ok(cancellations));
    }

    @GetMapping("/{cancellationId}")
    public ResponseEntity<ApiResponse<CancellationResponse>> getCancellation(@PathVariable Long cancellationId) {
        CancellationResponse cancellation = cancellationService.getCancellation(cancellationId);
        return ResponseEntity.ok(ApiResponse.ok(cancellation));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CancellationResponse>> createCancellation(@RequestBody CancellationRequest request) {
        CancellationResponse cancellation = cancellationService.createCancellation(request);
        return ResponseEntity.ok(ApiResponse.ok(cancellation));
    }

    @PutMapping("/{cancellationId}/reason")
    public ResponseEntity<ApiResponse<CancellationResponse>> updateCancellation(
            @PathVariable Long cancellationId, @RequestBody String reason) {
        CancellationResponse cancellation = cancellationService.updateCancellationReason(cancellationId, reason);
        return ResponseEntity.ok(ApiResponse.ok(cancellation));
    }

    @PutMapping("/{cancellationId}")
    public ResponseEntity<ApiResponse<CancellationResponse>> updateCancellationStatus(
            @PathVariable Long cancellationId, @RequestBody CancellationUpdateRequest request) {
        CancellationResponse cancellation = cancellationService.updateCancellationStatus(cancellationId, request);
        return ResponseEntity.ok(ApiResponse.ok(cancellation));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<List<CancellationResponse>>> getCancellationsByUser(@PathVariable Long userId) {
        List<CancellationResponse> cancellations = cancellationService.getCancellationsByUser(userId);
        return ResponseEntity.ok(ApiResponse.ok(cancellations));
    }
}
