package org.nauka.repository;

import org.nauka.model.dao.TuitionFee;

import java.util.ArrayList;
import java.util.List;

public class TuitionFeeRepositoryImpl implements TuitionFeeRepository {

    private final List<TuitionFee> tuitionFeeList = new ArrayList<>();

    @Override
    public void save(TuitionFee fee) {
        tuitionFeeList.add(fee);
    }

    @Override
    public List<TuitionFee> findAll() {
        return new ArrayList<>(tuitionFeeList);
    }
}

//todo poprawić na HashMap
