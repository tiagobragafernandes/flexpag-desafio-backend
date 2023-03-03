package com.flexpag.paymentscheduler.services;

import com.flexpag.paymentscheduler.controllers.requests.PaymentRequest;
import com.flexpag.paymentscheduler.models.PaymentScheduling;

public interface PaymentScheduleService {
   PaymentScheduling createPaymentSchedule(PaymentRequest paymentRequest) throws Exception;

   void deleteSchedule(Long id) throws Exception;

}
