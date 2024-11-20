package com.plantify.refund.domain.dto.response;

import com.plantify.refund.domain.entity.Cancellation;
import com.plantify.refund.domain.entity.Status;

import java.time.LocalDateTime;

public record CancellationAdminResponse(
        Long cancellationId,
        Long userId,
        Long paymentId,
        String reason,
        Status status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CancellationAdminResponse from(Cancellation cancellation) {
        return new CancellationAdminResponse(
                cancellation.getCancellationId(),
                cancellation.getUserId(),
                cancellation.getPaymentId(),
                cancellation.getReason(),
                cancellation.getStatus(),
                cancellation.getCreatedAt(),
                cancellation.getUpdatedAt()
        );
    }
}
