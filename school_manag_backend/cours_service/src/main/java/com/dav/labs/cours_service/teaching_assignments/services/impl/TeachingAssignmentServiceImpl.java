package com.dav.labs.cours_service.teaching_assignments.services.impl;

import com.dav.labs.cours_service.classes.repository.ClasseRepository;
import com.dav.labs.cours_service.clients.interfaces.TeacherRestClient;
import com.dav.labs.cours_service.courses.repository.CourseRepository;
import com.dav.labs.cours_service.exception.EntityExistsException;
import com.dav.labs.cours_service.exception.EntityNotFoundException;
import com.dav.labs.cours_service.teaching_assignments.dto.requests.TeachingAssignmentDtoRequest;
import com.dav.labs.cours_service.teaching_assignments.dto.responses.TeachingAssignmentDtoResponse;
import com.dav.labs.cours_service.teaching_assignments.entities.TeachingAssignmentEntity;
import com.dav.labs.cours_service.teaching_assignments.mapper.TeachingAssignmentMapper;
import com.dav.labs.cours_service.teaching_assignments.repository.TeachingAssignmentRepository;
import com.dav.labs.cours_service.teaching_assignments.services.ITeachingAssignmentService;
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
public class TeachingAssignmentServiceImpl implements ITeachingAssignmentService {
    private final TeachingAssignmentRepository teacherAssignmentRepository;
    private final TeachingAssignmentMapper teacherAssignmentMapper;
    private final MessageSource messageSource;
    private final CourseRepository courseRepository;
    private final ClasseRepository classeRepository;
    private final Logger logger = LoggerFactory.getLogger(TeachingAssignmentServiceImpl.class);
    private final TeacherRestClient teacherRestClient;
    @Override
    public Optional<TeachingAssignmentDtoResponse> saveTeachingAssignment(TeachingAssignmentDtoRequest teacherAssignmentDtoRequest){
        TeachingAssignmentEntity teacherAssignment = teacherAssignmentMapper.toTeachingAssignmentEntity(teacherAssignmentDtoRequest);
        logger.info("Teaching assignment: {}", teacherAssignment);
        TeachingAssignmentEntity teacherAssignmentEntity = teacherAssignmentRepository.save(teacherAssignment);
        TeachingAssignmentDtoResponse teacherAssignmentDtoResponse = teacherAssignmentMapper.toTeachingAssignmentDtoResponse(teacherAssignmentEntity);
        return Optional.of(teacherAssignmentDtoResponse);
    }
    @Override
    public Optional<List<TeachingAssignmentDtoResponse>> getAllTeachingAssignments(){
        List<TeachingAssignmentEntity> teacherAssignmentsEntities = teacherAssignmentRepository.findAll().stream().map(elt -> {
                    var courseName = courseRepository.findById(elt.getCourseId()).get().getName();
                    var classeName = classeRepository.findById(elt.getClasseId()).get().getName();
                    //var teacherName = teacherRestClient.getTeacherById(elt.getTeacherId()).getFirstName()+" "+teacherRestClient.getTeacherById(elt.getTeacherId()).getLastName();
                    elt.setCourseName(courseName);
                    elt.setClasseName(classeName);
                    //elt.setTeacherName(teacherName);
                    return elt;
                }

            ).collect(Collectors.toList());

        return Optional.of(teacherAssignmentMapper.toTeachingAssignmentDtoResponseList(teacherAssignmentsEntities));
    }
    @Override
    public Optional<TeachingAssignmentDtoResponse> getTeachingAssignmentById(Long id){
        return teacherAssignmentRepository.findById(id)
                .map(teacherAssignment -> Optional.of(teacherAssignmentMapper.toTeachingAssignmentDtoResponse(teacherAssignment)))
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("teacherAssignment.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public Optional<TeachingAssignmentDtoResponse> updateTeachingAssignment(Long id, TeachingAssignmentDtoRequest teacherAssignmentDtoRequest){
        return teacherAssignmentRepository.findById(id)
                .map(teacherAssignment -> {
                    teacherAssignment.setCourseId(teacherAssignmentDtoRequest.getCourseId());
                    teacherAssignment.setClasseId(teacherAssignmentDtoRequest.getClasseId());
                    teacherAssignment.setTeacherId(teacherAssignmentDtoRequest.getTeacherId());
                    var teacherAssignmentEntity = teacherAssignmentRepository.save(teacherAssignment);
                    return Optional.of(teacherAssignmentMapper.toTeachingAssignmentDtoResponse(teacherAssignmentEntity));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("teacherAssignment.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public boolean deleteTeachingAssignment(Long id){
        if (teacherAssignmentRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(messageSource.getMessage("teacherAssignment.notfound", new Object[]{id}, Locale.getDefault()));
        }
        teacherAssignmentRepository.deleteById(id);
        return true;
    }
}
