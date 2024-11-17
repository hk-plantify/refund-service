package com.plantify.refund.domain.dto.request;

import com.plantify.refund.domain.entity.Cancellation;
import com.plantify.refund.domain.entity.Status;

public record CancellationRequest(
        Long paymentId,
        String reason
) {
    public Cancellation toEntity(Long userId) {
        return Cancellation.builder()
                .paymentId(paymentId)
                .userId(userId)
                .reason(reason)
                .status(Status.PENDING)
                .build();
    }
}

