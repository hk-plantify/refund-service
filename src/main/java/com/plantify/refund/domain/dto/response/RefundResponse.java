package com.plantify.refund.domain.dto.response;

import com.plantify.refund.domain.entity.Refund;

import java.time.LocalDateTime;

public record RefundResponse(
        Long refundId,
        Long paymentId,
        Long userId,
        String reason,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static RefundResponse from(Refund refund) {
        return new RefundResponse(
                refund.getRefundId(),
                refund.getPaymentId(),
                refund.getUserId(),
                refund.getReason(),
                refund.getStatus().name(),
                refund.getCreatedAt(),
                refund.getUpdatedAt()
        );
    }
}