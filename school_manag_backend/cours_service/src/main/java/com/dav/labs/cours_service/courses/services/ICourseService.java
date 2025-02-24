package com.dav.labs.cours_service.courses.services;



import com.dav.labs.cours_service.courses.dto.requests.CourseDtoRequest;
import com.dav.labs.cours_service.courses.dto.responses.CourseDtoResponse;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    Optional<CourseDtoResponse> saveCourse(CourseDtoRequest courseDtoRequest);
    Optional<List<CourseDtoResponse>> getAllCourses();
    Optional<CourseDtoResponse> getCourseById(Long courseId);
    Optional<CourseDtoResponse> updateCourse(Long courseId, CourseDtoRequest courseDtoRequest);
    boolean deleteCourse(Long courseId);
}
