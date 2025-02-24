package com.dav.labs.cours_service.classes.mapper;

import com.dav.labs.cours_service.classes.dto.requests.ClasseDtoRequest;
import com.dav.labs.cours_service.classes.dto.responses.ClasseDtoResponse;
import com.dav.labs.cours_service.classes.entities.ClasseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ClasseMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    ClasseEntity toClasseEntity(ClasseDtoRequest classeDtoRequest);
    ClasseDtoResponse toClasseDtoResponse(ClasseEntity classeEntity);
    List<ClasseEntity> toClasseEntityList(List<ClasseDtoRequest> classeDtoRequestList);
    List<ClasseDtoResponse> toClasseDtoResponseList(List<ClasseEntity> classeEntityList);
}
