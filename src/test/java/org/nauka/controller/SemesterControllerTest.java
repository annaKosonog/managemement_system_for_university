package org.nauka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nauka.exception.handler.controller.ExceptionsHandler;
import org.nauka.service.SemesterService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.nauka.model.SemesterTest.semesterSummer;
import static org.nauka.model.SemesterTest.semesterSummerDto;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SemesterControllerTest {

    private MockMvc mvc;

    @Mock
    private SemesterService semesterService;

    @InjectMocks
    private SemesterController semesterController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(semesterController)
                .setControllerAdvice(new ExceptionsHandler()) // <- dorzucamy handler
                .build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void shouldReturnAddNewSemester() throws Exception {
        when(semesterService.createNewSemester(semesterSummer)).thenReturn(semesterSummerDto);

        String json = objectMapper.writeValueAsString(semesterSummer);
        mvc.perform(post("/api/semesters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.semesterId").value(1L))
                .andExpect(jsonPath("$.name").value("letni"))
                .andReturn();
    }
}
