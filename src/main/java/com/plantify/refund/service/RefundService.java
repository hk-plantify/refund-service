package com.plantify.refund.service;

import com.plantify.refund.domain.dto.request.RefundRequest;
import com.plantify.refund.domain.dto.request.RefundUpdateRequest;
import com.plantify.refund.domain.dto.response.RefundResponse;

import java.util.List;

public interface RefundService {

    List<RefundResponse> getAllRefunds();
    RefundResponse getRefund(Long refundId);
    RefundResponse createRefund(RefundRequest request);
    RefundResponse updateRefundReason(Long refundId, String reason);
    RefundResponse updateRefundStatus(Long refundId, RefundUpdateRequest request);
    List<RefundResponse> getRefundsByUser(Long userId);
}
