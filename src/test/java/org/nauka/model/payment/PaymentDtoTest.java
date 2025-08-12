package org.nauka.model.payment;

import org.nauka.model.dto.PaymentDto;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.nauka.model.student.StudentDtoTestData.adamKowalskiDto;
import static org.nauka.model.student.StudentDtoTestData.alaKotDto;
import static org.nauka.model.tuitionFee.TuitionFeeDtoTest.tuitionFeePaidDto;
import static org.nauka.model.tuitionFee.TuitionFeeDtoTest.tuitionFeePendingDto;

public class PaymentDtoTest {
    public static PaymentDto paidOnTimeDto() {
        return new PaymentDto(adamKowalskiDto(), LocalDate.of(2025, 2, 28), new BigDecimal("1000"), tuitionFeePaidDto());
    }

    public static PaymentDto noPaidOnTimeDto() {
        return new PaymentDto(alaKotDto(), LocalDate.now().plusMonths(2), new BigDecimal(1000), tuitionFeePendingDto());
    }
}
