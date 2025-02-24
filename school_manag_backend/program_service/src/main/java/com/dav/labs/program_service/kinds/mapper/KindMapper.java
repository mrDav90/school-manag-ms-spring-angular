package com.dav.labs.program_service.kinds.mapper;

import com.dav.labs.program_service.kinds.dto.requests.KindDtoRequest;
import com.dav.labs.program_service.kinds.dto.responses.KindDtoResponse;
import com.dav.labs.program_service.kinds.entities.KindEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface KindMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    KindEntity toKindEntity(KindDtoRequest kindDtoRequest);
    KindDtoResponse toKindDtoResponse(KindEntity studentEntity);
    List<KindEntity> toKindEntityList(List<KindDtoRequest> kindDtoRequestList);
    List<KindDtoResponse> toKindDtoResponseList(List<KindEntity> studentEntityList);
}
