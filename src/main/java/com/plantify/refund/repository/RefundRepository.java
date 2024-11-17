package com.plantify.refund.repository;

import com.plantify.refund.domain.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefundRepository extends JpaRepository<Refund, Long> {

    List<Refund> findByUserId(Long userId);
}
