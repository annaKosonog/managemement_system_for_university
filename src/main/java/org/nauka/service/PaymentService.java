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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final StudentRepository studentRepository;
    private final TuitionFeeRepository tuitionFeeRepository;
    private final Clock clock;

    @Value("${final.date.payment}")
    private int finalDatePayment;

    public PaymentDto registerPayment(Long idStudent, Long idTuitionFee, BigDecimal amount) {
        Payment makingAPayment = createPayment(idStudent, idTuitionFee, amount);

        BigDecimal tuitionFees = totalAmountOfTuitionFees(makingAPayment.getPaymentId(), idTuitionFee);
        updateTuitionFeeStatus(idTuitionFee);
        makingAPayment.setAmount(tuitionFees);
        return paymentMapper.toPaymentDto(makingAPayment);
    }

    private Payment createPayment(Long idStudent, Long idTuitionFee, BigDecimal amount) {
        TuitionFee tuitionFee = tuitionFeeRepository.findById(idTuitionFee);
        Student student = studentRepository.findById(idStudent);

        //Utworzenie i zapisanie wpłaty
        Payment payment = new Payment(
                paymentRepository.generateId(),
                student,
                LocalDate.now(clock),
                amount,
                tuitionFee
        );
        paymentRepository.savePayment(payment);
        return payment;
    }

    private void updateTuitionFeeStatus(Long idTuitionFee) {
        TuitionFee tuitionFee = tuitionFeeRepository.findById(idTuitionFee);
        BigDecimal totalPaid = paymentRepository.sumPaymentsByTuitionFee(idTuitionFee);
        BigDecimal amount = tuitionFee.getAmount();

        if (totalPaid.compareTo(amount) == 0) {
            tuitionFee.setPaymentStatus(PaymentStatus.PAID);
        } else if (totalPaid.compareTo(amount) > 0) {
            tuitionFee.setPaymentStatus(PaymentStatus.PARTIAL);
        } else if (totalPaid.compareTo(BigDecimal.ZERO) == 0) {
            tuitionFee.setPaymentStatus(PaymentStatus.NOT_PAID);
        }
        tuitionFeeRepository.save(tuitionFee);
    }

    public BigDecimal totalAmountOfTuitionFees(Long idPayment, Long idTuitionFee) {
        Payment payment = paymentRepository.findByIdPayment(idPayment);
        BigDecimal areas = calculateOfArrearsForTheSemester(idPayment, idTuitionFee);
        if (areas.compareTo(BigDecimal.valueOf(-1)) == 0) {
            return payment.getAmount();
        }
        return payment.getAmount().add(areas);
    }


    private BigDecimal calculateOfArrearsForTheSemester(Long idPayment, Long idTuitionFee) {
        Payment payment = paymentRepository.findByIdPayment(idPayment);
        if (checkIfPaymentWasMadeOnTime(idPayment)) {
            return payment.getAmount();
        } else if (checkIfPaymentWasMadeWithinExtendedDeadline(idPayment)) {
            long dayOverdue = totalNumberOfDaysOverdue(idTuitionFee);
            return new BigDecimal(2).multiply(BigDecimal.valueOf(dayOverdue));
        } else {
            return new BigDecimal(-1);
        }
    }

    private boolean checkIfPaymentWasMadeOnTime(Long idPayment) {
        Payment payment = paymentRepository.findByIdPayment(idPayment);
        LocalDate deadLine = payment.getTuitionFee().getSemester().getEndDate().plusWeeks(2);
        LocalDate paymentDate = payment.getPaymentDate();
        return !paymentDate.isAfter(deadLine);
    }

    private boolean checkIfPaymentWasMadeWithinExtendedDeadline(Long idPayment) {
        Payment payment = paymentRepository.findByIdPayment(idPayment);
        LocalDate finaDeadline = payment.getTuitionFee().getSemester().getEndDate().plusWeeks(2).plusDays(finalDatePayment);
        LocalDate paymentDate = payment.getPaymentDate();
        return !paymentDate.isAfter(finaDeadline);
    }

    private long totalNumberOfDaysOverdue(Long idPayment) {
        Payment payment = paymentRepository.findByIdPayment(idPayment);
        LocalDate paymentDate = payment.getPaymentDate();
        LocalDate finaDeadline = payment.getTuitionFee().getSemester().getEndDate().plusWeeks(2);
        return ChronoUnit.DAYS.between(finaDeadline, paymentDate);

    }
}
