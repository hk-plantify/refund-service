package com.plantify.refund.domain.dto.request;

public record CancellationUserRequest(
        Long paymentId,
        String reason
) {}

