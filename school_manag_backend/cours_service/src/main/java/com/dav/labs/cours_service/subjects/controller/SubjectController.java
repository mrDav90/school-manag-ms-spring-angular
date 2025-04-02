package com.dav.labs.cours_service.subjects.controller;

import com.dav.labs.cours_service.subjects.dto.requests.SubjectDtoRequest;
import com.dav.labs.cours_service.subjects.dto.responses.SubjectDtoResponse;
import com.dav.labs.cours_service.subjects.services.ISubjectService;
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
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
@Getter
@Setter
public class SubjectController {
    private final ISubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<SubjectDtoResponse>> getAllSubjects() {
        List<SubjectDtoResponse> subjects = subjectService.getAllSubjects().get();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDtoResponse> getSubject(@PathVariable("id") String id){
        Optional<SubjectDtoResponse> subject = subjectService.getSubjectById(id);
        return new ResponseEntity<>(subject.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SubjectDtoResponse> saveSubject(@RequestBody @Valid SubjectDtoRequest subjectDtoRequest){
        Optional<SubjectDtoResponse> subjectDtoResponse = subjectService.saveSubject(subjectDtoRequest);
        return new ResponseEntity<>(subjectDtoResponse.get(), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SubjectDtoResponse> updateSubject(@PathVariable("id") String id, @RequestBody @Valid SubjectDtoRequest subjectDtoRequest){
        Optional<SubjectDtoResponse> subjectDtoResponse = subjectService.updateSubject(id, subjectDtoRequest);
        return new ResponseEntity<>(subjectDtoResponse.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSubject(@PathVariable("id") String id){
        boolean result = subjectService.deleteSubject(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
