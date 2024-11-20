package com.plantify.refund.domain.dto.response;

import com.plantify.refund.domain.entity.Cancellation;
import com.plantify.refund.domain.entity.Status;

import java.time.LocalDateTime;

public record CancellationUserResponse(
        Long cancellationId,
        Long paymentId,
        String reason,
        Status status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static CancellationUserResponse from(Cancellation cancellation) {
        return new CancellationUserResponse(
                cancellation.getCancellationId(),
                cancellation.getPaymentId(),
                cancellation.getReason(),
                cancellation.getStatus(),
                cancellation.getCreatedAt(),
                cancellation.getUpdatedAt()
        );
    }
}
