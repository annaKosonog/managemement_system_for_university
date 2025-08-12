package org.nauka.service;

import lombok.RequiredArgsConstructor;
import org.nauka.mapper.PaymentMapper;
import org.nauka.model.dao.Payment;
import org.nauka.model.dao.PaymentStatus;
import org.nauka.model.dao.Student;
import org.nauka.model.dao.TuitionFee;
import org.nauka.model.dto.PaymentDto;
import org.nauka.repository.PaymentRepository;
import org.nauka.repository.StudentRepository;
import org.nauka.repository.TuitionFeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final StudentRepository studentRepository;
    private final TuitionFeeRepository tuitionFeeRepository;

    public PaymentDto registerPayment(Long idStudent, Long idTuitionFee, BigDecimal amount) {
        TuitionFee tuitionFee = tuitionFeeRepository.findById(idTuitionFee);
        Student student = studentRepository.findById(idStudent);

        //Utworzenie i zapisanie wpłaty
        Payment payment = new Payment(
                paymentRepository.generateId(),
                student,
                LocalDate.now(),
                amount,
                tuitionFee
        );
        updateTuitionFeeStatus(idTuitionFee);
        paymentRepository.savePayment(payment);
        return paymentMapper.toPaymentDto(payment);
    }

    private void updateTuitionFeeStatus(Long idTuitionFee) {
        TuitionFee tuitionFee = tuitionFeeRepository.findById(idTuitionFee);

        BigDecimal totalPaid = paymentRepository.sumPaymentsByTuitionFee(idTuitionFee);

        if (totalPaid.compareTo(tuitionFee.getAmount()) == 0) {
            tuitionFee.setPaymentStatus(PaymentStatus.PAID);
        } else if (totalPaid.compareTo(tuitionFee.getAmount()) > 0) {
            tuitionFee.setPaymentStatus(PaymentStatus.PARTIAL);
        } else {
            tuitionFee.setPaymentStatus(PaymentStatus.NOT_PAID);
        }
        tuitionFeeRepository.save(tuitionFee);
    }


}
