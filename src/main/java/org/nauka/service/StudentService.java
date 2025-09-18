package org.nauka.service;

import lombok.RequiredArgsConstructor;
import org.nauka.exception.student.StudentAlreadyExistsException;
import org.nauka.exception.student.StudentNotFoundException;
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
            throw new StudentAlreadyExistsException(studentDto.getIndexNumber());
        }
        studentRepository.save(saveStudent);
        return studentMapper.toStudentDto(saveStudent);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }
}
