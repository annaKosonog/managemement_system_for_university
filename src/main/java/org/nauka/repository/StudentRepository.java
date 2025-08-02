package org.nauka.repository;

import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository {

    boolean save(Student student);
    Long generateId();
}
