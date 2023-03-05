package com.flexpag.paymentscheduler.services.impl;

import com.flexpag.paymentscheduler.controllers.requests.CreateSchedulingRequest;
import com.flexpag.paymentscheduler.controllers.requests.EditSchedulingRequest;
import com.flexpag.paymentscheduler.models.PaymentScheduling;
import com.flexpag.paymentscheduler.models.enums.PaymentStatus;
import com.flexpag.paymentscheduler.repositories.PaymentSchedulingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentSchedulingService implements com.flexpag.paymentscheduler.services.PaymentSchedulingService {

    @Autowired
    private final PaymentSchedulingRepository paymentSchedulingRepository;

    public PaymentSchedulingService(PaymentSchedulingRepository paymentSchedulingRepository) {
        this.paymentSchedulingRepository = paymentSchedulingRepository;
    }

    public PaymentScheduling createPaymentScheduling(CreateSchedulingRequest createSchedulingRequest) throws Exception {

        //Verifica se o valor do pagamento é nulo
        if (createSchedulingRequest.getPaymentAmount() == null) {
            throw new Exception("Você deve inserir um valor para agendar um pagamento.");
        }

        //Verifica se o valor do pagamento é menor que zero
        if (createSchedulingRequest.getPaymentAmount() <= 0) {
            throw new Exception("O valor do pagamento deve ser maior que zero.");
        }

        //Verifica se a data enviada é nula
        if (createSchedulingRequest.getSchedulingDate() == null) {
            throw new Exception("Você deve inserir uma data e hora para agendar um pagamento. (aaaa-mm-ddThh:mm:ss)");
        }

        //Verifica se a data de agendamento é superior a data atual.
        if (createSchedulingRequest.getSchedulingDate().isBefore(LocalDateTime.now())) {
            throw new Exception("A data do pagamento deve ser maior que a data de hoje.");
        }

        //Realiza a criação de um novo agendamento caso passe nas verificações.
        PaymentScheduling paymentScheduling = new PaymentScheduling(createSchedulingRequest);
        return paymentSchedulingRepository.save(paymentScheduling);
    }

    public PaymentScheduling getPaymentSchedulingDetails(Long id) throws Exception {

        //Consulta no BD o id enviado
        Optional<PaymentScheduling> paymentSchedulingOptional = paymentSchedulingRepository.findById(id);

        //Verifica com o método isPresent se o ID informado existe na base de dados.
        if (!paymentSchedulingOptional.isPresent()) {
            throw new Exception("Não há nenhum agendamento de pagamento com o id fornecido.");
        }

        //Caso passe na verificação retorna um objeto contendo as informações do agendamento
        return paymentSchedulingOptional.get();

    }

    public void deleteScheduling(Long id) throws Exception {

        //Consulta no BD o id enviado
        Optional<PaymentScheduling> paymentSchedulingOptional = paymentSchedulingRepository.findById(id);

        //Verifica com o método isPresent se o ID informado existe na base de dados.
        if (!paymentSchedulingOptional.isPresent()) {
            throw new Exception("Não há nenhum agendamento de pagamento com o id fornecido.");
        }

        //Verifica se o agendamento de pagamento possui o status de "PAID";
        if (paymentSchedulingOptional.get().getStatus() == PaymentStatus.PAID) {
            throw new Exception("O agendamento não pôde ser deletado pois o pagamento já foi efetuado.");
        }

        //Verifica se o agendamento de pagamento possui a data anterior a data atual.
        if (paymentSchedulingOptional.get().getSchedulingDateTime().isBefore(LocalDateTime.now())) {
            throw new Exception("Pagamento já efetuado.");
        }

        //Caso passe nas verificações, o agendamento é deletado.
        paymentSchedulingRepository.deleteById(id);
    }

    public PaymentScheduling editPaymentScheduling(EditSchedulingRequest editSchedulingRequest) throws Exception {

        //Consulta no BD o id enviado
        Optional<PaymentScheduling> paymentSchedulingOptional = paymentSchedulingRepository.findById(editSchedulingRequest.getId());

        //Verifica com o método isPresent se o ID informado existe na base de dados.
        if (!paymentSchedulingOptional.isPresent()) {
            throw new Exception("Não há nenhum agendamento de pagamento com o id fornecido.");
        }

        //Verifica se o agendamento de pagamento possui o status de "PAID";
        if (paymentSchedulingOptional.get().getStatus() == PaymentStatus.PAID) {
            throw new Exception("O pagamento não pode ser editado pois o mesmo já foi pago.");
        }

        //Verifica se o agendamento de pagamento possui a data anterior a data atual.
        LocalDateTime newSchedulingDateTime = editSchedulingRequest.getSchedulingDate();
        if (newSchedulingDateTime.isBefore(LocalDateTime.now())) {
            throw new Exception("A data e/ou hora do reagendamento deve ser superior que a atual.");
        }

        //Caso passe nas verificações, a data enviada substitui a data anteriormente salva no BD.
        PaymentScheduling paymentScheduling = paymentSchedulingOptional.get();

        paymentScheduling.setSchedulingDateTime(editSchedulingRequest.getSchedulingDate());

        return paymentSchedulingRepository.save(paymentScheduling);

    }


}
