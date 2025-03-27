package com.dav.labs.program_service.sector.mapper;


import com.dav.labs.program_service.sector.dto.requests.SectorDtoRequest;
import com.dav.labs.program_service.sector.dto.responses.SectorDtoResponse;
import com.dav.labs.program_service.sector.entities.SectorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface SectorMapper {
    @Mapping(source = "name", target = "name")
    SectorEntity toSectorEntity(SectorDtoRequest sectorDtoRequest);
    SectorDtoResponse toSectorDtoResponse(SectorEntity sectorEntity);
    List<SectorEntity> toSectorEntityList(List<SectorDtoRequest> sectorDtoRequestList);
    List<SectorDtoResponse> toSectorDtoResponseList(List<SectorEntity> sectorEntityList);
}
