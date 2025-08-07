package org.nauka.repository;

import org.nauka.model.dao.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository {

    boolean save(Student student);

    Long generateId();

    Student findById(Long idStudent);

    void clear();
}
