package com.dav.labs.cours_service.teaching_assignments.mapper;

import com.dav.labs.cours_service.teaching_assignments.dto.requests.TeachingAssignmentDtoRequest;
import com.dav.labs.cours_service.teaching_assignments.dto.responses.TeachingAssignmentDtoResponse;
import com.dav.labs.cours_service.teaching_assignments.entities.TeachingAssignmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TeachingAssignmentMapper {
    @Mapping(source = "teacherId", target = "teacherId")
    @Mapping(source = "courseId", target = "courseId")
    @Mapping(source = "classeId", target = "classeId")
    TeachingAssignmentEntity toTeachingAssignmentEntity(TeachingAssignmentDtoRequest teachingAssignmentDtoRequest);
    TeachingAssignmentDtoResponse toTeachingAssignmentDtoResponse(TeachingAssignmentEntity teachingAssignmentEntity);
    List<TeachingAssignmentEntity> toTeachingAssignmentEntityList(List<TeachingAssignmentDtoRequest> teachingAssignmentDtoRequestList);
    List<TeachingAssignmentDtoResponse> toTeachingAssignmentDtoResponseList(List<TeachingAssignmentEntity> teachingAssignmentEntityList);
}
