package org.nauka.service;

import lombok.AllArgsConstructor;
import org.nauka.mapper.StudentMapper;
import org.nauka.mapper.TuitionFeeMapper;
import org.nauka.model.dao.PaymentStatus;
import org.nauka.model.dao.Semester;
import org.nauka.model.dao.Student;
import org.nauka.model.dao.TuitionFee;
import org.nauka.model.dto.StudentDto;
import org.nauka.model.dto.TuitionFeeDto;
import org.nauka.repository.StudentRepository;
import org.nauka.repository.TuitionFeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.nauka.model.dao.TuitionFee.createTuition;

@AllArgsConstructor
@Service
public class TuitionFeeService {
    private TuitionFeeRepository tuitionFeeRepository;
    private StudentRepository studentRepository;
    private TuitionFeeMapper tuitionFeeMapper;
    private StudentMapper studentMapper;

    public List<TuitionFeeDto> assignTuitionToSemesters(StudentDto studentDto, List<Semester> semesters, BigDecimal amount) {
        List<TuitionFeeDto> assigned = new ArrayList<>();
        Student student = studentMapper.toDao(studentDto, studentRepository.generateId());
        for (Semester semester : semesters) {
            TuitionFee dao = createTuition(
                    student,
                    semester,
                    amount,
                    semester.getEndDate().minusWeeks(2),
                    PaymentStatus.OVERDUE
            );
            tuitionFeeRepository.save(dao);
            TuitionFeeDto dto = tuitionFeeMapper.toDtoTuitionFeeDto(dao);
            assigned.add(dto);
        }
        return assigned;
    }
}
