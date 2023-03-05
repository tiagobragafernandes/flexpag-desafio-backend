package com.flexpag.paymentscheduler.services;

import com.flexpag.paymentscheduler.controllers.requests.CreateSchedulingRequest;
import com.flexpag.paymentscheduler.controllers.requests.EditSchedulingRequest;
import com.flexpag.paymentscheduler.models.PaymentScheduling;

public interface PaymentSchedulingService {

    PaymentScheduling createPaymentScheduling(CreateSchedulingRequest createSchedulingRequest) throws Exception;

    PaymentScheduling getPaymentSchedulingDetails(Long id) throws Exception;

    void deleteScheduling(Long id) throws Exception;

    PaymentScheduling editPaymentScheduling(EditSchedulingRequest editSchedulingRequest) throws Exception;

}
