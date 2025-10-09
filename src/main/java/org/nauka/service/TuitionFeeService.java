package org.nauka.service;

import lombok.RequiredArgsConstructor;
import org.nauka.exception.handler.service.AppException;
import org.nauka.exception.handler.service.ErrorType;
import org.nauka.mapper.StudentMapper;
import org.nauka.mapper.TuitionFeeMapper;
import org.nauka.model.dao.PaymentStatus;
import org.nauka.model.dao.TuitionFee;
import org.nauka.model.dto.StudentDto;
import org.nauka.model.dto.TuitionFeeDto;
import org.nauka.repository.PaymentRepository;
import org.nauka.repository.SemesterRepository;
import org.nauka.repository.TuitionFeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class TuitionFeeService {
    private final TuitionFeeRepository tuitionFeeRepository;
    private final PaymentRepository paymentRepository;
    private final StudentService studentService;
    private final SemesterRepository semesterRepository;
    private final TuitionFeeMapper tuitionFeeMapper;
    private final StudentMapper studentMapper;

    public TuitionFeeDto addTuitionFees(Long indexNumber, Long idSemester, BigDecimal amount) {
        validateAmount(amount);
        StudentDto dto = studentService.getStudentByIndexNumber(indexNumber);

        TuitionFee entity = TuitionFee.of(
                studentMapper.toEntity(dto),
                semesterRepository.findSemesterBySemesterId(idSemester),
                amount,
                PaymentStatus.NOT_PAID
        );
        tuitionFeeRepository.save(entity);
        return tuitionFeeMapper.toDto(entity);
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
                .orElseThrow(() -> new AppException("TuitionFee", id, ErrorType.NOT_FOUND));
    }
}