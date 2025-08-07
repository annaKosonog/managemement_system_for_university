package org.nauka.service;

import lombok.AllArgsConstructor;
import org.nauka.mapper.TuitionFeeMapper;
import org.nauka.model.dao.PaymentStatus;
import org.nauka.model.dao.Semester;
import org.nauka.model.dao.TuitionFee;
import org.nauka.model.dto.TuitionFeeDto;
import org.nauka.repository.SemesterRepository;
import org.nauka.repository.StudentRepository;
import org.nauka.repository.TuitionFeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class TuitionFeeService {
    private TuitionFeeRepository tuitionFeeRepository;
    private StudentRepository studentRepository;
    private SemesterRepository semesterRepository;
    private TuitionFeeMapper tuitionFeeMapper;

    public Map<Long, TuitionFeeDto> addTuitionFees(Long idStudent, Semester idSemester, BigDecimal amount) {
        validTuitionFee(amount);

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

    private static void validTuitionFee(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
}
