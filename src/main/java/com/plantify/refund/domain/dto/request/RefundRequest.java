package com.plantify.refund.domain.dto.request;

import com.plantify.refund.domain.entity.Refund;
import com.plantify.refund.domain.entity.Status;

public record RefundRequest(
        Long paymentId,
        String reason
) {

    public Refund toEntity(Long userId) {
        return Refund.builder()
                .paymentId(paymentId)
                .userId(userId)
                .reason(reason)
                .status(Status.PENDING)
                .build();
    }
}

