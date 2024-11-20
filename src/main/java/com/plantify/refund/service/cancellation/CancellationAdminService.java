package com.plantify.refund.service.cancellation;

import com.plantify.refund.domain.dto.request.CancellationStatusRequest;
import com.plantify.refund.domain.dto.response.CancellationAdminResponse;

import java.util.List;

public interface CancellationAdminService {

    List<CancellationAdminResponse> getAllCancellations();
    CancellationAdminResponse getCancellationById(Long cancellationId);
    CancellationAdminResponse updateCancellationStatus(Long cancellationId, CancellationStatusRequest request);
    List<CancellationAdminResponse> getCancellationsByUserId(Long userId);
}