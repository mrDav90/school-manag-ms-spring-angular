package com.dav.labs.cours_service.subjects.mapper;

import com.dav.labs.cours_service.subjects.dto.requests.SubjectDtoRequest;
import com.dav.labs.cours_service.subjects.dto.responses.SubjectDtoResponse;
import com.dav.labs.cours_service.subjects.entities.SubjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface SubjectMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    SubjectEntity toSubjectEntity(SubjectDtoRequest subjectDtoRequest);
    SubjectDtoResponse toSubjectDtoResponse(SubjectEntity subjectEntity);
    List<SubjectEntity> toSubjectEntityList(List<SubjectDtoRequest> subjectDtoRequestList);
    List<SubjectDtoResponse> toSubjectDtoResponseList(List<SubjectEntity> subjectEntityList);
}
