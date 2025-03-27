package com.dav.labs.program_service.academic_year.controller;

import com.dav.labs.program_service.academic_year.dto.requests.AcademicYearDtoRequest;
import com.dav.labs.program_service.academic_year.dto.responses.AcademicYearDtoResponse;
import com.dav.labs.program_service.academic_year.service.IAcademicYearService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/academic-year")
@RequiredArgsConstructor
@Getter
@Setter
public class AcademicYearController {
    private final IAcademicYearService academicYearService;

    @GetMapping
    public ResponseEntity<List<AcademicYearDtoResponse>> getAllAcademicYears() {
        List<AcademicYearDtoResponse> academicYears = academicYearService.getAllAcademicYears().get();
        return new ResponseEntity<>(academicYears, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademicYearDtoResponse> getAcademicYear(@PathVariable("id") String id){
        Optional<AcademicYearDtoResponse> academicYear = academicYearService.getAcademicYearById(id);
        return new ResponseEntity<>(academicYear.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AcademicYearDtoResponse> saveAcademicYear(@RequestBody @Valid AcademicYearDtoRequest academicYearDtoRequest){
        Optional<AcademicYearDtoResponse> academicYearDtoResponse = academicYearService.saveAcademicYear(academicYearDtoRequest);
        return new ResponseEntity<>(academicYearDtoResponse.get(), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AcademicYearDtoResponse> updateAcademicYear(@PathVariable("id") String id, @RequestBody @Valid AcademicYearDtoRequest academicYearDtoRequest){
        Optional<AcademicYearDtoResponse> academicYearDtoResponse = academicYearService.updateAcademicYear(id, academicYearDtoRequest);
        return new ResponseEntity<>(academicYearDtoResponse.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAcademicYear(@PathVariable("id") String id){
        boolean result = academicYearService.deleteAcademicYear(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
