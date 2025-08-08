package org.nauka.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nauka.mapper.StudentMapper;
import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;
import org.nauka.model.student.StudentDaoTestData;
import org.nauka.model.student.StudentDtoTestData;
import org.nauka.repository.StudentRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @Mock
    StudentMapper studentMapper;

    @InjectMocks
    StudentService studentService;

    @Test
    void shouldAddNewStudent() {
        Long id = 1L;
        StudentDto adamDto = StudentDtoTestData.adamKowalskiDto();
        Student adamedKowalskiWithId = StudentDaoTestData.adamKowalskiWithId();

        when(studentRepository.generateId()).thenReturn(id);
        when(studentMapper.toStudentDao(adamDto, id)).thenReturn(adamedKowalskiWithId);
        when(studentMapper.toStudentDto(adamedKowalskiWithId)).thenReturn(adamDto);

        StudentDto result = studentService.addNewStudent(adamDto);

        //werfikacje
        verify(studentRepository).generateId();

        verify(studentRepository).save(any());

        //assercje
        assertEquals(adamDto.getName(), result.getName());

        assertEquals("Adam", result.getName());
    }

    @Test
    void notShouldAddNewStudent() {
        Long id = 1L;
        StudentDto dto = StudentDtoTestData.adamKowalskiDto();
        Student entity = StudentDaoTestData.adamKowalskiWithId();

        // mocki
        when(studentRepository.generateId()).thenReturn(id);
        when(studentMapper.toStudentDao(dto, id)).thenReturn(entity);
        when(studentRepository.save(entity)).thenReturn(false);

        StudentDto result = studentService.addNewStudent(dto);

        // weryfikacje
        verify(studentRepository).generateId();
        verify(studentRepository).save(entity);

        // asercje – zakładamy, że zwracany jest null
        assertEquals(null, result);
    }

    @Test
    void shouldReturnStudentDtoWhenStudentExists() {
        Long id = 1L;
        StudentDto dto = StudentDtoTestData.adamKowalskiDto();
        Student entity = StudentDaoTestData.adamKowalskiWithId();

        when(studentRepository.findById(id)).thenReturn(entity);
        when(studentMapper.toStudentDto(entity)).thenReturn(dto);

        StudentDto result = studentService.getStudentById(id);

        assertNotNull(result);
        assertEquals(id, entity.getIdStudent());
        assertEquals("Adam", result.getName());

        verify(studentRepository).findById(id);
        verify(studentMapper).toStudentDto(entity);
    }

    @Test
    void shouldThrowExceptionWhenStudentDoesNotExist() {
        Long id = 1L;

        when(studentRepository.findById(id)).thenReturn(null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> studentService.getStudentById(id)
        );

        assertTrue(exception.getMessage().contains("Not found student by id: " + id));
        verify(studentRepository).findById(id);
        verify(studentMapper, never()).toStudentDto(any());
    }
}