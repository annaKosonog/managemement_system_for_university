package org.nauka.controller;


import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;
import org.nauka.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto saved = studentService.addNewStudent(studentDto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/index/{indexNumber}")
    public ResponseEntity<StudentDto> getStudentByIndexNumber(@PathVariable Long indexNumber) {
        StudentDto studentIndexNumber = studentService.getStudentByIndexNumber(indexNumber);
        return ResponseEntity.ok(studentIndexNumber);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<StudentDto> getStudents(@RequestParam int page,
                                        @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<StudentDto> students = studentService.getStudents(page, size);
        long total = studentService.countStudents();
        return new PageImpl<>(students, pageable, total);
    }

    @PostMapping("/{idStudent}/semesterDirections/{idSemesterDirection}")
    public ResponseEntity<StudentDto> addSemesterDirectionToStudent(
            @PathVariable Long idStudent,
            @PathVariable Long idSemesterDirection) {
        StudentDto added = studentService.addSemesterDirectionToStudent(idStudent, idSemesterDirection);
        return ResponseEntity.ok(added);
    }
}
