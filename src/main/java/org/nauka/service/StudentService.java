package org.nauka.service;

import lombok.RequiredArgsConstructor;
import org.nauka.mapper.StudentMapper;
import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;
import org.nauka.repository.StudentRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;


    public StudentDto addNewStudent(StudentDto studentDto) {
        Student saveStudent = studentMapper.toStudentDao(studentDto);
        if (studentRepository.existsByIndexNumber(saveStudent.getIndexNumber())) {
            throw new IllegalArgumentException("Student with this index number already exists");
        }
        studentRepository.save(saveStudent);
        return studentMapper.toStudentDto(saveStudent);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student with id " + id + " not found"));
    }
}
