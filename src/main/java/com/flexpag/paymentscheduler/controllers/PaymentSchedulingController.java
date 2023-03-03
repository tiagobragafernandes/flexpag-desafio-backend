package com.flexpag.paymentscheduler.controllers;

import com.flexpag.paymentscheduler.controllers.requests.PaymentRequest;
import com.flexpag.paymentscheduler.models.PaymentScheduling;
import com.flexpag.paymentscheduler.services.PaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/payment")
public class PaymentSchedulingController {
    @Autowired
    private PaymentScheduleService paymentScheduleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentScheduling addPaymentScheduling(@RequestBody PaymentRequest paymentRequest) {

        try{
            return paymentScheduleService.createPaymentSchedule(paymentRequest);
        }catch (Exception exc){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public PaymentScheduling deleteSchedule(@PathVariable Long id){
        try {
            paymentScheduleService.deleteSchedule(id);
        }catch (Exception exc){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
        return null;
    }

}

