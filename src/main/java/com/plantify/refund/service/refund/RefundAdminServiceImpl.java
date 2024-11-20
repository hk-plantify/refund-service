package com.plantify.refund.service.refund;

import com.plantify.refund.domain.dto.request.RefundStatusRequest;
import com.plantify.refund.domain.dto.response.RefundAdminResponse;
import com.plantify.refund.domain.entity.Refund;
import com.plantify.refund.global.exception.ApplicationException;
import com.plantify.refund.global.exception.errorcode.RefundErrorCode;
import com.plantify.refund.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefundAdminServiceImpl implements RefundAdminService {

    private final RefundRepository refundRepository;


    @Override
    public List<RefundAdminResponse> getAllRefunds() {
        return refundRepository.findAll().stream()
                .map(RefundAdminResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public RefundAdminResponse getRefundById(Long refundId) {
        Refund refund = refundRepository.findById(refundId)
                .orElseThrow(() -> new ApplicationException(RefundErrorCode.REFUND_NOT_FOUND));
        return RefundAdminResponse.from(refund);
    }

    @Override
    public RefundAdminResponse updateRefundStatus(Long refundId, RefundStatusRequest request) {
        Refund refund = refundRepository.findById(refundId)
                .orElseThrow(() -> new ApplicationException(RefundErrorCode.REFUND_NOT_FOUND));

        refund = refund.toBuilder()
                .status(request.status())
                .reason(request.reason())
                .build();
        refundRepository.save(refund);

        return RefundAdminResponse.from(refund);
    }

    @Override
    public List<RefundAdminResponse> getRefundsByUserId(Long userId) {
        return refundRepository.findByUserId(userId).stream()
                .map(RefundAdminResponse::from)
                .collect(Collectors.toList());
    }
}