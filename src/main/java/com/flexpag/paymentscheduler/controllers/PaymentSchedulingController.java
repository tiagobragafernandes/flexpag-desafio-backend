package com.flexpag.paymentscheduler.controllers;

import com.flexpag.paymentscheduler.controllers.requests.CreateSchedulingRequest;
import com.flexpag.paymentscheduler.controllers.requests.EditSchedulingRequest;
import com.flexpag.paymentscheduler.models.PaymentScheduling;
import com.flexpag.paymentscheduler.services.PaymentSchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/payment-scheduling") //Rota principal da API
public class PaymentSchedulingController {
    @Autowired
    private PaymentSchedulingService paymentSchedulingService;

    /* Cria um novo agendamento de pagamento
    * O formato da requisição deve ser da seguinte maneira:
    *   {
    *   "paymentAmount": 275.0,
	*   "schedulingDate": "2023-03-15T03:00:00"
    *   }
    * Ao enviar o status http esperado é o 201 e você deverá ter como retorno os dados
    * referentes a esse agendamento.
    *
    * localhost:8080/payment-scheduling (POST)
    */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentScheduling createPaymentScheduling(@RequestBody CreateSchedulingRequest createSchedulingRequest) {

        try {
            return paymentSchedulingService.createPaymentScheduling(createSchedulingRequest);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
    }

    //Permite verificar o status do pagamento, você deve enviar via queryParam o id válido;
    //localhost:8080/payment-scheduling/id (GET)
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentScheduling getPaymentSchedulingDetails(@PathVariable Long id) {
        try {
            return paymentSchedulingService.getPaymentSchedulingDetails(id);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
    }

    //Permite excluir um agendamento de pagamento que não foi pago. Para isso você deve informar um id válido;
    //localhost:8080/payment-scheduling/id (PUT)
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PaymentScheduling deleteScheduling(@PathVariable Long id) {
        try {
            paymentSchedulingService.deleteScheduling(id);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
        return null;
    }

    //Permite editar a data de agendamento de um pagamento, para isso você deve informar um id válido;
    //localhost:8080/payment-scheduling/id (PUT)
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public PaymentScheduling editPaymentScheduling(@RequestBody EditSchedulingRequest editSchedulingRequest) {
        try {
            return paymentSchedulingService.editPaymentScheduling(editSchedulingRequest);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
    }

}

