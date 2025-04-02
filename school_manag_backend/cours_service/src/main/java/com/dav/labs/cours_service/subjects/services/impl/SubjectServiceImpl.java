package com.dav.labs.cours_service.subjects.services.impl;

import com.dav.labs.cours_service.exception.EntityExistsException;
import com.dav.labs.cours_service.exception.EntityNotFoundException;
import com.dav.labs.cours_service.subjects.dto.requests.SubjectDtoRequest;
import com.dav.labs.cours_service.subjects.dto.responses.SubjectDtoResponse;
import com.dav.labs.cours_service.subjects.entities.SubjectEntity;
import com.dav.labs.cours_service.subjects.mapper.SubjectMapper;
import com.dav.labs.cours_service.subjects.repository.SubjectRepository;
import com.dav.labs.cours_service.subjects.services.ISubjectService;
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
public class SubjectServiceImpl implements ISubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);

    @Override
    public Optional<SubjectDtoResponse> saveSubject(SubjectDtoRequest subjectDtoRequest){
        if (subjectRepository.findByName(subjectDtoRequest.getName()).isPresent()) {
            throw new EntityExistsException(messageSource.getMessage("subject.exists", new Object[]{subjectDtoRequest.getName()}, Locale.getDefault()));
        }
        SubjectEntity subject = subjectMapper.toSubjectEntity(subjectDtoRequest);
        logger.info("Cours: {}", subject);
        SubjectEntity subjectEntity = subjectRepository.save(subject);
        SubjectDtoResponse subjectDtoResponse = subjectMapper.toSubjectDtoResponse(subjectEntity);
        return Optional.of(subjectDtoResponse);
    }
    @Override
    public Optional<List<SubjectDtoResponse>> getAllSubjects(){
        List<SubjectEntity> subjectsEntities = subjectRepository.findAll();
        return Optional.of(subjectMapper.toSubjectDtoResponseList(subjectsEntities));
    }
    @Override
    public Optional<SubjectDtoResponse> getSubjectById(String id){
        return subjectRepository.findById(id)
                .map(subject -> Optional.of(subjectMapper.toSubjectDtoResponse(subject)))
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("subject.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public Optional<SubjectDtoResponse> updateSubject(String id, SubjectDtoRequest subjectDtoRequest){
        return subjectRepository.findById(id)
                .map(subject -> {
                    subject.setName(subjectDtoRequest.getName());
                    subject.setDescription(subjectDtoRequest.getDescription());
                    var subjectEntity = subjectRepository.save(subject);
                    return Optional.of(subjectMapper.toSubjectDtoResponse(subjectEntity));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("subject.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public boolean deleteSubject(String id){
        if (subjectRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(messageSource.getMessage("subject.notfound", new Object[]{id}, Locale.getDefault()));
        }
        subjectRepository.deleteById(id);
        return true;
    }
}
