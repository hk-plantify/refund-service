package com.plantify.refund.repository;

import com.plantify.refund.domain.entity.Cancellation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CancellationRepository extends JpaRepository<Cancellation, Long> {

    List<Cancellation> findByUserId(Long userId);
    Page<Cancellation> findByUserId(Long userId, Pageable pageable);
    Optional<Cancellation> findByCancellationIdAndUserId(Long cancellationId, Long userId);
}
