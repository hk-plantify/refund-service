package com.plantify.refund.service.refund;

import com.plantify.refund.domain.dto.request.RefundStatusRequest;
import com.plantify.refund.domain.dto.response.RefundAdminResponse;

import java.util.List;

public interface RefundAdminService {

    List<RefundAdminResponse> getAllRefunds();
    RefundAdminResponse getRefundById(Long refundId);
    RefundAdminResponse updateRefundStatus(Long refundId, RefundStatusRequest request);
    List<RefundAdminResponse> getRefundsByUserId(Long userId);
}
