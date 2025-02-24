package com.dav.labs.cours_service.classes.services;



import com.dav.labs.cours_service.classes.dto.requests.ClasseDtoRequest;
import com.dav.labs.cours_service.classes.dto.responses.ClasseDtoResponse;

import java.util.List;
import java.util.Optional;

public interface IClasseService {
    Optional<ClasseDtoResponse> saveClasse(ClasseDtoRequest classeDtoRequest);
    Optional<List<ClasseDtoResponse>> getAllClasses();
    Optional<ClasseDtoResponse> getClasseById(Long classeId);
    Optional<ClasseDtoResponse> updateClasse(Long classeId, ClasseDtoRequest classeDtoRequest);
    boolean deleteClasse(Long courseId);
}
