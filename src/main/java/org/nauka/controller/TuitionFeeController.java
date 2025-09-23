package org.nauka.controller;

import lombok.RequiredArgsConstructor;
import org.nauka.model.dto.TuitionFeeDto;
import org.nauka.service.TuitionFeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tuition")
@RequiredArgsConstructor
public class TuitionFeeController {

    private final TuitionFeeService tuitionFeeService;


    @PostMapping()
    public ResponseEntity<TuitionFeeDto> addTuitionFees(@RequestBody TuitionFeeDto tuitionFeeDto, Long id) {
        TuitionFeeDto addTuitionFeeRequest = tuitionFeeService.addTuitionFees(
                id,
                tuitionFeeDto.getSemester(),
                tuitionFeeDto.getAmount());
        return ResponseEntity.status(HttpStatus.CREATED).body(addTuitionFeeRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTuitionFeeById(@PathVariable Long id) {
        tuitionFeeService.getTuitionFeeById(id);
        return ResponseEntity.ok(tuitionFeeService.getTuitionFeeById(id));
    }
}
