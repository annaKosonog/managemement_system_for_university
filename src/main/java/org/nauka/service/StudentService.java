package org.nauka.service;

import lombok.RequiredArgsConstructor;
import org.nauka.exception.handler.service.AppException;
import org.nauka.exception.handler.service.ErrorType;
import org.nauka.mapper.StudentMapper;
import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;
import org.nauka.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;


    @Transactional
    public StudentDto addNewStudent(StudentDto studentDto) {
        Student saveStudent = studentMapper.toStudentDao(studentDto);
        if (studentRepository.existsByIndexNumber(saveStudent.getIndexNumber())) {
            throw new AppException("Student", studentDto.getIndexNumber(), ErrorType.NOT_FOUND);
        }
        studentRepository.save(saveStudent);
        return studentMapper.toStudentDto(saveStudent);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new AppException("Student", id, ErrorType.NOT_FOUND));
    }

    public StudentDto getStudentByIndexNumber(Long indexNumber) {
        return studentRepository.findByIndexNumber(indexNumber)
                .map(studentMapper::toStudentDto)
                .orElseThrow(() -> new AppException("Student", indexNumber, ErrorType.NOT_FOUND));
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
}
