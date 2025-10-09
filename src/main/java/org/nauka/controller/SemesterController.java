package org.nauka.controller;

import org.nauka.mapper.SemesterMapper;
import org.nauka.model.dao.Semester;
import org.nauka.model.dto.SemesterDto;
import org.nauka.service.SemesterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/semesters")
public class SemesterController {


    public SemesterController(SemesterService semesterService, SemesterMapper semesterMapper) {
        this.semesterService = semesterService;
    }

    private final SemesterService semesterService;

    @PostMapping
    public ResponseEntity<SemesterDto> addNewSemester(@RequestBody Semester semester) {
        SemesterDto newSemester = semesterService.createNewSemester(semester);
        return ResponseEntity.ok(newSemester);
    }

    @PatchMapping("/{idSemester}/indexNumber/{indexNumber}")
    public ResponseEntity<SemesterDto> addStudentToSemesters(
            @PathVariable Long idSemester,
            @PathVariable Long indexNumber) {
        SemesterDto toSemester = semesterService.addStudentToSemester(idSemester, indexNumber);
        return ResponseEntity.ok(toSemester);
    }
}
