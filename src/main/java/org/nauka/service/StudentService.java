package org.nauka.service;

import org.nauka.mapper.StudentMapper;
import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;
import org.nauka.repository.StudentRepository;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    private final StudentRepository repository;

    private final StudentMapper studentMapper;

    public StudentService(StudentRepository repository, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }

    public StudentDto addNewStudent(StudentDto studentDto) {
        Long id = repository.generateId();
        Student saveStudent = studentMapper.toDao(studentDto, id);
        repository.save(saveStudent);
        return studentMapper.toDto(saveStudent);
    }
}
