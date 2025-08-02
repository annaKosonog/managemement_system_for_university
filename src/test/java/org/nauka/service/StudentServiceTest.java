package org.nauka.service;

import org.junit.jupiter.api.Test;
import org.nauka.model.StudentDaoTestData;
import org.nauka.model.StudentDtoTestData;
import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StudentServiceTest {

     StudentService studentService;

    public StudentServiceTest(StudentService studentService) {
        this.studentService = studentService;
    }

    @Test
    void shouldAddNewStudent() {
        Student adamDao = StudentDaoTestData.adamKowalskiWithoutId();

        StudentDto adamDto = StudentDtoTestData.adamKowalskiDto();


        StudentDto addNewStudent = studentService.addNewStudent(adamDto);

        assertEquals("Adam", addNewStudent.getName());
    }

}