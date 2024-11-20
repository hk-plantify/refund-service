package com.plantify.refund.service;

import com.plantify.refund.util.UserInfoProvider;
import com.plantify.refund.global.exception.ApplicationException;
import com.plantify.refund.global.exception.errorcode.RefundErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserInfoProvider userInfoProvider;
    private final List<String> adminRoles;

    @Override
    public boolean validateAdminRole() {
        String role = userInfoProvider.getUserInfo().role();
        if (adminRoles.contains(role)) {
            return true;
        }
        throw new ApplicationException(RefundErrorCode.UNAUTHORIZED_ACCESS);
    }

    @Override
    public void validateOwnership(Long ownerId) {
        Long userId = userInfoProvider.getUserInfo().userId();
        if (!userId.equals(ownerId)) {
            throw new ApplicationException(RefundErrorCode.UNAUTHORIZED_ACCESS);
        }
    }

    @Override
    public Long getUserId() {
        return userInfoProvider.getUserInfo().userId();
    }
}

