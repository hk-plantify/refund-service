package com.plantify.refund.service.cancellation;

import com.plantify.refund.util.UserInfoProvider;
import com.plantify.refund.domain.dto.request.CancellationReasonRequest;
import com.plantify.refund.domain.dto.request.CancellationUserRequest;
import com.plantify.refund.domain.dto.response.CancellationUserResponse;
import com.plantify.refund.domain.entity.Cancellation;
import com.plantify.refund.global.exception.ApplicationException;
import com.plantify.refund.global.exception.errorcode.CancellationErrorCode;
import com.plantify.refund.repository.CancellationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancellationUserServiceImpl implements CancellationUserService {

    private final CancellationRepository cancellationRepository;
    private final UserInfoProvider userInfoProvider;

    @Override
    public Page<CancellationUserResponse> getAllCancellations(Pageable pageable) {
        Long userId = userInfoProvider.getUserInfo().userId();
        return cancellationRepository.findByUserId(userId, pageable)
                .map(CancellationUserResponse::from);
    }

    @Override
    public CancellationUserResponse getCancellationById(Long cancellationId) {
        Long userId = userInfoProvider.getUserInfo().userId();
        Cancellation cancellation = cancellationRepository.findByCancellationIdAndUserId(cancellationId, userId)
                .orElseThrow(() -> new ApplicationException(CancellationErrorCode.CANCELLATION_NOT_FOUND));
        return CancellationUserResponse.from(cancellation);
    }

    @Override
    public CancellationUserResponse createCancellation(CancellationUserRequest request) {
        Long userId = userInfoProvider.getUserInfo().userId();
        Cancellation cancellation = request.toEntity(userId);
        cancellationRepository.save(cancellation);
        return CancellationUserResponse.from(cancellation);
    }

    @Override
    public CancellationUserResponse updateCancellationReason(Long cancellationId, CancellationReasonRequest request) {
        Long userId = userInfoProvider.getUserInfo().userId();
        Cancellation cancellation = cancellationRepository.findByCancellationIdAndUserId(cancellationId, userId)
                .orElseThrow(() -> new ApplicationException(CancellationErrorCode.CANCELLATION_NOT_FOUND));

        cancellation = cancellation.toBuilder()
                .reason(request.reason())
                .build();
        cancellationRepository.save(cancellation);

        return CancellationUserResponse.from(cancellation);
    }
}
