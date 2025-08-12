package org.nauka.model.payment;

import org.nauka.model.dao.Payment;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.nauka.model.student.StudentDaoTestData.adamKowalskiWithId;
import static org.nauka.model.student.StudentDaoTestData.alaKot;
import static org.nauka.model.tuitionFee.TuitionFeeDaoTest.tuitionFeePaid;
import static org.nauka.model.tuitionFee.TuitionFeeDaoTest.tuitionFeePending;

public class PaymentDaoTest {
    public static Payment paidOnTime() {
        return new Payment(1L, adamKowalskiWithId(), LocalDate.of(2025, 2, 28), new BigDecimal("1000"), tuitionFeePaid());
    }

    public static Payment noPaidOnTime() {
        return new Payment(2L, alaKot(), LocalDate.now().plusMonths(2), new BigDecimal(1000), tuitionFeePending());
    }
}
