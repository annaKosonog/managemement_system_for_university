package org.nauka.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.nauka.model.payment.PaymentDaoTest.*;

@ExtendWith(MockitoExtension.class)
class FinanceServiceTest {

    @InjectMocks
    FinanceService financeService;

    @Test
    void shouldReturnAmountWithoutInterestBecauseTheyWerePaidOnTheTime() {
        BigDecimal totalAmount = financeService.calculateTotalAmount(paidOnTime());
        assertEquals(new BigDecimal("1000"), totalAmount);
    }

    @Test
    void shouldReturnAmountWithoutInterestDespitePaymentAfterTheDeadlineButWithinFourteenDays() {
        BigDecimal result = financeService.calculateTotalAmount(notPaidWithinFourteenDaysButWithoutInterest());
        assertEquals(new BigDecimal(1000), result);
    }

    @Test
    void shouldReturnAmountWithInterestBecauseTheyWerePaidAfterFourteenDays() {
        ReflectionTestUtils.setField(financeService, "finalDatePayment", 7);
        BigDecimal result = financeService.calculateTotalAmount(paymentAfterTheFinalPaymentDateWithInterestCharged());
        assertEquals(BigDecimal.valueOf(1006), result);
    }

    @Test
    void shouldReturnAmountBecauseItWasNotPaidByTheDeadline() {
        ReflectionTestUtils.setField(financeService, "finalDatePayment", 7);
        BigDecimal result = financeService.calculateTotalAmount(noPaidAfterTheFinalPaymentDate());
        assertEquals(BigDecimal.valueOf(1000), result);
    }
}
