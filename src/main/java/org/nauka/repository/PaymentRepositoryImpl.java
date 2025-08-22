package org.nauka.repository;

import org.nauka.model.dao.Payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentRepositoryImpl implements PaymentRepository {
    private final Map<Long, List<Payment>> paymentsByTuitionFee = new HashMap<>();

    private long actualId = 1;


    @Override
    public void savePayment(Payment payment) {
        Long tuitionFeeId = payment.getTuitionFee().getTuitionFeeId();
        paymentsByTuitionFee
                .computeIfAbsent(tuitionFeeId, id -> new ArrayList<>())
                .removeIf(p -> p.getPaymentId().equals(payment.getPaymentId()));

        paymentsByTuitionFee.get(tuitionFeeId).add(payment);
    }

    @Override
    public Long generateId() {
        return actualId++;
    }

    @Override
    public BigDecimal sumPaymentsByTuitionFee(Long idPayment) {
        return paymentsByTuitionFee
                .getOrDefault(idPayment, List.of())
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
