package org.nauka.repository;

import org.nauka.model.dao.TuitionFee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TuitionFeeRepository {

    void save(TuitionFee fee);

    List<TuitionFee> findAll();

    TuitionFee findById(Long idStudent);
}
