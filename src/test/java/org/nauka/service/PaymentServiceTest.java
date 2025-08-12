package org.nauka.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nauka.mapper.PaymentMapper;
import org.nauka.model.dao.Payment;
import org.nauka.model.dao.TuitionFee;
import org.nauka.model.dto.PaymentDto;
import org.nauka.repository.PaymentRepository;
import org.nauka.repository.StudentRepository;
import org.nauka.repository.TuitionFeeRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.nauka.model.payment.PaymentDtoTest.paidOnTimeDto;
import static org.nauka.model.tuitionFee.TuitionFeeDaoTest.tuitionFeePaid;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    PaymentMapper paymentMapper;

    @Mock
    StudentRepository studentRepository;

    @Mock
    TuitionFeeRepository tuitionFeeRepository;

    @InjectMocks
    PaymentService paymentService;


    @Test
    void shouldReturnCorrectRegisterPayment() {
        Long feeId = 1L;
        Long studentId = 1L;
        BigDecimal amountPaid = BigDecimal.valueOf(1000);

        TuitionFee tuitionFee = tuitionFeePaid();
        PaymentDto expectedDto = paidOnTimeDto();

        when(tuitionFeeRepository.findById(feeId)).thenReturn(tuitionFee);
        when(paymentRepository.sumPaymentsByTuitionFee(feeId)).thenReturn(BigDecimal.valueOf(100));
        doNothing().when(paymentRepository).savePayment(any());
        when(paymentMapper.toPaymentDto(any(Payment.class))).thenReturn(expectedDto);


        PaymentDto result = paymentService.registerPayment(studentId, feeId, amountPaid);

        verify(paymentRepository).savePayment(any());
        assertEquals(expectedDto, result);
    }
}
