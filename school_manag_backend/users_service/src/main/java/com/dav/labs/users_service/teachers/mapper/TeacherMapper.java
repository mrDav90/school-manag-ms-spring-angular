package com.dav.labs.users_service.teachers.mapper;

import com.dav.labs.users_service.teachers.dto.requests.TeacherDtoRequest;
import com.dav.labs.users_service.teachers.dto.responses.TeacherDtoResponse;
import com.dav.labs.users_service.teachers.entities.TeacherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TeacherMapper {
    //@Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "emailPro", target = "emailPro")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "teachingAssignments", target = "teachingAssignments")
    TeacherEntity toTeacherEntity(TeacherDtoRequest teacherDtoRequest);
    TeacherDtoResponse toTeacherDtoResponse(TeacherEntity teacherEntity);
    List<TeacherEntity> toTeacherEntityList(List<TeacherDtoRequest> teacherDtoRequestList);
    List<TeacherDtoResponse> toTeacherDtoResponseList(List<TeacherEntity> teacherEntityList);
}
