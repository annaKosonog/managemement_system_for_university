package org.nauka.service;

import lombok.RequiredArgsConstructor;
import org.nauka.mapper.TuitionFeeMapper;
import org.nauka.model.dao.PaymentStatus;
import org.nauka.model.dao.Semester;
import org.nauka.model.dao.TuitionFee;
import org.nauka.model.dto.TuitionFeeDto;
import org.nauka.repository.PaymentRepository;
import org.nauka.repository.SemesterRepository;
import org.nauka.repository.StudentRepository;
import org.nauka.repository.TuitionFeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class TuitionFeeService {
    private final TuitionFeeRepository tuitionFeeRepository;
    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final SemesterRepository semesterRepository;
    private final TuitionFeeMapper tuitionFeeMapper;

    public Map<Long, TuitionFeeDto> addTuitionFees(Long idStudent, Semester idSemester, BigDecimal amount) {
        validateAmount(amount);

        Map<Long, TuitionFeeDto> tuitionFees = new HashMap<>();
        TuitionFee entity = TuitionFee.of(
                studentRepository.findById(idStudent).getIdStudent(),
                semesterRepository.findIdBySemester(idSemester.getSemesterId()),
                amount,
                PaymentStatus.NOT_PAID
        );
        tuitionFeeRepository.save(entity);
        TuitionFeeDto tuitionFeeDto = tuitionFeeMapper.toDtoTuitionFeeDto(entity);
        tuitionFees.put(idSemester.getSemesterId(), tuitionFeeDto);
        return tuitionFees;
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be positive and not null");
        }
    }

    public void updateTuitionFeeStatus(Long idTuitionFee) {
        TuitionFee tuitionFee = tuitionFeeRepository.findById(idTuitionFee);

        // Suma wszystkich Payment.amount powiązanych z tą opłatą, wliczając odsetki
        BigDecimal totalPaid = paymentRepository.sumPaymentsByTuitionFee(idTuitionFee);
        BigDecimal amountDue = tuitionFee.getAmount();

        if (totalPaid.compareTo(BigDecimal.ZERO) == 0) {
            tuitionFee.setPaymentStatus(PaymentStatus.NOT_PAID);
        } else if (totalPaid.compareTo(amountDue) >= 0) {
            tuitionFee.setPaymentStatus(PaymentStatus.PAID);
        } else {
            tuitionFee.setPaymentStatus(PaymentStatus.PARTIAL);
        }
        tuitionFeeRepository.save(tuitionFee);
    }
}
