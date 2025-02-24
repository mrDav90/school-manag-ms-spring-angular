package com.dav.labs.cours_service.courses.mapper;

import com.dav.labs.cours_service.courses.dto.requests.CourseDtoRequest;
import com.dav.labs.cours_service.courses.dto.responses.CourseDtoResponse;
import com.dav.labs.cours_service.courses.entities.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    CourseEntity toCourseEntity(CourseDtoRequest courseDtoRequest);
    CourseDtoResponse toCourseDtoResponse(CourseEntity courseEntity);
    List<CourseEntity> toCourseEntityList(List<CourseDtoRequest> courseDtoRequestList);
    List<CourseDtoResponse> toCourseDtoResponseList(List<CourseEntity> courseEntityList);
}
