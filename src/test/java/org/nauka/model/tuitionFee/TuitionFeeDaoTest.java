package org.nauka.model.tuitionFee;

import org.nauka.model.dao.PaymentStatus;
import org.nauka.model.dao.TuitionFee;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.nauka.model.SemesterTest.semesterSummer;
import static org.nauka.model.SemesterTest.semesterWinter;
import static org.nauka.model.student.StudentDaoTestData.adamKowalskiWithId;
import static org.nauka.model.student.StudentDaoTestData.alaKot;

public class TuitionFeeDaoTest {

public static TuitionFee tuitionFeePaid(){
    return new TuitionFee(1L, adamKowalskiWithId(), semesterSummer, new BigDecimal("2900"), LocalDate.of(2025,2,25), PaymentStatus.PAID);
}

public static TuitionFee tuitionFeePending(){
    return new TuitionFee(2L, alaKot(), semesterWinter, new BigDecimal("2900"), LocalDate.of(2025,9,29), PaymentStatus.PENDING);
}
}
