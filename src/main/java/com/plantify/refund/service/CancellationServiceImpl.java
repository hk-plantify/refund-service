package com.plantify.refund.service;

import com.plantify.refund.domain.dto.request.CancellationRequest;
import com.plantify.refund.domain.dto.request.CancellationUpdateRequest;
import com.plantify.refund.domain.dto.response.CancellationResponse;
import com.plantify.refund.domain.entity.Cancellation;
import com.plantify.refund.global.exception.ApplicationException;
import com.plantify.refund.global.exception.errorcode.CancellationErrorCode;
import com.plantify.refund.repository.CancellationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CancellationServiceImpl implements CancellationService {

    private final CancellationRepository cancellationRepository;
    private final AuthenticationService authenticationService;

    @Override
    public List<CancellationResponse> getAllCancellations() {
        if(authenticationService.validateAdminRole()) {
            return cancellationRepository.findAll()
                    .stream()
                    .map(CancellationResponse::from)
                    .collect(Collectors.toList());
        }
        Long userId = authenticationService.getUserId();
        return cancellationRepository.findByUserId(userId)
                .stream()
                .map(CancellationResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public CancellationResponse getCancellation(Long cancellationId) {
        Cancellation cancellation = cancellationRepository.findById(cancellationId)
                .orElseThrow(() -> new ApplicationException(CancellationErrorCode.CANCELLATION_NOT_FOUND));
        authenticationService.validateOwnership(cancellation.getUserId());

        return CancellationResponse.from(cancellation);
    }

    @Override
    public CancellationResponse createCancellation(CancellationRequest request) {
        Long userId = authenticationService.getUserId();
        Cancellation cancellation = request.toEntity(userId);
        Cancellation savedCancellation = cancellationRepository.save(cancellation);
        return CancellationResponse.from(savedCancellation);
    }

    @Override
    public CancellationResponse updateCancellationReason(Long cancellationId, String reason) {
        Cancellation cancellation = cancellationRepository.findById(cancellationId)
                .orElseThrow(() -> new ApplicationException(CancellationErrorCode.CANCELLATION_NOT_FOUND));
        authenticationService.validateOwnership(cancellation.getUserId());

        Cancellation updatedCancellation = cancellation.toBuilder()
                .reason(reason)
                .build();
        Cancellation savedCancellation = cancellationRepository.save(updatedCancellation);

        return CancellationResponse.from(savedCancellation);
    }

    @Override
    public CancellationResponse updateCancellationStatus(Long cancellationId, CancellationUpdateRequest request) {
        Cancellation cancellation = cancellationRepository.findById(cancellationId)
                .orElseThrow(() -> new ApplicationException(CancellationErrorCode.CANCELLATION_NOT_FOUND));
        authenticationService.validateAdminRole();

        Cancellation updatedCancellation = cancellation.toBuilder()
                .status(request.status())
                .build();
        Cancellation savedCancellation = cancellationRepository.save(updatedCancellation);

        return CancellationResponse.from(savedCancellation);
    }

    @Override
    public List<CancellationResponse> getCancellationsByUser(Long userId) {
        authenticationService.validateAdminRole();
        return cancellationRepository.findByUserId(userId)
                .stream()
                .map(CancellationResponse::from)
                .collect(Collectors.toList());
    }
}
