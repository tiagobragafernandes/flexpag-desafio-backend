package com.flexpag.paymentscheduler.services;

import com.flexpag.paymentscheduler.controllers.requests.CreateSchedulingRequest;
import com.flexpag.paymentscheduler.controllers.requests.EditSchedulingRequest;
import com.flexpag.paymentscheduler.models.PaymentScheduling;

public interface PaymentScheduleService {
   PaymentScheduling createPaymentSchedule(CreateSchedulingRequest createSchedulingRequest) throws Exception;

   PaymentScheduling paymentSchedulingDetails(Long id) throws Exception;

   void deleteSchedule(Long id) throws Exception;

   PaymentScheduling editPaymentSchedule(EditSchedulingRequest editSchedulingRequest) throws Exception;

}
