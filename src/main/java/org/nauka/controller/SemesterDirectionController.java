package org.nauka.controller;

import lombok.RequiredArgsConstructor;
import org.nauka.model.dto.SemesterDirectionDto;
import org.nauka.service.SemesterDirectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/direction")
public class SemesterDirectionController {

    private final SemesterDirectionService semesterDirectionService;

    @PostMapping("/name/{name}")
    public ResponseEntity<SemesterDirectionDto> addNewDirection(@PathVariable String name) {
        SemesterDirectionDto direction = semesterDirectionService.createNewSemesterDirection(name);
        return ResponseEntity.ok(direction);
    }

    @PatchMapping("/idSemesterDirection/{idSemesterDirection}/idSemester/{idSemester}")
    public ResponseEntity<SemesterDirectionDto> addNewSemesterDirectionToSemester(
            @PathVariable Long idSemesterDirection,
            @PathVariable Long idSemester) {
        SemesterDirectionDto directionToSemester = semesterDirectionService.addSemesterDirectionToSemester(idSemesterDirection, idSemester);
        return ResponseEntity.ok(directionToSemester);
    }
}
