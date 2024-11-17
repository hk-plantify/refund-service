package com.plantify.refund.repository;

import com.plantify.refund.domain.entity.Cancellation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CancellationRepository extends JpaRepository<Cancellation, Long> {

    List<Cancellation> findByUserId(Long userId);
}
