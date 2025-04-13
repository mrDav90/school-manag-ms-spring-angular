package com.dav.labs.users_service.students.mapper;

import com.dav.labs.users_service.students.dto.requests.StudentDtoRequest;
import com.dav.labs.users_service.students.dto.responses.StudentDtoResponse;
import com.dav.labs.users_service.students.entities.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "placeOfBirth", target = "placeOfBirth")
    @Mapping(source = "emailPerso", target = "emailPerso")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "nationality", target = "nationality")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "profilePicture", target = "profilePicture")
    @Mapping(source = "document", target = "document")
    @Mapping(source = "tutorFullName", target = "tutorFullName")
    @Mapping(source = "tutorPhoneNumber", target = "tutorPhoneNumber")
    StudentEntity toStudentEntity(StudentDtoRequest studentDtoRequest);
    StudentDtoResponse toStudentDtoResponse(StudentEntity studentEntity);
    List<StudentEntity> toStudentEntityList(List<StudentDtoRequest> studentDtoRequestList);
    List<StudentDtoResponse> toStudentDtoResponseList(List<StudentEntity> studentEntityList);
}
