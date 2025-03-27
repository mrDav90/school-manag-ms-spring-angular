package com.dav.labs.program_service.academic_year.service.impl;

import com.dav.labs.program_service.academic_year.dto.requests.AcademicYearDtoRequest;
import com.dav.labs.program_service.academic_year.dto.responses.AcademicYearDtoResponse;
import com.dav.labs.program_service.academic_year.entities.AcademicYearEntity;
import com.dav.labs.program_service.exception.EntityExistsException;
import com.dav.labs.program_service.exception.EntityNotFoundException;
import com.dav.labs.program_service.academic_year.mapper.AcademicYearMapper;
import com.dav.labs.program_service.academic_year.repository.AcademicYearRepository;
import com.dav.labs.program_service.academic_year.service.IAcademicYearService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AcademicYearServiceImpl implements IAcademicYearService {
    private final AcademicYearRepository academicYearRepository;
    private final AcademicYearMapper academicYearMapper;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(AcademicYearServiceImpl.class);

    @Override
    public Optional<AcademicYearDtoResponse> saveAcademicYear(AcademicYearDtoRequest academicYearDtoRequest){
        if (academicYearRepository.findByLabel(academicYearDtoRequest.getLabel()).isPresent()) {
            throw new EntityExistsException(messageSource.getMessage("academicYear.exists", new Object[]{academicYearDtoRequest.getLabel()}, Locale.getDefault()));
        }
        AcademicYearEntity academicYear = academicYearMapper.toAcademicYearEntity(academicYearDtoRequest);
        logger.info("Academic Year: {}", academicYear);
        AcademicYearEntity academicYearEntity = academicYearRepository.save(academicYear);
        AcademicYearDtoResponse academicYearDtoResponse = academicYearMapper.toAcademicYearDtoResponse(academicYearEntity);
        return Optional.of(academicYearDtoResponse);
    }
    @Override
    public Optional<List<AcademicYearDtoResponse>> getAllAcademicYears(){
        List<AcademicYearEntity> academicYearsEntities = academicYearRepository.findAll().stream().map(academicYearEntity -> {
            boolean isCurrent = LocalDateTime.now().getYear() == academicYearEntity.getStartDate().getYear() || LocalDateTime.now().getYear() == academicYearEntity.getEndDate().getYear();
            academicYearEntity.setIsCurrent(isCurrent);
            return academicYearEntity;
        }).collect(Collectors.toList());
        return Optional.of(academicYearMapper.toAcademicYearDtoResponseList(academicYearsEntities));
    }
    @Override
    public Optional<AcademicYearDtoResponse> getAcademicYearById(String id){
        return academicYearRepository.findById(id)
                .map(academicYear -> {
                    var isCurrent = LocalDateTime.now().getYear() == academicYear.getStartDate().getYear() || LocalDateTime.now().getYear() == academicYear.getEndDate().getYear();
                    academicYear.setIsCurrent(isCurrent);
                    return academicYearMapper.toAcademicYearDtoResponse(academicYear);
                })
                .map(Optional::of)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("academicYear.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public Optional<AcademicYearDtoResponse> updateAcademicYear(String id, AcademicYearDtoRequest academicYearDtoRequest){
        return academicYearRepository.findById(id)
                .map(academicYear -> {
                    academicYear.setLabel(academicYearDtoRequest.getLabel());
                    academicYear.setStartDate(academicYearDtoRequest.getStartDate());
                    academicYear.setEndDate(academicYearDtoRequest.getEndDate());
                    var academicYearEntity = academicYearRepository.save(academicYear);
                    return Optional.of(academicYearMapper.toAcademicYearDtoResponse(academicYearEntity));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("academicYear.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Override
    public boolean deleteAcademicYear(String id){
        if (academicYearRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(messageSource.getMessage("academicYear.notfound", new Object[]{id}, Locale.getDefault()));
        }
        academicYearRepository.deleteById(id);
        return true;
    }
}
