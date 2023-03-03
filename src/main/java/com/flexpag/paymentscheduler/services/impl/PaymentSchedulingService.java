package com.flexpag.paymentscheduler.services.impl;

import com.flexpag.paymentscheduler.controllers.requests.CreateSchedulingRequest;
import com.flexpag.paymentscheduler.controllers.requests.EditSchedulingRequest;
import com.flexpag.paymentscheduler.models.PaymentScheduling;
import com.flexpag.paymentscheduler.repositories.PaymentSchedulingRepository;
import com.flexpag.paymentscheduler.services.PaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentSchedulingService implements PaymentScheduleService {

    @Autowired
    private final PaymentSchedulingRepository paymentSchedulingRepository;

    public PaymentSchedulingService(PaymentSchedulingRepository paymentSchedulingRepository) {
        this.paymentSchedulingRepository = paymentSchedulingRepository;
    }

    public PaymentScheduling createPaymentSchedule(CreateSchedulingRequest createSchedulingRequest) throws Exception {

        if(createSchedulingRequest.getPaymentAmount() <= 0){
            throw new Exception("O valor do pagamento deve ser maior que zero.");
        }

        if(createSchedulingRequest.getSchedulingDate().isBefore(LocalDateTime.now())){
            throw new Exception("A data do pagamento deve ser maior que a data de hoje.");
        }

        PaymentScheduling paymentScheduling = new PaymentScheduling(createSchedulingRequest);
        return paymentSchedulingRepository.save(paymentScheduling);
    }

    public void deleteSchedule(Long id) throws Exception {

        Optional<PaymentScheduling> paymentSchedulingOptional = paymentSchedulingRepository.findById(id);

        if (!paymentSchedulingOptional.isPresent()) {
            throw new Exception("Não há nenhum agendamento de pagamento com o id fornecido.");
        }

        if(paymentSchedulingOptional.get().getStatus()){
            throw new Exception("O agendamento não pôde ser deletado pois o pagamento já foi efetuado.");
        }

        if(paymentSchedulingOptional.get().getSchedulingDateTime().isBefore(LocalDateTime.now())){
            throw new Exception("Pagamento já efetuado.");
        }

        paymentSchedulingRepository.deleteById(id);
    }

    public PaymentScheduling editPaymentSchedule(EditSchedulingRequest editSchedulingRequest) throws Exception {

        Optional<PaymentScheduling> paymentSchedulingOptional = paymentSchedulingRepository.findById(editSchedulingRequest.getId());

        if(!paymentSchedulingOptional.isPresent()){
            throw new Exception("Não há nenhum agendamento de pagamento com o id fornecido.");
        }

        if(paymentSchedulingOptional.get().getStatus()){
            throw new Exception("O pagamento não pode ser editado pois o mesmo já foi pago.");
        }

        LocalDateTime newLocalDateTime = editSchedulingRequest.getSchedulingDate();

        if(newLocalDateTime.isBefore(LocalDateTime.now())){
            throw new Exception("A data e/ou hora do reagendamento deve ser superior que a atual.");
        }

        PaymentScheduling paymentScheduling = paymentSchedulingOptional.get();

        paymentScheduling.setSchedulingDateTime(editSchedulingRequest.getSchedulingDate());

        return paymentSchedulingRepository.save(paymentScheduling);

    }


}
