package com.flexpag.paymentscheduler.config;

import com.flexpag.paymentscheduler.models.PaymentScheduling;
import com.flexpag.paymentscheduler.models.enums.PaymentStatus;
import com.flexpag.paymentscheduler.repositories.PaymentSchedulingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
@Component
public class PaymentSchedulingLoader implements CommandLineRunner {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private PaymentSchedulingRepository paymentSchedulingRepository;
    @Override
    @Transactional
    public void run(String... args) throws Exception {

        PaymentScheduling paymentScheduling = new PaymentScheduling();
        paymentScheduling.setAmount(250.0);
        paymentScheduling.setSchedulingDateTime(LocalDateTime.now().minusDays(30));
        paymentScheduling.setStatus(PaymentStatus.PAID);

        PaymentScheduling paymentScheduling2 = new PaymentScheduling();
        paymentScheduling2.setAmount(150.0);
        paymentScheduling2.setSchedulingDateTime(LocalDateTime.now().minusDays(60));
        paymentScheduling2.setStatus(PaymentStatus.PAID);

        paymentSchedulingRepository.saveAll(Arrays.asList(paymentScheduling, paymentScheduling2));

    }
}