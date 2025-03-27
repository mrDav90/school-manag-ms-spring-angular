package com.dav.labs.program_service.niveau.service.impl;

import com.dav.labs.program_service.exception.EntityExistsException;
import com.dav.labs.program_service.exception.EntityNotFoundException;
import com.dav.labs.program_service.niveau.dto.requests.NiveauDtoRequest;
import com.dav.labs.program_service.niveau.dto.responses.NiveauDtoResponse;
import com.dav.labs.program_service.niveau.entities.NiveauEntity;
import com.dav.labs.program_service.niveau.mapper.NiveauMapper;
import com.dav.labs.program_service.niveau.repository.NiveauRepository;
import com.dav.labs.program_service.niveau.service.INiveauService;
import com.dav.labs.program_service.sector.entities.SectorEntity;
import com.dav.labs.program_service.sector.repository.SectorRepository;
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
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class NiveauServiceImpl implements INiveauService {
    private final NiveauRepository niveauRepository;
    private final ISectorService sectorService;
    private final NiveauMapper niveauMapper;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(NiveauServiceImpl.class);

    @Override
    public Optional<NiveauDtoResponse> saveNiveau(NiveauDtoRequest niveauDtoRequest){
        if (niveauRepository.findByNameAndSectorId(niveauDtoRequest.getName(), niveauDtoRequest.getSectorId()).isPresent()) {
            throw new EntityExistsException(messageSource.getMessage("niveau.exists", new Object[]{niveauDtoRequest.getName(), niveauDtoRequest.getSectorId() }, Locale.getDefault()));
        }
        NiveauEntity niveau = niveauMapper.toNiveauEntity(niveauDtoRequest);
        logger.info("Niveau (Département): {}", niveau);
        NiveauEntity niveauEntity = niveauRepository.save(niveau);
        NiveauDtoResponse niveauDtoResponse = niveauMapper.toNiveauDtoResponse(niveauEntity);
        return Optional.of(niveauDtoResponse);
    }
    @Override
    public Optional<List<NiveauDtoResponse>> getAllNiveaux(){
        List<NiveauEntity> niveauxEntities = niveauRepository.findAll().stream().map(elt -> {
            var sector = sectorService.getSectorById(elt.getSectorId()) ;
            String sectorName = "";
            if (sector.isPresent()) {
                sectorName = sector.get().getName();
            }
            elt.setSectorName(sectorName);
            return elt;
        }).collect(Collectors.toList());
        return Optional.of(niveauMapper.toNiveauDtoResponseList(niveauxEntities));
    }
    @Override
    public Optional<NiveauDtoResponse> getNiveauById(String id){
        return niveauRepository.findById(id)
                .map(niveau -> Optional.of(niveauMapper.toNiveauDtoResponse(niveau)))
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("niveau.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public Optional<NiveauDtoResponse> updateNiveau(String id, NiveauDtoRequest niveauDtoRequest){
        return niveauRepository.findById(id)
                .map(niveau -> {
                    niveau.setName(niveauDtoRequest.getName());
                    var niveauEntity = niveauRepository.save(niveau);
                    return Optional.of(niveauMapper.toNiveauDtoResponse(niveauEntity));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("niveau.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public boolean deleteNiveau(String id){
        if (niveauRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(messageSource.getMessage("niveau.notfound", new Object[]{id}, Locale.getDefault()));
        }
        niveauRepository.deleteById(id);
        return true;
    }
}
