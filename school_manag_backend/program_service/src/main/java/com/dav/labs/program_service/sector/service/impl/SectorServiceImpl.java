package com.dav.labs.program_service.sector.service.impl;

import com.dav.labs.program_service.sector.dto.requests.SectorDtoRequest;
import com.dav.labs.program_service.sector.dto.responses.SectorDtoResponse;
import com.dav.labs.program_service.sector.entities.SectorEntity;
import com.dav.labs.program_service.sector.mapper.SectorMapper;
import com.dav.labs.program_service.sector.repository.SectorRepository;
import com.dav.labs.program_service.sector.service.ISectorService;
import com.dav.labs.program_service.exception.EntityExistsException;
import com.dav.labs.program_service.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class SectorServiceImpl implements ISectorService {
    private final SectorRepository sectorRepository;
    private final SectorMapper sectorMapper;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(SectorServiceImpl.class);

    @Override
    public Optional<SectorDtoResponse> saveSector(SectorDtoRequest sectorDtoRequest){
        if (sectorRepository.findByName(sectorDtoRequest.getName()).isPresent()) {
            throw new EntityExistsException(messageSource.getMessage("sector.exists", new Object[]{sectorDtoRequest.getName()}, Locale.getDefault()));
        }
        SectorEntity sector = sectorMapper.toSectorEntity(sectorDtoRequest);
        logger.info("Sector (Département): {}", sector);
        SectorEntity sectorEntity = sectorRepository.save(sector);
        SectorDtoResponse sectorDtoResponse = sectorMapper.toSectorDtoResponse(sectorEntity);
        return Optional.of(sectorDtoResponse);
    }
    @Override
    public Optional<List<SectorDtoResponse>> getAllSectors(){
        List<SectorEntity> sectorsEntities = sectorRepository.findAll();
        return Optional.of(sectorMapper.toSectorDtoResponseList(sectorsEntities));
    }
    @Override
    public Optional<SectorDtoResponse> getSectorById(String id){
        return sectorRepository.findById(id)
                .map(sector -> Optional.of(sectorMapper.toSectorDtoResponse(sector)))
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("sector.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public Optional<SectorDtoResponse> updateSector(String id, SectorDtoRequest sectorDtoRequest){
        return sectorRepository.findById(id)
                .map(sector -> {
                    sector.setName(sectorDtoRequest.getName());
                    var sectorEntity = sectorRepository.save(sector);
                    return Optional.of(sectorMapper.toSectorDtoResponse(sectorEntity));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("sector.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public boolean deleteSector(String id){
        if (sectorRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(messageSource.getMessage("sector.notfound", new Object[]{id}, Locale.getDefault()));
        }
        sectorRepository.deleteById(id);
        return true;
    }
}
