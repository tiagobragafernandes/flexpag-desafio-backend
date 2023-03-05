package com.flexpag.paymentscheduler.controllers.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateSchedulingRequest {
    private LocalDateTime schedulingDate;
    private Double paymentAmount;

}
