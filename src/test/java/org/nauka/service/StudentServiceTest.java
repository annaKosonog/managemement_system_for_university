package org.nauka.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nauka.mapper.StudentMapper;
import org.nauka.model.StudentDaoTestData;
import org.nauka.model.StudentDtoTestData;
import org.nauka.model.dao.Student;
import org.nauka.model.dto.StudentDto;
import org.nauka.repository.StudentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        when(studentMapper.toDao(adamDto, id)).thenReturn(adamedKowalskiWithId);
        when(studentMapper.toDto(adamedKowalskiWithId)).thenReturn(adamDto);

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
        when(studentMapper.toDao(dto, id)).thenReturn(entity);
        when(studentRepository.save(entity)).thenReturn(false);

        StudentDto result = studentService.addNewStudent(dto);

        // weryfikacje
        verify(studentRepository).generateId();
        verify(studentRepository).save(entity);

        // asercje – zakładamy, że zwracany jest null
        assertEquals(null, result);
    }


}