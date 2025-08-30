package org.nauka.repository;

import org.nauka.model.dao.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.tuitionFee.id = :tuitionFeeId")
    BigDecimal sumPaymentsByTuitionFee(Long idPayment);
}
