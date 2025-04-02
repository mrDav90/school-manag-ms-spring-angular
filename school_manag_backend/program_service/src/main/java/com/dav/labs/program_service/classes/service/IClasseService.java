package com.dav.labs.program_service.classes.service;


import com.dav.labs.program_service.classes.dto.requests.ClasseDtoRequest;
import com.dav.labs.program_service.classes.dto.responses.ClasseDtoResponse;

import java.util.List;
import java.util.Optional;

public interface IClasseService {
    Optional<ClasseDtoResponse> saveClasse(ClasseDtoRequest classeDtoRequest);
    Optional<List<ClasseDtoResponse>> getAllClasses();
    Optional<ClasseDtoResponse> getClasseById(String classeId);
    Optional<ClasseDtoResponse> updateClasse(String classeId, ClasseDtoRequest classeDtoRequest);
    boolean deleteClasse(String classeId);
}
