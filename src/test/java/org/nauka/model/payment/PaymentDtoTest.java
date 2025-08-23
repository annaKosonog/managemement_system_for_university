package org.nauka.model.payment;

import org.nauka.model.dto.PaymentDto;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.nauka.model.student.StudentDtoTestData.adamKowalskiDto;
import static org.nauka.model.student.StudentDtoTestData.alaKotDto;
import static org.nauka.model.tuitionFee.TuitionFeeDtoTest.*;

public class PaymentDtoTest {
    public static PaymentDto paidOnTimeDto() {
        return new PaymentDto(adamKowalskiDto(), LocalDate.of(2025, 2, 28), new BigDecimal("1000"), changeStatusAndAmountDto());
    }

    public static PaymentDto notPaidWithinFourteenDaysButWithoutInterestDto() {
        return new PaymentDto(alaKotDto(), LocalDate.of(2025, 3, 10), new BigDecimal(1000), tuitionFeePendingDto());
    }

    public static PaymentDto paymentAfterTheFinalPaymentDateWithInterestChargedDto() {
        return new PaymentDto(alaKotDto(), LocalDate.of(2025, 3, 17), new BigDecimal(1000), tuitionFeeOverDueDto());
    }
}
