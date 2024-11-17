package com.plantify.refund.service;

public interface AuthenticationService {

    boolean validateAdminRole();
    void validateOwnership(Long ownerId);
    Long getUserId();
}
