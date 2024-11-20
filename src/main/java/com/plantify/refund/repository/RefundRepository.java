package com.plantify.refund.repository;

import com.plantify.refund.domain.entity.Refund;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RefundRepository extends JpaRepository<Refund, Long> {

    List<Refund> findByUserId(Long userId);
    Page<Refund> findByUserId(Long userId, Pageable pageable);
    Optional<Refund> findByRefundIdAndUserId(Long refundId, Long userId);
}
