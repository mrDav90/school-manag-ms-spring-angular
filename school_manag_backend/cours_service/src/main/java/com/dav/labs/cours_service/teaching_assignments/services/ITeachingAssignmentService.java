package com.dav.labs.cours_service.teaching_assignments.services;



import com.dav.labs.cours_service.teaching_assignments.dto.requests.TeachingAssignmentDtoRequest;
import com.dav.labs.cours_service.teaching_assignments.dto.responses.TeachingAssignmentDtoResponse;

import java.util.List;
import java.util.Optional;

public interface ITeachingAssignmentService {
    Optional<TeachingAssignmentDtoResponse> saveTeachingAssignment(TeachingAssignmentDtoRequest teachingAssignmentDtoRequest);
    Optional<List<TeachingAssignmentDtoResponse>> getAllTeachingAssignments();
    Optional<TeachingAssignmentDtoResponse> getTeachingAssignmentById(Long teachingAssignmentId);
    Optional<TeachingAssignmentDtoResponse> updateTeachingAssignment(Long teachingAssignmentId, TeachingAssignmentDtoRequest teachingAssignmentDtoRequest);
    boolean deleteTeachingAssignment(Long teachingAssignmentId);
}
