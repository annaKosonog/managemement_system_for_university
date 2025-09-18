package org.nauka.service;

import lombok.RequiredArgsConstructor;
import org.nauka.exception.tuitionFee.TuitionFeeNotFoundException;
import org.nauka.mapper.TuitionFeeMapper;
import org.nauka.model.dao.PaymentStatus;
import org.nauka.model.dao.Semester;
import org.nauka.model.dao.TuitionFee;
import org.nauka.model.dto.TuitionFeeDto;
import org.nauka.repository.PaymentRepository;
import org.nauka.repository.TuitionFeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class TuitionFeeService {
    private final TuitionFeeRepository tuitionFeeRepository;
    private final PaymentRepository paymentRepository;
    private final StudentService studentService;
    private final SemesterService semesterService;
    private final TuitionFeeMapper tuitionFeeMapper;

    public TuitionFeeDto addTuitionFees(Long idStudent, Semester idSemester, BigDecimal amount) {
        validateAmount(amount);

        TuitionFee entity = TuitionFee.of(
                studentService.getStudentById(idStudent),
                semesterService.getSemesterById(idSemester.getSemesterId()),
                amount,
                PaymentStatus.NOT_PAID
        );
        tuitionFeeRepository.save(entity);
        return tuitionFeeMapper.toDtoTuitionFeeDto(entity);
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be positive and not null");
        }
    }

    public void updateTuitionFeeStatus(Long idTuitionFee) {
        TuitionFee tuitionFeeById = getTuitionFeeById(idTuitionFee);

        // Suma wszystkich Payment.amount powiązanych z tą opłatą, wliczając odsetki
        BigDecimal totalPaid = paymentRepository.sumPaymentsByTuitionFee(idTuitionFee);
        BigDecimal amountDue = tuitionFeeById.getAmount();

        if (totalPaid.compareTo(BigDecimal.ZERO) == 0) {
            tuitionFeeById.setPaymentStatus(PaymentStatus.NOT_PAID);
        } else if (totalPaid.compareTo(amountDue) >= 0) {
            tuitionFeeById.setPaymentStatus(PaymentStatus.PAID);
        } else {
            tuitionFeeById.setPaymentStatus(PaymentStatus.PARTIAL);
        }
        tuitionFeeRepository.save(tuitionFeeById);
    }

    public TuitionFee getTuitionFeeById(Long id) {
        return tuitionFeeRepository.findById(id)
                .orElseThrow(() -> new TuitionFeeNotFoundException(id));
    }
}