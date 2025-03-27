package com.dav.labs.program_service.niveau.mapper;


import com.dav.labs.program_service.niveau.dto.requests.NiveauDtoRequest;
import com.dav.labs.program_service.niveau.dto.responses.NiveauDtoResponse;
import com.dav.labs.program_service.niveau.entities.NiveauEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface NiveauMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "sectorId", target = "sectorId")
    NiveauEntity toNiveauEntity(NiveauDtoRequest niveauDtoRequest);
    NiveauDtoResponse toNiveauDtoResponse(NiveauEntity niveauEntity);
    List<NiveauEntity> toNiveauEntityList(List<NiveauDtoRequest> niveauDtoRequestList);
    List<NiveauDtoResponse> toNiveauDtoResponseList(List<NiveauEntity> niveauEntityList);
}
