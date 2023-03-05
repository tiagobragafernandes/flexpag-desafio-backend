package com.flexpag.paymentscheduler.controllers.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EditSchedulingRequest {

    private Long id;
    private LocalDateTime schedulingDate;

}