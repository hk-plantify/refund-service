package com.plantify.refund.service;

import com.plantify.refund.domain.dto.request.RefundRequest;
import com.plantify.refund.domain.dto.request.RefundUpdateRequest;
import com.plantify.refund.domain.dto.response.RefundResponse;
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
public class RefundServiceImpl implements RefundService {

    private final RefundRepository refundRepository;
    private final AuthenticationService authenticationService;

    @Override
    public List<RefundResponse> getAllRefunds() {
        if (authenticationService.validateAdminRole()) {
            return refundRepository.findAll()
                    .stream()
                    .map(RefundResponse::from)
                    .collect(Collectors.toList());
        }
        Long userId = authenticationService.getUserId();
        return refundRepository.findByUserId(userId)
                .stream()
                .map(RefundResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public RefundResponse getRefund(Long refundId) {
        Refund refund = refundRepository.findById(refundId)
                .orElseThrow(() -> new ApplicationException(RefundErrorCode.REFUND_NOT_FOUND));
        authenticationService.validateOwnership(refund.getUserId());
        return RefundResponse.from(refund);
    }

    @Override
    public RefundResponse createRefund(RefundRequest request) {
        Long userId = authenticationService.getUserId();
        Refund refund = request.toEntity(userId);
        Refund savedRefund = refundRepository.save(refund);
        return RefundResponse.from(savedRefund);
    }

    @Override
    public RefundResponse updateRefundReason(Long refundId, String reason) {
        Refund refund = refundRepository.findById(refundId)
                .orElseThrow(() -> new ApplicationException(RefundErrorCode.REFUND_NOT_FOUND));
        authenticationService.validateOwnership(refund.getUserId());
        Refund updatedRefund = refund.toBuilder()
                .reason(reason)
                .build();
        Refund savedRefund = refundRepository.save(updatedRefund);

        return RefundResponse.from(savedRefund);
    }

    @Override
    public RefundResponse updateRefundStatus(Long refundId, RefundUpdateRequest request) {
        Refund refund = refundRepository.findById(refundId)
                .orElseThrow(() -> new ApplicationException(RefundErrorCode.REFUND_NOT_FOUND));
        authenticationService.validateAdminRole();

        Refund updatedRefund = refund.toBuilder()
                .status(request.status())
                .build();
        Refund savedRefund = refundRepository.save(updatedRefund);

        return RefundResponse.from(savedRefund);
    }

    @Override
    public List<RefundResponse> getRefundsByUser(Long userId) {
        authenticationService.validateAdminRole();
        return refundRepository.findByUserId(userId)
                .stream()
                .map(RefundResponse::from)
                .collect(Collectors.toList());
    }
}

