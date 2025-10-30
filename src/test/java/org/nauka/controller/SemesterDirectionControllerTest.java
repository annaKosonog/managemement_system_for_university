package org.nauka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.nauka.exception.handler.service.AppException;
import org.nauka.exception.handler.service.ErrorType;
import org.nauka.service.SemesterDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.nauka.model.semesterDirection.SemesterDirectionDtoTest.computerScienceDto;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SemesterDirectionController.class)
@AutoConfigureMockMvc(addFilters = false)
public class SemesterDirectionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SemesterDirectionService semesterDirectionService;

    //  private SemesterDirectionController semesterDirectionController;

    @Autowired
    private ObjectMapper objectMapper;

   /* @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(semesterDirectionController)
                .setControllerAdvice(new ExceptionsHandler()) // <- dorzucamy handler
                .build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }*/

    @Test
    void shouldReturnAddNewDirection() throws Exception {
        String name = "COMPUTER SCIENCE";
        String json = objectMapper.writeValueAsString(name);
        when(semesterDirectionService.createNewSemesterDirection(name)).thenReturn(computerScienceDto());


        mvc.perform(post("/api/direction/name/{name}", name)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idSemesterDirection").value(2L))
                .andExpect(jsonPath("$.direction").value("COMPUTER SCIENCE"))
                .andReturn();
    }

    @Test
    void shouldReturnAddNewSemesterDirectionToSemester() throws Exception {
        Long idSemesterDirection = 2L;
        Long idSemester = 1L;
        when(semesterDirectionService.addSemesterDirectionToSemester(idSemesterDirection, idSemester)).thenReturn(computerScienceDto());

        MvcResult result = mvc.perform(patch("/api/direction/idSemesterDirection/{idSemesterDirection}/idSemester/{idSemester}",
                        idSemesterDirection, idSemester)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonBody = result.getResponse().getContentAsString();
        System.out.println(jsonBody);
        assertTrue(jsonBody.contains("COMPUTER SCIENCE"));
    }

    @Test
    void shouldThrowExceptionWhenDoNotAddNewSemesterDirectionToSemester() throws Exception {
        Long idSemesterDirection = 5L;
        Long idSemester = 1L;
        when(semesterDirectionService.addSemesterDirectionToSemester(idSemesterDirection, idSemester)).thenThrow(new AppException("SemesterDirection", 404L, ErrorType.NOT_FOUND));
        MvcResult result = mvc.perform(patch("/api/direction/idSemesterDirection/{idSemesterDirection}/idSemester/{idSemester}",
                        idSemesterDirection, idSemester)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        int status = result.getResponse().getStatus();

        assertEquals(404, status);
    }
}
