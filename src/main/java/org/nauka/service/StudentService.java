package org.nauka.service;

import org.nauka.mapper.StudentMapper;
import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;
import org.nauka.repository.StudentRepository;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentDto addNewStudent(StudentDto studentDto) {
        Long id = studentRepository.generateId();
        Student saveStudent = studentMapper.toStudentDao(studentDto, id);
        studentRepository.save(saveStudent);
        return studentMapper.toStudentDto(saveStudent);
    }

    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new IllegalArgumentException("Not found student by id: " + id);
        }
        return studentMapper.toStudentDto(student);
    }
}
