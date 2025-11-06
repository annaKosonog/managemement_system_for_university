package org.nauka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.nauka.exception.handler.controller.ExceptionsHandler;
import org.nauka.exception.handler.service.AppException;
import org.nauka.exception.handler.service.ErrorType;
import org.nauka.model.dto.StudentDto;
import org.nauka.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.nauka.model.student.StudentDaoTestData.adamKowalskiWithId;
import static org.nauka.model.student.StudentDtoTestData.adamKowalskiDto;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
class StudentControllerTest {

    @Autowired
    private MockMvc mvc;

    /*@MockBean*/
    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;


    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(studentController)
                .setControllerAdvice(new ExceptionsHandler()) // <- dorzucamy handler
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldReturnStudentById() throws Exception {
        Long idStudent = 1L;

        when(studentService.getStudentById(idStudent)).thenReturn(adamKowalskiWithId());

        mvc.perform(get("/api/students/id/{id}", idStudent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idStudent").value(idStudent))
                .andExpect(jsonPath("$.name").value("Adam"));
    }

    @Test
    void shouldReturnStatusNotFoundWhenStudentByIdNotExists() throws Exception {
        Long idStudent = 125896L;

        when(studentService.getStudentById(idStudent)).thenThrow(new AppException("Student", idStudent, ErrorType.NOT_FOUND));

        mvc.perform(get("/api/students/id/{id}", idStudent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldAddNewStudentToDb() throws Exception {
        StudentDto studentDto = adamKowalskiDto();
        when(studentService.addNewStudent(any())).thenReturn(studentDto);
        String json = objectMapper.writeValueAsString(adamKowalskiDto());

        MvcResult result = mvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        int status = result.getResponse().getStatus();
        String jsonBody = result.getResponse().getContentAsString();

        assertEquals(200, status);
        assertTrue(jsonBody.contains("Adam"));
    }

    @Test
    void shouldReturnExceptionWhenStudentAlreadyExistsInDb() throws Exception {
        StudentDto studentDto = adamKowalskiDto();

        when(studentService.addNewStudent(any()))
                .thenThrow(new AppException("Student", studentDto.getIndexNumber(), ErrorType.ALREADY_EXISTS));

        String json = objectMapper.writeValueAsString(studentDto);

        mvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message")
                        .value(STR."Student with id \{studentDto.getIndexNumber()} already exists"));
    }

    @Test
    void shouldReturnStudentByIndexNumber() throws Exception {
        Long indexNumber = 112233L;

        when(studentService.getStudentByIndexNumber(indexNumber)).thenReturn(adamKowalskiDto());

        mvc.perform(get("/api/students/index/{indexNumber}", indexNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldThrowExceptionWhenNotFoundStudentByIndexNumber() throws Exception {
        Long indexNumber = 999999L;
        when(studentService.getStudentByIndexNumber(indexNumber)).thenThrow(
                new AppException("Student", indexNumber, ErrorType.NOT_FOUND)
        );

        mvc.perform(get("/api/students/index/{indexNumber}", indexNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(
                        STR."Student with id \{indexNumber} not found"));
    }

    @Test
    void shouldDeleteStudentById() throws Exception {
        Long id = 1L;

        mvc.perform(delete("/api/students/id/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(studentService, times(1)).deleteStudentById(id);
    }
}