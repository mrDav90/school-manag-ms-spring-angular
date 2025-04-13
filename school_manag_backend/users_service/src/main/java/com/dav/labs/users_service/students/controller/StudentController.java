package com.dav.labs.users_service.students.controller;

import com.dav.labs.users_service.students.dto.requests.StudentDtoRequest;
import com.dav.labs.users_service.students.dto.responses.StudentDtoResponse;
import com.dav.labs.users_service.students.services.impl.StudentServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@Getter
@Setter
//@SecurityRequirement(name="Keycloak")
public class StudentController {
    private final StudentServiceImpl studentService;

    @GetMapping
    @PreAuthorize("hasRole('admin') or hasRole('student')")
    public ResponseEntity<Page<StudentDtoResponse>> getStudents(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize ) {
        Page<StudentDtoResponse> students = studentService.getStudents(pageNumber,pageSize);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDtoResponse> getStudent(@PathVariable("id") String id){
        Optional<StudentDtoResponse> student = studentService.getStudentById(id);
        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDtoResponse> saveStudent(@RequestBody @Valid StudentDtoRequest studentDtoRequest){
        Optional<StudentDtoResponse> studentDtoResponse = studentService.saveStudent(studentDtoRequest);
        return studentDtoResponse.map(dtoResponse -> new ResponseEntity<>(dtoResponse, HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentDtoResponse> updateStudent(@PathVariable("id") String id, @RequestBody @Valid StudentDtoRequest studentDtoRequest){
        Optional<StudentDtoResponse> studentDtoResponse = studentService.updateStudent(id, studentDtoRequest);
        return new ResponseEntity<>(studentDtoResponse.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable("id") String id){
        boolean result = studentService.deleteStudent(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
