package com.dav.labs.program_service.classes.service.impl;

import com.dav.labs.program_service.classes.dto.requests.ClasseDtoRequest;
import com.dav.labs.program_service.classes.dto.responses.ClasseDtoResponse;
import com.dav.labs.program_service.classes.entities.ClasseEntity;
import com.dav.labs.program_service.classes.mapper.ClasseMapper;
import com.dav.labs.program_service.classes.repository.ClasseRepository;
import com.dav.labs.program_service.classes.service.IClasseService;
import com.dav.labs.program_service.exception.EntityExistsException;
import com.dav.labs.program_service.exception.EntityNotFoundException;
import com.dav.labs.program_service.sector.service.ISectorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ClasseServiceImpl implements IClasseService {
    private final ClasseRepository classeRepository;
    private final ISectorService sectorService;
    private final ClasseMapper classeMapper;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(ClasseServiceImpl.class);

    @Override
    public Optional<ClasseDtoResponse> saveClasse(ClasseDtoRequest classeDtoRequest){
        if (classeRepository.findByNameAndSectorId(classeDtoRequest.getName(), classeDtoRequest.getSectorId()).isPresent()) {
            throw new EntityExistsException(messageSource.getMessage("classe.exists", new Object[]{classeDtoRequest.getName(), sectorService.getSectorById(classeDtoRequest.getSectorId()).get().getName() }, Locale.getDefault()));
        }
        ClasseEntity classe = classeMapper.toClasseEntity(classeDtoRequest);
        logger.info("Classe (Département): {}", classe);
        ClasseEntity classeEntity = classeRepository.save(classe);
        ClasseDtoResponse classeDtoResponse = classeMapper.toClasseDtoResponse(classeEntity);
        return Optional.of(classeDtoResponse);
    }
    @Override
    public Optional<List<ClasseDtoResponse>> getAllClasses(){
        List<ClasseEntity> classesEntities = classeRepository.findAll().stream().map(elt -> {
            var sector = sectorService.getSectorById(elt.getSectorId()) ;
            String sectorName = "";
            if (sector.isPresent()) {
                sectorName = sector.get().getName();
            }
            elt.setSectorName(sectorName);
            return elt;
        }).collect(Collectors.toList());
        return Optional.of(classeMapper.toClasseDtoResponseList(classesEntities));
    }
    @Override
    public Optional<ClasseDtoResponse> getClasseById(String id){
        return classeRepository.findById(id)
                .map(classe -> {
                    var sector = sectorService.getSectorById(classe.getSectorId()) ;
                    String sectorName = "";
                    if (sector.isPresent()) {
                        sectorName = sector.get().getName();
                    }
                    classe.setSectorName(sectorName);
                    return Optional.of(classeMapper.toClasseDtoResponse(classe)) ;
                })
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("classe.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public Optional<ClasseDtoResponse> updateClasse(String id, ClasseDtoRequest classeDtoRequest){
        return classeRepository.findById(id)
                .map(classe -> {
                    classe.setName(classeDtoRequest.getName());
                    var classeEntity = classeRepository.save(classe);
                    return Optional.of(classeMapper.toClasseDtoResponse(classeEntity));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("classe.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public boolean deleteClasse(String id){
        if (classeRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(messageSource.getMessage("classe.notfound", new Object[]{id}, Locale.getDefault()));
        }
        classeRepository.deleteById(id);
        return true;
    }
}
