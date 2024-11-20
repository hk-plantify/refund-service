package com.plantify.refund.service.cancellation;

import com.plantify.refund.domain.dto.request.CancellationStatusRequest;
import com.plantify.refund.domain.dto.response.CancellationAdminResponse;
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
public class CancellationAdminServiceImpl implements CancellationAdminService {

    private final CancellationRepository cancellationRepository;

    @Override
    public List<CancellationAdminResponse> getAllCancellations() {
        return cancellationRepository.findAll().stream()
                .map(CancellationAdminResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public CancellationAdminResponse getCancellationById(Long cancellationId) {
        Cancellation cancellation = cancellationRepository.findById(cancellationId)
                .orElseThrow(() -> new ApplicationException(CancellationErrorCode.CANCELLATION_NOT_FOUND));
        return CancellationAdminResponse.from(cancellation);
    }

    @Override
    public CancellationAdminResponse updateCancellationStatus(Long cancellationId, CancellationStatusRequest request) {
        Cancellation cancellation = cancellationRepository.findById(cancellationId)
                .orElseThrow(() -> new ApplicationException(CancellationErrorCode.CANCELLATION_NOT_FOUND));

        cancellation = cancellation.toBuilder()
                .status(request.status())
                .reason(request.reason())
                .build();
        cancellationRepository.save(cancellation);

        return CancellationAdminResponse.from(cancellation);
    }

    @Override
    public List<CancellationAdminResponse> getCancellationsByUserId(Long userId) {
        return cancellationRepository.findByUserId(userId).stream()
                .map(CancellationAdminResponse::from)
                .collect(Collectors.toList());
    }
}
