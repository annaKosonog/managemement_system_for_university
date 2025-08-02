package org.nauka.repository;

import org.nauka.model.dao.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    private final List<Student> students = new ArrayList<>();

    @Override
    public boolean save(Student student) {
        return students.add(student);
    }
}
