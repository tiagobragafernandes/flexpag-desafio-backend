package com.flexpag.paymentscheduler.controllers.requests;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PaymentRequest {
    private LocalDateTime schedulingDate;
    private Double paymentAmount;


}
