package org.nauka.service;

import lombok.RequiredArgsConstructor;
import org.nauka.exception.handler.service.AppException;
import org.nauka.exception.handler.service.ErrorType;
import org.nauka.mapper.StudentMapper;
import org.nauka.model.dao.SemesterDirection;
import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;
import org.nauka.repository.StudentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;
    private final SemesterDirectionService semesterDirectionService;


    @Transactional
    public StudentDto addNewStudent(StudentDto studentDto) {
        Student saveStudent = studentMapper.toEntity(studentDto);
        if (studentRepository.existsByIndexNumber(saveStudent.getIndexNumber())) {
            throw new AppException("Student", studentDto.getIndexNumber(), ErrorType.NOT_FOUND);
        }
        studentRepository.save(saveStudent);
        return studentMapper.toDto(saveStudent);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new AppException("Student", id, ErrorType.NOT_FOUND));
    }

    public StudentDto getStudentByIndexNumber(Long indexNumber) {
        return studentRepository.findByIndexNumber(indexNumber)
                .map(studentMapper::toDto)
                .orElseThrow(() -> new AppException("Student", indexNumber, ErrorType.NOT_FOUND));
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    public List<StudentDto> getStudents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable)
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    public long countStudents() {
        return studentRepository.count();
    }

    public List<StudentDto> getStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    public StudentDto addSemesterDirectionToStudent(Long idStudent, Long idSemesterDirection) {
        SemesterDirection semesterDirection = semesterDirectionService.getSemesterDirectionById(idSemesterDirection);
        Student student = getStudentById(idStudent);

        List<SemesterDirection> directions = student.getSemesterDirections();
        if (directions == null) {
            directions = new ArrayList<>();
            student.setSemesterDirections(directions);
        }
        semesterDirection.setStudent(student);
        directions.add(semesterDirection);
        Student save = studentRepository.save(student);
        return studentMapper.toDto(save);
    }
}
