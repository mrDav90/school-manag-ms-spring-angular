package com.dav.labs.program_service.academic_year.mapper;

import com.dav.labs.program_service.academic_year.dto.requests.AcademicYearDtoRequest;
import com.dav.labs.program_service.academic_year.dto.responses.AcademicYearDtoResponse;
import com.dav.labs.program_service.academic_year.entities.AcademicYearEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface AcademicYearMapper {
    @Mapping(source = "label", target = "label")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    AcademicYearEntity toAcademicYearEntity(AcademicYearDtoRequest academicYearDtoRequest);
    AcademicYearDtoResponse toAcademicYearDtoResponse(AcademicYearEntity academicYearEntity);
    List<AcademicYearEntity> toAcademicYearEntityList(List<AcademicYearDtoRequest> academicYearDtoRequestList);
    List<AcademicYearDtoResponse> toAcademicYearDtoResponseList(List<AcademicYearEntity> academicYearEntityList);
}
