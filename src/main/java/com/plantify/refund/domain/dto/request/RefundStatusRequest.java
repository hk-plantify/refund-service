package com.plantify.refund.domain.dto.request;

import com.plantify.refund.domain.entity.Status;

public record RefundStatusRequest(
        Status status,
        String reason
) {}