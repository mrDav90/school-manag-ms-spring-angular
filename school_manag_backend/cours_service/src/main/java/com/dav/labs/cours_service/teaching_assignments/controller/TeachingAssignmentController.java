package com.dav.labs.cours_service.teaching_assignments.controller;

import com.dav.labs.cours_service.teaching_assignments.dto.requests.TeachingAssignmentDtoRequest;
import com.dav.labs.cours_service.teaching_assignments.dto.responses.TeachingAssignmentDtoResponse;
import com.dav.labs.cours_service.teaching_assignments.services.ITeachingAssignmentService;
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
@RequestMapping("/api/v1/teaching_assignments")
//@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Getter
@Setter
public class TeachingAssignmentController {
    private final ITeachingAssignmentService teachingAssignmentService;

    @GetMapping
    public ResponseEntity<List<TeachingAssignmentDtoResponse>> getAllTeachingAssignments() {
        List<TeachingAssignmentDtoResponse> teachingAssignments = teachingAssignmentService.getAllTeachingAssignments().get();
        return new ResponseEntity<>(teachingAssignments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeachingAssignmentDtoResponse> getTeachingAssignment(@PathVariable("id") Long id){
        Optional<TeachingAssignmentDtoResponse> teachingAssignment = teachingAssignmentService.getTeachingAssignmentById(id);
        return new ResponseEntity<>(teachingAssignment.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeachingAssignmentDtoResponse> saveTeachingAssignment(@RequestBody @Valid TeachingAssignmentDtoRequest teachingAssignmentDtoRequest){
        Optional<TeachingAssignmentDtoResponse> teachingAssignmentDtoResponse = teachingAssignmentService.saveTeachingAssignment(teachingAssignmentDtoRequest);
        return new ResponseEntity<>(teachingAssignmentDtoResponse.get(), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TeachingAssignmentDtoResponse> updateTeachingAssignment(@PathVariable("id") Long id, @RequestBody @Valid TeachingAssignmentDtoRequest teachingAssignmentDtoRequest){
        Optional<TeachingAssignmentDtoResponse> teachingAssignmentDtoResponse = teachingAssignmentService.updateTeachingAssignment(id, teachingAssignmentDtoRequest);
        return new ResponseEntity<>(teachingAssignmentDtoResponse.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTeachingAssignment(@PathVariable("id") Long id){
        boolean result = teachingAssignmentService.deleteTeachingAssignment(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
