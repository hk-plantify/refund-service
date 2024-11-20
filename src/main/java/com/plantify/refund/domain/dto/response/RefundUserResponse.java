package com.plantify.refund.domain.dto.response;

import com.plantify.refund.domain.entity.Refund;
import com.plantify.refund.domain.entity.Status;

import java.time.LocalDateTime;

public record RefundUserResponse(
        Long refundId,
        Long paymentId,
        String reason,
        String refundTo,
        Status status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static RefundUserResponse from(Refund refund) {
        return new RefundUserResponse(
                refund.getRefundId(),
                refund.getPaymentId(),
                refund.getReason(),
                refund.getRefundTo(),
                refund.getStatus(),
                refund.getCreatedAt(),
                refund.getUpdatedAt()
        );
    }
}