package com.plantify.refund.service.refund;

import com.plantify.refund.util.UserInfoProvider;
import com.plantify.refund.domain.dto.request.RefundReasonRequest;
import com.plantify.refund.domain.dto.request.RefundUserRequest;
import com.plantify.refund.domain.dto.response.RefundUserResponse;
import com.plantify.refund.domain.entity.Refund;
import com.plantify.refund.global.exception.ApplicationException;
import com.plantify.refund.global.exception.errorcode.RefundErrorCode;
import com.plantify.refund.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefundUserServiceImpl implements RefundUserService {

    private final RefundRepository refundRepository;
    private final UserInfoProvider userInfoProvider;

    @Override
    public Page<RefundUserResponse> getAllRefundsByUser(Pageable pageable) {
        Long userId = userInfoProvider.getUserInfo().userId();
        return refundRepository.findByUserId(userId, pageable)
                .map(RefundUserResponse::from);
    }

    @Override
    public RefundUserResponse getRefundById(Long refundId) {
        Long userId = userInfoProvider.getUserInfo().userId();
        Refund refund = refundRepository.findByRefundIdAndUserId(refundId, userId)
                .orElseThrow(() -> new ApplicationException(RefundErrorCode.REFUND_NOT_FOUND));
        return RefundUserResponse.from(refund);
    }

    @Override
    public RefundUserResponse createRefund(RefundUserRequest request) {
        Long userId = userInfoProvider.getUserInfo().userId();
        Refund refund = request.toEntity(userId);
        refundRepository.save(refund);
        return RefundUserResponse.from(refund);
    }

    @Override
    public RefundUserResponse updateRefundReasonById(Long refundId, RefundReasonRequest request) {
        Long userId = userInfoProvider.getUserInfo().userId();
        Refund refund = refundRepository.findByRefundIdAndUserId(refundId, userId)
                .orElseThrow(() -> new ApplicationException(RefundErrorCode.REFUND_NOT_FOUND));

        refund = refund.toBuilder()
                .reason(request.reason())
                .build();

        refundRepository.save(refund);
        return RefundUserResponse.from(refund);
    }
}