package com.dav.labs.cours_service.subjects.services;



import com.dav.labs.cours_service.subjects.dto.requests.SubjectDtoRequest;
import com.dav.labs.cours_service.subjects.dto.responses.SubjectDtoResponse;

import java.util.List;
import java.util.Optional;

public interface ISubjectService {
    Optional<SubjectDtoResponse> saveSubject(SubjectDtoRequest subjectDtoRequest);
    Optional<List<SubjectDtoResponse>> getAllSubjects();
    Optional<SubjectDtoResponse> getSubjectById(String subjectId);
    Optional<SubjectDtoResponse> updateSubject(String subjectId, SubjectDtoRequest subjectDtoRequest);
    boolean deleteSubject(String subjectId);
}
