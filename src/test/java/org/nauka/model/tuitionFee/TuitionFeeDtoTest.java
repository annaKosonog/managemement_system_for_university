package org.nauka.model.tuitionFee;

import org.nauka.model.dao.PaymentStatus;
import org.nauka.model.dto.TuitionFeeDto;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.nauka.model.SemesterTest.semesterSummer;
import static org.nauka.model.SemesterTest.semesterWinter;
import static org.nauka.model.semesterDirection.SemesterDirectionDaoTest.administration;
import static org.nauka.model.semesterDirection.SemesterDirectionDaoTest.computerScience;
import static org.nauka.model.student.StudentDtoTestData.adamKowalskiDto;
import static org.nauka.model.student.StudentDtoTestData.alaKotDto;

public class TuitionFeeDtoTest {
    public static TuitionFeeDto tuitionFeePaidDto() {
        return new TuitionFeeDto(adamKowalskiDto(), semesterSummer, computerScience(), new BigDecimal("1000"), LocalDate.of(2025, 2, 25), PaymentStatus.NOT_PAID);
    }

    public static TuitionFeeDto changeStatusAndAmountDto() {
        return new TuitionFeeDto(adamKowalskiDto(), semesterWinter, computerScience(), new BigDecimal("1000"), LocalDate.of(2025, 2, 25), PaymentStatus.PAID);
    }

    public static TuitionFeeDto tuitionFeePendingDto() {
        return new TuitionFeeDto(alaKotDto(), semesterSummer, administration(), new BigDecimal("1000"), LocalDate.of(2025, 2, 28), PaymentStatus.PENDING);
    }

    public static TuitionFeeDto tuitionFeeOverDueDto() {
        return new TuitionFeeDto(alaKotDto(), semesterSummer, administration(), new BigDecimal("1000"), LocalDate.of(2025, 3, 17), PaymentStatus.OVERDUE);
    }
}
