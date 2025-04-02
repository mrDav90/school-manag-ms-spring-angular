package com.dav.labs.users_service.registrations.mapper;

import com.dav.labs.users_service.registrations.dto.requests.RegistrationDtoRequest;
import com.dav.labs.users_service.registrations.dto.responses.RegistrationDtoResponse;
import com.dav.labs.users_service.registrations.entities.RegistrationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface RegistrationMapper {
    @Mapping(source = "studentId", target = "studentId")
    @Mapping(source = "classeId", target = "classeId")
    @Mapping(source = "academicYearId", target = "academicYearId")
    RegistrationEntity toRegistrationEntity(RegistrationDtoRequest registrationDtoRequest);
    RegistrationDtoResponse toRegistrationDtoResponse(RegistrationEntity registrationEntity);
    List<RegistrationEntity> toRegistrationEntityList(List<RegistrationDtoRequest> registrationDtoRequestList);
    List<RegistrationDtoResponse> toRegistrationDtoResponseList(List<RegistrationEntity> registrationEntityList);
}
