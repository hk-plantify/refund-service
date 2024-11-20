package com.plantify.refund.service.cancellation;

import com.plantify.refund.domain.dto.request.CancellationReasonRequest;
import com.plantify.refund.domain.dto.request.CancellationUserRequest;
import com.plantify.refund.domain.dto.response.CancellationUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CancellationUserService {

    Page<CancellationUserResponse> getAllCancellations(Pageable pageable);
    CancellationUserResponse getCancellationById(Long cancellationId);
    CancellationUserResponse createCancellation(CancellationUserRequest request);
    CancellationUserResponse updateCancellationReason(Long cancellationId, CancellationReasonRequest request);
}