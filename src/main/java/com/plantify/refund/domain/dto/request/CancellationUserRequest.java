package com.plantify.refund.domain.dto.request;

import com.plantify.refund.domain.entity.Cancellation;
import com.plantify.refund.domain.entity.Status;

public record CancellationUserRequest(
        Long paymentId,
        String reason
) {
    public Cancellation toEntity(Long userId) {
        return Cancellation.builder()
                .userId(userId)
                .paymentId(paymentId)
                .reason(reason)
                .status(Status.PENDING)
                .build();
    }
}

