package com.dav.labs.users_service.teachers.services.impl;

import com.dav.labs.users_service.exception.EntityExistsException;
import com.dav.labs.users_service.exception.EntityNotFoundException;
import com.dav.labs.users_service.teachers.dto.requests.TeacherDtoRequest;
import com.dav.labs.users_service.teachers.dto.responses.TeacherDtoResponse;
import com.dav.labs.users_service.teachers.entities.TeacherEntity;
import com.dav.labs.users_service.teachers.mapper.TeacherMapper;
import com.dav.labs.users_service.teachers.repository.TeacherRepository;
import com.dav.labs.users_service.teachers.services.ITeacherService;
import com.dav.labs.users_service.users.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements ITeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Override
    public Optional<TeacherDtoResponse> saveTeacher(TeacherDtoRequest teacherDtoRequest){
        if (teacherRepository.findByEmailPro(teacherDtoRequest.getEmailPro()).isPresent()) {
            throw new EntityExistsException(messageSource.getMessage("teacher.exists", new Object[]{teacherDtoRequest.getEmailPro()}, Locale.getDefault()));
        }
        TeacherEntity teacher = teacherMapper.toTeacherEntity(teacherDtoRequest);
        logger.info("EmailPro: {}", teacher);
        TeacherEntity teacherEntity = teacherRepository.save(teacher);
        TeacherDtoResponse teacherDtoResponse = teacherMapper.toTeacherDtoResponse(teacherEntity);
        return Optional.of(teacherDtoResponse);
    }
    @Override
    public Optional<List<TeacherDtoResponse>> getAllTeachers(){
        List<TeacherEntity> teachersEntities = teacherRepository.findAll();
        return Optional.of(teacherMapper.toTeacherDtoResponseList(teachersEntities));
    }
    @Override
    public Optional<TeacherDtoResponse> getTeacherById(Long id){
        return teacherRepository.findById(id)
                .map(teacher -> Optional.of(teacherMapper.toTeacherDtoResponse(teacher)))
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("teacher.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public Optional<TeacherDtoResponse> updateTeacher(Long id, TeacherDtoRequest teacherDtoRequest){
        return teacherRepository.findById(id)
                .map(teacher -> {
                    teacher.setFirstName(teacherDtoRequest.getFirstName());
                    teacher.setLastName(teacherDtoRequest.getFirstName());
                    teacher.setEmailPro(teacherDtoRequest.getEmailPro());
                    teacher.setPhoneNumber(teacherDtoRequest.getPhoneNumber());
                    teacher.setAddress(teacherDtoRequest.getAddress());
                    teacher.setTeachingAssignments(teacherDtoRequest.getTeachingAssignments());
                    var teacherEntity = teacherRepository.save(teacher);
                    return Optional.of(teacherMapper.toTeacherDtoResponse(teacherEntity));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("teacher.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public boolean deleteTeacher(Long id){
        if (teacherRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(messageSource.getMessage("teacher.notfound", new Object[]{id}, Locale.getDefault()));
        }
        teacherRepository.deleteById(id);
        return true;
    }
}
