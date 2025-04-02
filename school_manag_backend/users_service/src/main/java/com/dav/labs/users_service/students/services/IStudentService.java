package com.dav.labs.users_service.students.services;


import com.dav.labs.users_service.students.dto.requests.StudentDtoRequest;
import com.dav.labs.users_service.students.dto.responses.StudentDtoResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    Optional<StudentDtoResponse> saveStudent(StudentDtoRequest studentDtoRequest);
    Optional<List<StudentDtoResponse>> getAllStudents();
    Page<StudentDtoResponse> getStudents(int pageNumber , int pageSize);
    Optional<StudentDtoResponse> getStudentById(String studentId);
    Optional<StudentDtoResponse> updateStudent(String studentId, StudentDtoRequest studentDtoRequest);
    boolean deleteStudent(String studentId);
}
