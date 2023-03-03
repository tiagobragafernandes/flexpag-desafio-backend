package com.flexpag.paymentscheduler.controllers;

import com.flexpag.paymentscheduler.controllers.requests.CreateSchedulingRequest;
import com.flexpag.paymentscheduler.controllers.requests.EditSchedulingRequest;
import com.flexpag.paymentscheduler.models.PaymentScheduling;
import com.flexpag.paymentscheduler.services.PaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/payment-scheduling")
public class PaymentSchedulingController {
    @Autowired
    private PaymentScheduleService paymentScheduleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentScheduling addPaymentScheduling(@RequestBody CreateSchedulingRequest createSchedulingRequest) {

        try{
            return paymentScheduleService.createPaymentSchedule(createSchedulingRequest);
        }catch (Exception exc){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentScheduling paymentSchedulingDetails(@PathVariable Long id){
        try {
            return paymentScheduleService.paymentSchedulingDetails(id);
        }catch (Exception exc){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PaymentScheduling deleteSchedule(@PathVariable Long id){
        try {
            paymentScheduleService.deleteSchedule(id);
        }catch (Exception exc){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
        return null;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public PaymentScheduling editPaymentScheduling(@RequestBody EditSchedulingRequest editSchedulingRequest){
        try{
            return paymentScheduleService.editPaymentSchedule(editSchedulingRequest);
        }catch (Exception exc){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
    }

}

