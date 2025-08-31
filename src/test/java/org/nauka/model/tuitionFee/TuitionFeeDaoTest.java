package org.nauka.model.tuitionFee;

import org.nauka.model.dao.PaymentStatus;
import org.nauka.model.dao.TuitionFee;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.nauka.model.SemesterTest.semesterWinter;
import static org.nauka.model.semesterDirection.SemesterDirectionDaoTest.administration;
import static org.nauka.model.semesterDirection.SemesterDirectionDaoTest.computerScience;
import static org.nauka.model.student.StudentDaoTestData.adamKowalskiWithId;
import static org.nauka.model.student.StudentDaoTestData.alaKotWithId;

public class TuitionFeeDaoTest {

    public static TuitionFee tuitionFeePaid() {
        return new TuitionFee(1L, adamKowalskiWithId(), semesterWinter, computerScience(), new BigDecimal("1000"), LocalDate.of(2025, 2, 25), PaymentStatus.NOT_PAID);
    }

    public static TuitionFee setStatusNotPaidWhenNoPayments() {
        return new TuitionFee(1L, adamKowalskiWithId(), semesterWinter, computerScience(), new BigDecimal("1000"), LocalDate.of(2025, 2, 25), PaymentStatus.NOT_PAID);
    }

    public static TuitionFee tuitionFeeNoPaid() {
        return new TuitionFee(2L, alaKotWithId(), semesterWinter, administration(), new BigDecimal("1000"), LocalDate.of(2025, 2, 28), PaymentStatus.NOT_PAID);
    }

    public static TuitionFee tuitionFeeOverdue() {
        return new TuitionFee(3L, alaKotWithId(), semesterWinter, administration(), new BigDecimal(1000), LocalDate.of(2025, 3, 17), PaymentStatus.OVERDUE);
    }
}
