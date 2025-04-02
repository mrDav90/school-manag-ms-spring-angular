package com.dav.labs.cours_service.courses.services.impl;

import com.dav.labs.cours_service.clients.interfaces.AcademicYearRestClient;
import com.dav.labs.cours_service.clients.interfaces.ClasseRestClient;
import com.dav.labs.cours_service.courses.services.ICourseService;
import com.dav.labs.cours_service.exception.EntityExistsException;
import com.dav.labs.cours_service.exception.EntityNotFoundException;
import com.dav.labs.cours_service.courses.dto.requests.CourseDtoRequest;
import com.dav.labs.cours_service.courses.dto.responses.CourseDtoResponse;
import com.dav.labs.cours_service.courses.entities.CourseEntity;
import com.dav.labs.cours_service.courses.mapper.CourseMapper;
import com.dav.labs.cours_service.courses.repository.CourseRepository;
import com.dav.labs.cours_service.courses.services.ICourseService;
import com.dav.labs.cours_service.subjects.services.ISubjectService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements ICourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final ISubjectService subjectService;
    private final ClasseRestClient classeRestClient;
    private final AcademicYearRestClient academicYearRestClient;

    @Override
    public Optional<CourseDtoResponse> saveCourse(CourseDtoRequest courseDtoRequest){
        if (courseRepository.findBySubjectIdAndClasseIdAndAcademicYearId(courseDtoRequest.getSubjectId() , courseDtoRequest.getClasseId(), courseDtoRequest.getAcademicYearId()).isPresent()) {
            throw new EntityExistsException(messageSource.getMessage("course.exists", new Object[]{subjectService.getSubjectById(courseDtoRequest.getSubjectId()).get().getName() , classeRestClient.getClasseById(courseDtoRequest.getClasseId()).getName()+" "+classeRestClient.getClasseById(courseDtoRequest.getClasseId()).getSectorName() , academicYearRestClient.getAcademicYearById(courseDtoRequest.getAcademicYearId()).getLabel()}, Locale.getDefault()));
        }
        CourseEntity course = courseMapper.toCourseEntity(courseDtoRequest);
        logger.info("Course: {}", course);
        CourseEntity courseEntity = courseRepository.save(course);
        CourseDtoResponse courseDtoResponse = courseMapper.toCourseDtoResponse(courseEntity);
        return Optional.of(courseDtoResponse);
    }
    @Override
    public Optional<List<CourseDtoResponse>> getAllCourses(){
        List<CourseEntity> coursesEntities = courseRepository.findAll().stream().map(course -> {
            course.setSubject(subjectService.getSubjectById(course.getSubjectId()).get());
            course.setClasse(classeRestClient.getClasseById(course.getClasseId()));
            course.setAcademicYear(academicYearRestClient.getAcademicYearById(course.getAcademicYearId()));
            return course;
        }).collect(Collectors.toList());
        return Optional.of(courseMapper.toCourseDtoResponseList(coursesEntities));
    }
    @Override
    public Optional<CourseDtoResponse> getCourseById(String id){
        return courseRepository.findById(id)
                .map(course -> {
                    course.setSubject(subjectService.getSubjectById(course.getSubjectId()).get());
                    course.setClasse(classeRestClient.getClasseById(course.getClasseId()));
                    course.setAcademicYear(academicYearRestClient.getAcademicYearById(course.getAcademicYearId()));
                    return Optional.of(courseMapper.toCourseDtoResponse(course));
                })
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("course.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public Optional<CourseDtoResponse> updateCourse(String id, CourseDtoRequest courseDtoRequest){
        return courseRepository.findById(id)
                .map(course -> {
                    course.setCourseType(courseDtoRequest.getCourseType());
                    course.setCoefficient(courseDtoRequest.getCoefficient());
                    course.setSubjectId(courseDtoRequest.getSubjectId());
                    course.setClasseId(courseDtoRequest.getClasseId());
                    course.setAcademicYearId(courseDtoRequest.getAcademicYearId());
                    var courseEntity = courseRepository.save(course);
                    return Optional.of(courseMapper.toCourseDtoResponse(courseEntity));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("course.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public boolean deleteCourse(String id){
        if (courseRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(messageSource.getMessage("course.notfound", new Object[]{id}, Locale.getDefault()));
        }
        courseRepository.deleteById(id);
        return true;
    }
}
