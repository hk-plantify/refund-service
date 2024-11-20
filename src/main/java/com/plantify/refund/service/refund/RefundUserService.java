package com.plantify.refund.service.refund;

import com.plantify.refund.domain.dto.request.RefundReasonRequest;
import com.plantify.refund.domain.dto.request.RefundUserRequest;
import com.plantify.refund.domain.dto.response.RefundUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RefundUserService {

    Page<RefundUserResponse> getAllRefundsByUser(Pageable pageable);
    RefundUserResponse getRefundById(Long refundId);
    RefundUserResponse createRefund(RefundUserRequest request);
    RefundUserResponse updateRefundReasonById(Long refundId, RefundReasonRequest request);
}