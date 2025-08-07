package org.nauka.model.tuitionFee;

import org.nauka.model.dao.PaymentStatus;
import org.nauka.model.dto.TuitionFeeDto;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.nauka.model.SemesterTest.semesterSummer;
import static org.nauka.model.student.StudentDtoTestData.adamKowalskiDto;
import static org.nauka.model.student.StudentDtoTestData.alaKotDto;

public class TuitionFeeDtoTest {
    public static TuitionFeeDto tuitionFeePaidDto(){
        return new TuitionFeeDto(adamKowalskiDto(), semesterSummer, new BigDecimal("1000"), LocalDate.of(2025,2,25), PaymentStatus.NOT_PAID);
    }

    public static TuitionFeeDto tuitionFeePendingDto(){
        return new TuitionFeeDto(alaKotDto(), semesterSummer, new BigDecimal("2900"), LocalDate.of(2025,9,29), PaymentStatus.PENDING);
    }
}
