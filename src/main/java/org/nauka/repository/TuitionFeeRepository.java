package org.nauka.repository;

import org.nauka.model.dao.Semester;
import org.nauka.model.dao.Student;
import org.nauka.model.dao.TuitionFee;
import org.nauka.model.dto.StudentDto;
import org.nauka.model.dto.TuitionFeeDto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TuitionFeeRepository {

    void save(TuitionFee fee);
    List<TuitionFee> findAll();
}
