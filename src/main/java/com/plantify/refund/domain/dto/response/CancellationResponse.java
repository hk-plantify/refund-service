package com.plantify.refund.domain.dto.response;

import com.plantify.refund.domain.entity.Cancellation;

import java.time.LocalDateTime;

public record CancellationResponse(
        Long cancellationId,
        Long paymentId,
        Long userId,
        String reason,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CancellationResponse from(Cancellation cancellation) {
        return new CancellationResponse(
                cancellation.getCancellationId(),
                cancellation.getPaymentId(),
                cancellation.getUserId(),
                cancellation.getReason(),
                cancellation.getStatus().name(),
                cancellation.getCreatedAt(),
                cancellation.getUpdatedAt()
        );
    }
}
