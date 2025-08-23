package org.nauka.repository;

import lombok.RequiredArgsConstructor;
import org.nauka.model.dao.TuitionFee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class TuitionFeeRepositoryImpl implements TuitionFeeRepository {

    //    private final List<TuitionFee> tuitionFeeList = new ArrayList<>();
    private final Map<Long, TuitionFee> tuitionFeeMap = new HashMap<>();

    public void save(TuitionFee fee) {
        tuitionFeeMap.put(fee.getSemester().getSemesterId(), fee);
    }

    @Override
    public List<TuitionFee> findAll() {
        return new ArrayList<>(tuitionFeeMap
                .values());
    }

    @Override
    public TuitionFee findById(Long idTuitionFee) {
        return tuitionFeeMap
                .values()
                .stream()
                .filter(tuitionFee -> tuitionFee.getTuitionFeeId().equals(idTuitionFee))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not found id: " + idTuitionFee));
    }
}
