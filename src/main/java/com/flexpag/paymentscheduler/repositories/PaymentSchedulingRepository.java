package com.flexpag.paymentscheduler.repositories;

import com.flexpag.paymentscheduler.models.PaymentScheduling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentSchedulingRepository extends JpaRepository<PaymentScheduling, Long> {
}
