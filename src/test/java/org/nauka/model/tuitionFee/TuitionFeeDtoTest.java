package org.nauka.model.tuitionFee;

import org.nauka.model.dao.PaymentStatus;
import org.nauka.model.dto.TuitionFeeDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TuitionFeeDtoTest {
    public static TuitionFeeDto tuitionFeePaidDto() {
        return new TuitionFeeDto(1L, 1L, 1L, 2L, new BigDecimal("1000"), LocalDate.of(2025, 2, 25), PaymentStatus.NOT_PAID);
    }

    public static TuitionFeeDto changeStatusAndAmountDto() {
        return new TuitionFeeDto(2L, 1L, 2L, 2L, new BigDecimal("1000"), LocalDate.of(2025, 2, 25), PaymentStatus.PAID);
    }

    public static TuitionFeeDto tuitionFeePendingDto() {
        return new TuitionFeeDto(3L, 2L, 1L, 1L, new BigDecimal("1000"), LocalDate.of(2025, 2, 28), PaymentStatus.PENDING);
    }

    public static TuitionFeeDto tuitionFeeOverDueDto() {
        return new TuitionFeeDto(4L, 2L, 1L, 2L, new BigDecimal("1000"), LocalDate.of(2025, 3, 17), PaymentStatus.OVERDUE);
    }
}
