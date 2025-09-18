package org.nauka.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nauka.exception.student.StudentAlreadyExistsException;
import org.nauka.exception.student.StudentNotFoundException;
import org.nauka.mapper.StudentMapper;
import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;
import org.nauka.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.nauka.model.SemesterTest.semesterWinter;
import static org.nauka.model.semesterDirection.SemesterDirectionDaoTest.computerScience;
import static org.nauka.model.student.StudentDaoTestData.adamKowalskiWithoutPaymentList;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @Mock
    StudentMapper studentMapper;

    @InjectMocks
    StudentService studentService;

    @AfterEach
    void tearDown() {
        studentRepository.clear();
    }

    @Test
    void shouldAddNewStudent() {
        StudentDto adam = StudentDto.of("Adam", 112233L, "112233@student.wwe.pl", List.of(semesterWinter), List.of(computerScience()));

        when(studentMapper.toStudentDao(adam))
                .thenReturn(adamKowalskiWithoutPaymentList());
        when(studentRepository.existsByIndexNumber(112233L)).thenReturn(false);
        when(studentRepository.save(adamKowalskiWithoutPaymentList()))
                .thenReturn(adamKowalskiWithoutPaymentList());
        when(studentMapper.toStudentDto(any(Student.class))).thenReturn(adam);

        StudentDto result = studentService.addNewStudent(adam);

        //werfikacje
        verify(studentRepository).save(any());

        //assercje
        assertEquals(adam.getName(), result.getName());
        assertEquals("Adam", result.getName());
    }

    @Test
    void shouldNotAddNewStudentBecauseStudentExistsWithDb() {
        StudentDto adam = StudentDto.of("Adam", 112233L, "112233@student.wwe.pl", List.of(semesterWinter), List.of(computerScience()));

        when(studentMapper.toStudentDao(adam))
                .thenReturn(adamKowalskiWithoutPaymentList());
        when(studentRepository.existsByIndexNumber(112233L)).thenReturn(true);

        assertThrows(StudentAlreadyExistsException.class,
                () -> studentService.addNewStudent(adam));

        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void shouldReturnWhenStudentByIdExists() {
        Long idStudent = 1L;
        when(studentRepository.findById(idStudent)).thenReturn(Optional.of(adamKowalskiWithoutPaymentList()));

        Student studentById = studentService.getStudentById(1L);

        assertEquals(idStudent, studentById.getIdStudent());
    }

    @Test
    void shouldThrowExceptionWhenStudentByIdNotFound() {
        Long idStudent = 1115L;

        StudentNotFoundException exception = assertThrows(StudentNotFoundException.class,
                () -> studentService.getStudentById(idStudent));

        assertTrue(exception.getMessage().contains("Student with id " + idStudent + " not found"));
        verify(studentRepository).findById(idStudent);
    }
}