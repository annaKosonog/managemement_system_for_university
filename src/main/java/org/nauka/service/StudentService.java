package org.nauka.service;

import lombok.RequiredArgsConstructor;
import org.nauka.mapper.StudentMapper;
import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private Long actualId = 1L;

    private final StudentMapper studentMapper = StudentMapper.INSTANCE;

    public StudentDto addNewStudent(StudentDto studentDto) {
        Student studentDao = studentMapper.toDao(studentDto);
        studentDao.setStudentId(generateId());
        students.add(studentDao);
        return studentMapper.toDto(studentDao);
    }


    private Long generateId() {
        return actualId++;
    }

}
