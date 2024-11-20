package com.plantify.refund.domain.dto.request;

import com.plantify.refund.domain.entity.Status;

<<<<<<<< HEAD:src/main/java/com/plantify/refund/domain/dto/request/CancellationStatusRequest.java
public record CancellationStatusRequest(
        Status status,
        String reason
) {}
========
public record RefundStatusRequest(
        Status status,
        String reason
) {}
>>>>>>>> feat/refund-service:src/main/java/com/plantify/refund/domain/dto/request/RefundStatusRequest.java
