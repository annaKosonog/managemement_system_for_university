package org.nauka.service;

import lombok.RequiredArgsConstructor;
import org.nauka.mapper.PaymentMapper;
import org.nauka.model.dao.Payment;
import org.nauka.model.dao.Student;
import org.nauka.model.dao.TuitionFee;
import org.nauka.model.dto.PaymentDto;
import org.nauka.repository.PaymentRepository;
import org.nauka.repository.StudentRepository;
import org.nauka.repository.TuitionFeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final StudentRepository studentRepository;
    private final TuitionFeeRepository tuitionFeeRepository;
    private final FinanceService financeService;
    private final TuitionFeeService tuitionFeeService;
    private final Clock clock;

    public PaymentDto registerPayment(Long idStudent, Long idTuitionFee, BigDecimal amount) {
        Payment payment = createPayment(idStudent, idTuitionFee, amount);
        BigDecimal totalAmount = financeService.calculateTotalAmount(payment);
        payment.setAmount(totalAmount);

        paymentRepository.savePayment(payment);
        tuitionFeeService.updateTuitionFeeStatus(idTuitionFee);
        return paymentMapper.toPaymentDto(payment);
    }

    private Payment createPayment(Long idStudent, Long idTuitionFee, BigDecimal amount) {
        TuitionFee tuitionFee = tuitionFeeRepository.findById(idTuitionFee);
        Student student = studentRepository.findById(idStudent);

        //Utworzenie i zapisanie wpłaty
        return new Payment(
                paymentRepository.generateId(),
                student,
                LocalDate.now(clock),
                amount,
                tuitionFee
        );
    }
}
