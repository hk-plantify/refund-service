package com.plantify.refund.domain.dto.response;

import com.plantify.refund.domain.entity.Refund;
import com.plantify.refund.domain.entity.Status;

import java.time.LocalDateTime;

public record RefundAdminResponse(
        Long refundId,
        Long userId,
        Long paymentId,
        String reason,
        String refundTo,
        Status status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static RefundAdminResponse from(Refund refund) {
        return new RefundAdminResponse(
                refund.getRefundId(),
                refund.getUserId(),
                refund.getPaymentId(),
                refund.getReason(),
                refund.getRefundTo(),
                refund.getStatus(),
                refund.getCreatedAt(),
                refund.getUpdatedAt()
        );
    }
}