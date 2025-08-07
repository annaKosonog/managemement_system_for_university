package org.nauka.repository;

import org.nauka.model.dao.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    private final List<Student> students = new ArrayList<>();
    private long actualId = 1;

    @Override
    public boolean save(Student student) {
        return students.add(student);
    }

    @Override
    public Long generateId() {
        return actualId++;
    }

    @Override
    public Student findById(Long idStudent) {
        return students.stream()
                .filter(student -> student.getIdStudent().equals(idStudent))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void clear() {
        students.clear();
    }


}
