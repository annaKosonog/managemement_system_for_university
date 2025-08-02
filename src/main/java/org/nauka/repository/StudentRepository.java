package org.nauka.repository;

import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;

public interface StudentRepository {

    boolean save(Student student);

}
