package com.dav.labs.cours_service.courses.services.impl;

import com.dav.labs.cours_service.clients.models.AcademicYear;
import com.dav.labs.cours_service.clients.models.Classe;
import com.dav.labs.cours_service.courses.entities.CourseType;
import com.dav.labs.cours_service.exception.EntityExistsException;
import com.dav.labs.cours_service.exception.EntityNotFoundException;
import com.dav.labs.cours_service.courses.dto.requests.CourseDtoRequest;
import com.dav.labs.cours_service.courses.dto.responses.CourseDtoResponse;
import com.dav.labs.cours_service.courses.entities.CourseEntity;
import com.dav.labs.cours_service.courses.mapper.CourseMapper;
import com.dav.labs.cours_service.courses.repository.CourseRepository;
import com.dav.labs.cours_service.subjects.dto.responses.SubjectDtoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import javax.security.auth.Subject;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceImplTest {
    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private CourseServiceImpl courseService;
    @Mock
    private CourseMapper courseMapper;
    @Mock
    private MessageSource messageSource;

    @Test
    void saveCourseOK() {
        when(courseRepository.findBySubjectIdAndClasseIdAndAcademicYearId(anyString() , anyString(), anyString())).thenReturn(Optional.empty());
        when(courseMapper.toCourseEntity(any())).thenReturn(getCourseEntity());
        when(courseRepository.save(any())).thenReturn(getCourseEntity());
        when(courseMapper.toCourseDtoResponse(any())).thenReturn(getCourseDtoResponse());

        Optional<CourseDtoResponse> courseDtoResponse = courseService.saveCourse(getCourseDtoRequest());
        assertTrue(courseDtoResponse.isPresent());
    }

    @Test
    void saveCourseKO() {
        when(courseRepository.findBySubjectIdAndClasseIdAndAcademicYearId(anyString() , anyString() , anyString())).thenReturn(Optional.of(getCourseEntity()));
        when(messageSource.getMessage(eq("course.exists"), any() , any(Locale.class))).thenReturn("The course jee for M1GL in 2024-2025 is already created");

        EntityExistsException exception = assertThrows(EntityExistsException.class, () -> courseService.saveCourse(getCourseDtoRequest()));
        assertEquals("The course jee for M1GL in 2024-2025 is already created", exception.getMessage());
        assertNotNull(exception);
    }

    @Test
    void getAllCourses() {
        when(courseRepository.findAll()).thenReturn(List.of(getCourseEntity()));
        when(courseMapper.toCourseDtoResponseList(any())).thenReturn(List.of(getCourseDtoResponse()));

        Optional<List<CourseDtoResponse>> courses = courseService.getAllCourses();
        assertTrue(courses.isPresent());
        assertEquals(1, courses.get().size());
    }

    @Test
    void getCourseByIdOK() {
        when(courseRepository.findById(anyString())).thenReturn(Optional.of(getCourseEntity()));
        when(courseMapper.toCourseDtoResponse(any())).thenReturn(getCourseDtoResponse());

        Optional<CourseDtoResponse> course = courseService.getCourseById("1");
        assertTrue(course.isPresent());
        assertEquals("1", course.get().getId());
    }

    @Test
    void getCourseByIdKO() {
        when(courseRepository.findById(anyString())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("course.notfound"), any(), any(Locale.class))).thenReturn("Course with id=1 is not found");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> courseService.getCourseById("1"));
        assertEquals("Course with id=1 is not found", exception.getMessage());
    }

    @Test
    void updateCourseOK() {
        when(courseRepository.findById(anyString())).thenReturn(Optional.of(getCourseEntity()));
        when(courseRepository.save(any())).thenReturn(getCourseEntity());
        when(courseMapper.toCourseDtoResponse(any())).thenReturn(getCourseDtoResponse());

        Optional<CourseDtoResponse> updatedCourse = courseService.updateCourse("1", getCourseDtoRequest());
        assertTrue(updatedCourse.isPresent());
        assertEquals("1", updatedCourse.get().getId());
    }

    @Test
    void updateCourseKO() {
        when(courseRepository.findById(anyString())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("course.notfound"), any(), any(Locale.class)))
                .thenReturn("Course not found");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> courseService.updateCourse("1" ,getCourseDtoRequest()));
        assertEquals("Course not found", exception.getMessage());
    }

    @Test
    void deleteCourseOK() {
        when(courseRepository.findById(anyString())).thenReturn(Optional.of(getCourseEntity()));
        boolean result = courseService.deleteCourse(anyString());
        assertTrue(result);
        verify(courseRepository, times(1)).deleteById(anyString());
    }

    @Test
    void deleteCourseKO() {
        when(courseRepository.findById(anyString())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("course.notfound"), any(), any(Locale.class)))
                .thenReturn("Course not found");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> courseService.deleteCourse("1"));
        assertEquals("Course not found", exception.getMessage());
    }

    private CourseDtoRequest getCourseDtoRequest(){
        CourseDtoRequest courseDtoRequest = new CourseDtoRequest();
        courseDtoRequest.setSubjectId("1");
        courseDtoRequest.setClasseId("1");
        courseDtoRequest.setAcademicYearId("1");
        courseDtoRequest.setCourseType(CourseType.ANNUAL);
        courseDtoRequest.setCoefficient(3);
        return courseDtoRequest;
    }
    private CourseEntity getCourseEntity(){
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId("1");
        courseEntity.setSubjectId("1");
        courseEntity.setClasseId("1");
        courseEntity.setAcademicYearId("1");
        courseEntity.setCourseType(CourseType.ANNUAL);
        courseEntity.setCoefficient(3);
        return courseEntity;
    }
    private CourseDtoResponse getCourseDtoResponse(){
        CourseDtoResponse courseDtoResponse = new CourseDtoResponse();
        courseDtoResponse.setSubjectId("1");
        courseDtoResponse.setSubject(new SubjectDtoResponse());
        courseDtoResponse.setClasseId("1");
        courseDtoResponse.setClasse(new Classe());
        courseDtoResponse.setAcademicYearId("1");
        courseDtoResponse.setAcademicYear(new AcademicYear());
        courseDtoResponse.setCourseType(CourseType.ANNUAL);
        courseDtoResponse.setCoefficient(3);
        return courseDtoResponse;
    }

}
