package org.nauka.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nauka.exception.tuitionFee.TuitionFeeNotFoundException;
import org.nauka.mapper.TuitionFeeMapper;
import org.nauka.model.SemesterTest;
import org.nauka.model.dao.PaymentStatus;
import org.nauka.model.dao.Semester;
import org.nauka.model.dao.Student;
import org.nauka.model.dao.TuitionFee;
import org.nauka.model.dto.TuitionFeeDto;
import org.nauka.repository.PaymentRepository;
import org.nauka.repository.TuitionFeeRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.nauka.model.student.StudentDaoTestData.adamKowalskiWithId;
import static org.nauka.model.tuitionFee.TuitionFeeDaoTest.setStatusNotPaidWhenNoPayments;
import static org.nauka.model.tuitionFee.TuitionFeeDaoTest.tuitionFeePaid;
import static org.nauka.model.tuitionFee.TuitionFeeDtoTest.tuitionFeePaidDto;
import static org.nauka.model.tuitionFee.TuitionFeeDtoTest.tuitionFeePendingDto;

@ExtendWith(MockitoExtension.class)
class TuitionFeeServiceTest implements SemesterTest {

    @Mock
    TuitionFeeRepository tuitionFeeRepository;

    @Mock
    PaymentRepository paymentRepository;
    @Mock
    StudentService studentService;
    @Mock
    SemesterService semesterService;

    @Mock
    TuitionFeeMapper tuitionFeeMapper;

    @InjectMocks
    TuitionFeeService tuitionFeeService;

    @AfterEach
    public void clearAll() {
        tuitionFeeRepository.clear();
    }

    @Test
    void shouldAddTuitionFeeToSemester() {
        Long idStudent = 1L;
        Student adam = adamKowalskiWithId();
        Semester semester = semesterSummer;
        BigDecimal amount = new BigDecimal("1000");

        when(studentService.getStudentById(idStudent)).thenReturn(adam);
        when(tuitionFeeRepository.save(any(TuitionFee.class))).thenReturn(tuitionFeePaid());
        when(semesterService.getSemesterById(semester.getSemesterId())).thenReturn(semester);
        when(tuitionFeeMapper.toDtoTuitionFeeDto(any())).thenReturn(tuitionFeePaidDto(), tuitionFeePendingDto());
        when(studentService.getStudentById(idStudent)).thenReturn(adam);

        TuitionFeeDto result = tuitionFeeService.addTuitionFees(adam.getIdStudent(), semester, amount);

        assertNotNull(result);
        assertEquals(amount, result.getAmount());
        assertEquals(PaymentStatus.NOT_PAID, result.getPaymentStatus());
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
    void shouldSetStatusPaidWhenPaidOnTime() {
        Long idTuitionFee = 1L;
        Long idPayment = 1L;
        BigDecimal amount = new BigDecimal(1000);
        TuitionFee tuitionFee = tuitionFeePaid();

        when(tuitionFeeRepository.findById(idTuitionFee)).thenReturn(Optional.of(tuitionFee));
        when(paymentRepository.sumPaymentsByTuitionFee(idPayment)).thenReturn(amount);

        tuitionFeeService.updateTuitionFeeStatus(idTuitionFee);

        assertThat(tuitionFee.getPaymentStatus()).isEqualTo(PaymentStatus.PAID);
        verify(tuitionFeeRepository).save(tuitionFee);
    }

    @Test
    void shouldSetStatusPaidWhenTotalPaidIsGreaterOrEqualToAmount() {
        Long idTuitionFee = 1L;
        Long idPayment = 1L;
        BigDecimal amount = new BigDecimal(1020);
        TuitionFee tuitionFee = tuitionFeePaid();

        when(tuitionFeeRepository.findById(idTuitionFee)).thenReturn(Optional.of(tuitionFee));
        when(paymentRepository.sumPaymentsByTuitionFee(idPayment)).thenReturn(amount);

        tuitionFeeService.updateTuitionFeeStatus(idTuitionFee);
        assertThat(tuitionFee.getPaymentStatus()).isEqualTo(PaymentStatus.PAID);
        verify(tuitionFeeRepository).save(tuitionFee);
    }

    @Test
    void shouldSetStatusNotPaidWhenNoPayments() {
        Long idTuitionFee = 1L;
        Long idPayment = 1L;
        BigDecimal amount = BigDecimal.ZERO;
        TuitionFee tuitionFee = setStatusNotPaidWhenNoPayments();

        when(tuitionFeeRepository.findById(idTuitionFee)).thenReturn(Optional.of(tuitionFee));
        when(paymentRepository.sumPaymentsByTuitionFee(idPayment)).thenReturn(amount);

        tuitionFeeService.updateTuitionFeeStatus(idTuitionFee);

        assertThat(tuitionFee.getPaymentStatus()).isEqualTo(PaymentStatus.NOT_PAID);
        verify(tuitionFeeRepository).save(tuitionFee);
    }

    @Test
    void shouldSetStatusPartialWhenSomeAmountPaidButLessThanDue() {
        Long idTuitionFee = 1L;
        Long idPayment = 1L;
        BigDecimal amount = new BigDecimal(120);
        TuitionFee tuitionFee = tuitionFeePaid();

        when(tuitionFeeRepository.findById(idTuitionFee)).thenReturn(Optional.of(tuitionFee));
        when(paymentRepository.sumPaymentsByTuitionFee(idPayment)).thenReturn(amount);

        tuitionFeeService.updateTuitionFeeStatus(idTuitionFee);
        assertThat(tuitionFee.getPaymentStatus()).isEqualTo(PaymentStatus.PARTIAL);
        verify(tuitionFeeRepository).save(tuitionFee);
    }

    @Test
    void shouldReturnWhenTuitionFeeByIdExists() {
        Long idTuition = 1L;
        when(tuitionFeeRepository.findById(idTuition)).thenReturn(Optional.of(tuitionFeePaid()));

        TuitionFee tuitionFeeById = tuitionFeeService.getTuitionFeeById(idTuition);

        assertEquals(idTuition, tuitionFeeById.getTuitionFeeId());
    }

    @Test
    void shouldThrowExceptionWhenStudentByIdNotFound() {
        Long idTuition = 1115L;

        TuitionFeeNotFoundException exception = assertThrows(TuitionFeeNotFoundException.class,
                () -> tuitionFeeService.getTuitionFeeById(idTuition));

        assertTrue(exception.getMessage().contains("TuitionFee with id: " + idTuition + " not found"));
        verify(tuitionFeeRepository).findById(idTuition);
    }
}
