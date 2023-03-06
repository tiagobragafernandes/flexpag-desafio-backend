package com.flexpag.paymentscheduler.controllers.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateSchedulingRequest {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss") //timestamp
    private LocalDateTime schedulingDate;

    private Double paymentAmount;

}
