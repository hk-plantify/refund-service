package com.plantify.refund.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Settlement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long cancellationId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long paymentId;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private Status status;
}
