package com.plantify.refund.domain.dto.request;

import com.plantify.refund.domain.entity.Refund;
import com.plantify.refund.domain.entity.Status;

<<<<<<<< HEAD:src/main/java/com/plantify/refund/domain/dto/request/CancellationUserRequest.java
public record CancellationUserRequest(
========
public record RefundUserRequest(
>>>>>>>> feat/refund-service:src/main/java/com/plantify/refund/domain/dto/request/RefundUserRequest.java
        Long paymentId,
        String reason,
        String refundTo
) {
<<<<<<<< HEAD:src/main/java/com/plantify/refund/domain/dto/request/CancellationUserRequest.java
    public Cancellation toEntity(Long userId) {
        return Cancellation.builder()
========

    public Refund toEntity(Long userId) {
        return Refund.builder()
>>>>>>>> feat/refund-service:src/main/java/com/plantify/refund/domain/dto/request/RefundUserRequest.java
                .userId(userId)
                .paymentId(paymentId)
                .reason(reason)
                .refundTo(refundTo)
                .status(Status.PENDING)
                .build();
    }
}
