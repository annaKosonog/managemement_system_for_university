package org.nauka.repository;

import org.nauka.model.dao.Payment;

import java.math.BigDecimal;
import java.util.*;

public class PaymentRepositoryImpl implements PaymentRepository {
    private final Map<Long, List<Payment>> paymentsByTuitionFee = new HashMap<>();

    private long actualId = 1;


    @Override
    public void savePayment(Payment payment) {
        Long tuitionFeeId = payment.getTuitionFee().getTuitionFeeId();
        paymentsByTuitionFee
                .computeIfAbsent(tuitionFeeId, id -> new ArrayList<>())
                .add(payment);
    }

    @Override
    public Long generateId() {
        return actualId++;
    }

    @Override
    public BigDecimal sumPaymentsByTuitionFee(Long idTuition) {
        return paymentsByTuitionFee
                .getOrDefault(idTuition, List.of())
                .stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Payment findByIdPayment(Long idPayment) {
       return paymentsByTuitionFee.values()
                .stream()
                .flatMap(List::stream)
                .filter(payment -> payment.getPaymentId().equals(idPayment))
                .findFirst()
                .orElseThrow();
    }
}
