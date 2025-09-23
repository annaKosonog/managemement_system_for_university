package org.nauka.controller;


import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;
import org.nauka.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
