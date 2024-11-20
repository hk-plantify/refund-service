package com.plantify.refund.domain.dto.request;

import com.plantify.refund.domain.entity.Refund;
import com.plantify.refund.domain.entity.Status;

public record RefundUserRequest(
        Long paymentId,
        String reason,
        String refundTo
) {
    public Refund toEntity(Long userId) {
        return Refund.builder()
                .userId(userId)
                .paymentId(paymentId)
                .reason(reason)
                .refundTo(refundTo)
                .status(Status.PENDING)
                .build();
    }
}
