package org.nauka.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nauka.mapper.TuitionFeeMapper;
import org.nauka.model.SemesterTest;
import org.nauka.model.dao.PaymentStatus;
import org.nauka.model.dao.Semester;
import org.nauka.model.dao.Student;
import org.nauka.model.dao.TuitionFee;
import org.nauka.model.dto.TuitionFeeDto;
import org.nauka.repository.SemesterRepository;
import org.nauka.repository.StudentRepository;
import org.nauka.repository.TuitionFeeRepository;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.nauka.model.student.StudentDaoTestData.adamKowalskiWithId;
import static org.nauka.model.tuitionFee.TuitionFeeDtoTest.tuitionFeePaidDto;
import static org.nauka.model.tuitionFee.TuitionFeeDtoTest.tuitionFeePendingDto;

@ExtendWith(MockitoExtension.class)
class TuitionFeeServiceTest implements SemesterTest {

    @Mock
    TuitionFeeRepository tuitionFeeRepository;

    @Mock
    StudentRepository studentRepository;

    @Mock
    SemesterRepository semesterRepository;

    @Mock
    TuitionFeeMapper tuitionFeeMapper;

    @InjectMocks
    TuitionFeeService tuitionFeeService;

    @AfterEach
    public void clearAll(){
        studentRepository.clear();
        semesterRepository.clear();
    }

    @Test
    void shouldAddTuitionFeeToSemester() {
        Long idStudent = 1L;
        Student adam = adamKowalskiWithId();
        Semester semester = semesterSummer;
        BigDecimal amount = new BigDecimal("1000");

        doNothing().when(tuitionFeeRepository).save(any(TuitionFee.class));
        when(semesterRepository.findIdBySemester(1L)).thenReturn(semester);
        when(studentRepository.findById(idStudent)).thenReturn(adam);
        when(tuitionFeeMapper.toDtoTuitionFeeDto(any())).thenReturn(tuitionFeePaidDto(), tuitionFeePendingDto());

        Map<Long, TuitionFeeDto> result = tuitionFeeService.addTuitionFees(adam.getIdStudent(), semester, amount);

        assertFalse(result.isEmpty());
        assertTrue(result.containsKey(semester.getSemesterId()));

        TuitionFeeDto dto = result.get(semester.getSemesterId());
        assertNotNull(dto);
        assertEquals(amount, dto.getAmount());
        assertEquals(PaymentStatus.NOT_PAID, dto.getPaymentStatus());
    }

    //Jeśli amount == null powinno rzucić wyjątek
    @Test
    void shouldThrowExceptionWhenAmountIsNull() {
        Long idStudent = 1L;

        assertThrows(IllegalArgumentException.class, () ->
                tuitionFeeService.addTuitionFees(idStudent, semesterSummer, null)
        );
    }

    @Test
    void shouldThrowExceptionWhenAmountIsNegative() {
        Long idStudent = 1L;
        assertThrows(IllegalArgumentException.class, () ->
                tuitionFeeService.addTuitionFees(idStudent, semesterSummer, new BigDecimal("-1000"))
        );
    }

    @Test
    void shouldReturnEmptyListWhenNoSemestersProvided() {
        Long idStudent = 1L;
        BigDecimal amount = new BigDecimal("1000");

        // then
        assertThrows(IllegalArgumentException.class, () ->
                tuitionFeeService.addTuitionFees(1L, null, amount));
    }
}