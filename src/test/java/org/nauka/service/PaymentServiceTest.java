package org.nauka.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nauka.mapper.PaymentMapper;
import org.nauka.model.dao.Payment;
import org.nauka.model.dao.TuitionFee;
import org.nauka.model.dto.PaymentDto;
import org.nauka.repository.PaymentRepository;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.nauka.model.payment.PaymentDtoTest.paidOnTimeDto;
import static org.nauka.model.student.StudentDaoTestData.adamKowalskiWithId;
import static org.nauka.model.tuitionFee.TuitionFeeDaoTest.tuitionFeePaid;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    PaymentMapper paymentMapper;

    @Mock
    StudentService studentService;

    @Mock
    FinanceService financeService;

    @Mock
    TuitionFeeService tuitionFeeService;

    @Mock
    Clock clock;

    @InjectMocks
    PaymentService paymentService;

    @Test
    void shouldReturnCorrectRegisterPayment() {
        Long feeId = 1L;
        Long studentId = 1L;
        BigDecimal amountPaid = BigDecimal.valueOf(1000);

        TuitionFee tuitionFee = tuitionFeePaid();
        PaymentDto expectedDto = paidOnTimeDto();

        when(tuitionFeeService.getTuitionFeeById(feeId)).thenReturn(tuitionFee);
        when(studentService.getStudentById(studentId)).thenReturn(adamKowalskiWithId());
        when(financeService.calculateTotalAmount(any(Payment.class)))
                .thenReturn(BigDecimal.valueOf(120));
        when(clock.instant()).thenReturn(Instant.parse("2025-02-25T10:00:00Z"));
        when(clock.getZone()).thenReturn(ZoneId.systemDefault());
        when(paymentMapper.toPaymentDto(any(Payment.class))).thenReturn(expectedDto);

        PaymentDto result = paymentService.registerPayment(studentId, feeId, amountPaid);

        assertThat(result).isEqualTo(expectedDto);
        verify(paymentRepository).save(any(Payment.class));
        verify(tuitionFeeService).updateTuitionFeeStatus(tuitionFee.getTuitionFeeId());
    }

    @Test
    void shouldCreatePaymentWithCorrectFields() {
        Long feeId = 1L;
        Long studentId = 1L;
        BigDecimal amountPaid = BigDecimal.valueOf(1000);

        TuitionFee tuitionFee = tuitionFeePaid();
        PaymentDto expectedDto = paidOnTimeDto();

        when(tuitionFeeService.getTuitionFeeById(feeId)).thenReturn(tuitionFee);
        when(studentService.getStudentById(studentId)).thenReturn(adamKowalskiWithId());
        when(financeService.calculateTotalAmount(any(Payment.class)))
                .thenReturn(BigDecimal.valueOf(1000));

        when(clock.instant()).thenReturn(Instant.parse("2025-02-25T10:00:00Z"));
        when(clock.getZone()).thenReturn(ZoneId.systemDefault());
        when(paymentMapper.toPaymentDto(any(Payment.class))).thenReturn(expectedDto);

        PaymentDto result = paymentService.registerPayment(studentId, feeId, amountPaid);

        ArgumentCaptor<Payment> captor = ArgumentCaptor.forClass(Payment.class);
        verify(financeService).calculateTotalAmount(captor.capture());
        Payment passedPayment = captor.getValue();
        assertThat(passedPayment.getAmount()).isEqualTo(BigDecimal.valueOf(1000));
        assertThat(result.getAmount()).isEqualTo(amountPaid);
    }
}
