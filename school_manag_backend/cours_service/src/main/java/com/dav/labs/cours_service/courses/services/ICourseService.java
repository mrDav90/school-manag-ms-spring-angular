package com.dav.labs.cours_service.courses.services;



import com.dav.labs.cours_service.courses.dto.requests.CourseDtoRequest;
import com.dav.labs.cours_service.courses.dto.responses.CourseDtoResponse;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    Optional<CourseDtoResponse> saveCourse(CourseDtoRequest courseDtoRequest);
    Optional<List<CourseDtoResponse>> getAllCourses();
    Optional<CourseDtoResponse> getCourseById(String courseId);
    Optional<CourseDtoResponse> updateCourse(String courseId, CourseDtoRequest courseDtoRequest);
    boolean deleteCourse(String courseId);
}
