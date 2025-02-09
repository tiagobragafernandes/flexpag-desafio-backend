package com.flexpag.paymentscheduler.controllers.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EditSchedulingRequest {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo") //timestamp
    private LocalDateTime schedulingDate;

}