package com.dav.labs.cours_service.classes.services.impl;

import com.dav.labs.cours_service.classes.dto.requests.ClasseDtoRequest;
import com.dav.labs.cours_service.classes.dto.responses.ClasseDtoResponse;
import com.dav.labs.cours_service.classes.entities.ClasseEntity;
import com.dav.labs.cours_service.classes.mapper.ClasseMapper;
import com.dav.labs.cours_service.classes.repository.ClasseRepository;
import com.dav.labs.cours_service.classes.services.IClasseService;
import com.dav.labs.cours_service.exception.EntityExistsException;
import com.dav.labs.cours_service.exception.EntityNotFoundException;
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
public class ClasseServiceImpl implements IClasseService {
    private final ClasseRepository classeRepository;
    private final ClasseMapper classeMapper;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(ClasseServiceImpl.class);

    @Override
    public Optional<ClasseDtoResponse> saveClasse(ClasseDtoRequest classeDtoRequest){
        if (classeRepository.findByName(classeDtoRequest.getName()).isPresent()) {
            throw new EntityExistsException(messageSource.getMessage("classe.exists", new Object[]{classeDtoRequest.getName()}, Locale.getDefault()));
        }
        ClasseEntity classe = classeMapper.toClasseEntity(classeDtoRequest);
        logger.info("Classe: {}", classe);
        ClasseEntity classeEntity = classeRepository.save(classe);
        ClasseDtoResponse classeDtoResponse = classeMapper.toClasseDtoResponse(classeEntity);
        return Optional.of(classeDtoResponse);
    }
    @Override
    public Optional<List<ClasseDtoResponse>> getAllClasses(){
        List<ClasseEntity> classesEntities = classeRepository.findAll();
        return Optional.of(classeMapper.toClasseDtoResponseList(classesEntities));
    }
    @Override
    public Optional<ClasseDtoResponse> getClasseById(Long id){
        return classeRepository.findById(id)
                .map(classe -> Optional.of(classeMapper.toClasseDtoResponse(classe)))
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("classe.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public Optional<ClasseDtoResponse> updateClasse(Long id, ClasseDtoRequest classeDtoRequest){
        return classeRepository.findById(id)
                .map(classe -> {
                    classe.setName(classeDtoRequest.getName());
                    classe.setDescription(classeDtoRequest.getDescription());
                    var classeEntity = classeRepository.save(classe);
                    return Optional.of(classeMapper.toClasseDtoResponse(classeEntity));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("classe.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public boolean deleteClasse(Long id){
        if (classeRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(messageSource.getMessage("classe.notfound", new Object[]{id}, Locale.getDefault()));
        }
        classeRepository.deleteById(id);
        return true;
    }
}
