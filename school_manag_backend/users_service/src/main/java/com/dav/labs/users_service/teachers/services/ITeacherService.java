package com.dav.labs.users_service.teachers.services;


import com.dav.labs.users_service.teachers.dto.requests.TeacherDtoRequest;
import com.dav.labs.users_service.teachers.dto.responses.TeacherDtoResponse;

import java.util.List;
import java.util.Optional;

public interface ITeacherService {
    Optional<TeacherDtoResponse> saveTeacher(TeacherDtoRequest teacherDtoRequest);
    Optional<List<TeacherDtoResponse>> getAllTeachers();
    Optional<TeacherDtoResponse> getTeacherById(Long teacherId);
    Optional<TeacherDtoResponse> updateTeacher(Long teacherId, TeacherDtoRequest teacherDtoRequest);
    boolean deleteTeacher(Long teacherId);
}
