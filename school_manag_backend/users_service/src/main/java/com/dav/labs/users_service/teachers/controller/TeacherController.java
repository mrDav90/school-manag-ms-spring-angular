package com.dav.labs.users_service.teachers.controller;

import com.dav.labs.users_service.teachers.dto.requests.TeacherDtoRequest;
import com.dav.labs.users_service.teachers.dto.responses.TeacherDtoResponse;
import com.dav.labs.users_service.teachers.services.ITeacherService;
import com.dav.labs.users_service.teachers.services.impl.TeacherServiceImpl;
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
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
@Getter
@Setter
public class TeacherController {
    private final ITeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherDtoResponse>> getAllTeachers() {
        List<TeacherDtoResponse> teachers = teacherService.getAllTeachers().get();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDtoResponse> getTeacher(@PathVariable("id") Long id){
        Optional<TeacherDtoResponse> teacher = teacherService.getTeacherById(id);
        return new ResponseEntity<>(teacher.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeacherDtoResponse> saveTeacher(@RequestBody @Valid TeacherDtoRequest teacherDtoRequest){
        Optional<TeacherDtoResponse> teacherDtoResponse = teacherService.saveTeacher(teacherDtoRequest);
        return new ResponseEntity<>(teacherDtoResponse.get(), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDtoResponse> updateTeacher(@PathVariable("id") Long id, @RequestBody @Valid TeacherDtoRequest teacherDtoRequest){
        Optional<TeacherDtoResponse> teacherDtoResponse = teacherService.updateTeacher(id, teacherDtoRequest);
        return new ResponseEntity<>(teacherDtoResponse.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTeacher(@PathVariable("id") Long id){
        boolean result = teacherService.deleteTeacher(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
