package com.plantify.refund.service;

import com.plantify.refund.domain.dto.request.CancellationRequest;
import com.plantify.refund.domain.dto.request.CancellationUpdateRequest;
import com.plantify.refund.domain.dto.response.CancellationResponse;

import java.util.List;

public interface CancellationService {

    List<CancellationResponse> getAllCancellations();
    CancellationResponse getCancellation(Long cancellationId);
    CancellationResponse createCancellation(CancellationRequest request);
    CancellationResponse updateCancellationReason(Long cancellationId, String reason);
    CancellationResponse updateCancellationStatus(Long cancellationId, CancellationUpdateRequest request);
    List<CancellationResponse> getCancellationsByUser(Long userId);
}
