package org.nauka.model.payment;

import org.nauka.model.dto.PaymentDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentDtoTest {
    public static PaymentDto paidOnTimeDto() {
        return new PaymentDto(2L, LocalDate.of(2025, 2, 28), new BigDecimal("1000"), 2L);
    }

    public static PaymentDto notPaidWithinFourteenDaysButWithoutInterestDto() {
        return new PaymentDto(2L, LocalDate.of(2025, 3, 10), new BigDecimal(1000), 3L);
    }

    public static PaymentDto paymentAfterTheFinalPaymentDateWithInterestChargedDto() {
        return new PaymentDto(2L, LocalDate.of(2025, 3, 17), new BigDecimal(1000), 4L);
    }
}
