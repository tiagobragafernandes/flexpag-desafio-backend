package com.flexpag.paymentscheduler.models;

import com.flexpag.paymentscheduler.controllers.requests.CreateSchedulingRequest;
import com.flexpag.paymentscheduler.models.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_scheduling")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentScheduling implements Serializable {

    //Permitindo a serialização da classe
    private static final long serialVersionUID = 1L;

    //Criação das colunas do BD seguindo as regras de negócio
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 7)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo") //timestamp
    private LocalDateTime schedulingDateTime;

    public PaymentScheduling(@NotNull CreateSchedulingRequest createSchedulingRequest) {
        this.status = PaymentStatus.PENDING;
        this.amount = createSchedulingRequest.getPaymentAmount();
        this.schedulingDateTime = createSchedulingRequest.getSchedulingDate();
    }

}
