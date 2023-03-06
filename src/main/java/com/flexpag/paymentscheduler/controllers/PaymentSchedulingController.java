package com.flexpag.paymentscheduler.controllers;

import com.flexpag.paymentscheduler.controllers.requests.CreateSchedulingRequest;
import com.flexpag.paymentscheduler.controllers.requests.EditSchedulingRequest;
import com.flexpag.paymentscheduler.models.PaymentScheduling;
import com.flexpag.paymentscheduler.services.PaymentSchedulingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/payment-scheduling") //Rota principal da API
@Api(value = "Api de detalhes de agendamento")
public class PaymentSchedulingController {
    @Autowired
    private PaymentSchedulingService paymentSchedulingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Permite criar um novo agendamento de pagamento."),
            @ApiResponse(code = 400, message = "Valida se os campos quantia e data possuem os valores esperados.")
    })
    public PaymentScheduling createPaymentScheduling(@RequestBody CreateSchedulingRequest createSchedulingRequest) {

        try {
            return paymentSchedulingService.createPaymentScheduling(createSchedulingRequest);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Permite verificar o status do pagamento, você deve enviar via queryParam o id válido."),
        @ApiResponse(code = 400, message = "Valida se os campos quantia e schedulingDateTime possuem os valores esperados.")
    })
    public PaymentScheduling getPaymentSchedulingDetails(@PathVariable Long id) {
        try {
            return paymentSchedulingService.getPaymentSchedulingDetails(id);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Permite excluir um agendamento de pagamento que não foi pago"),
            @ApiResponse(code = 400, message = "Valida se o campo id coincide com algum registro do BD.")
    })
    public PaymentScheduling deleteScheduling(@PathVariable Long id) {
        try {
            paymentSchedulingService.deleteScheduling(id);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
        return null;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Permite editar a data de agendamento de um pagamento, para isso você deve informar um id válido"),
            @ApiResponse(code = 400, message = "Valida se o campo id coincide com algum registro do BD e schedulingDateTime possui o valor permitido.")
    })
    public PaymentScheduling editPaymentScheduling(@RequestBody EditSchedulingRequest editSchedulingRequest) {
        try {
            return paymentSchedulingService.editPaymentScheduling(editSchedulingRequest);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
    }

}

