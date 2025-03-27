package com.dav.labs.program_service.niveau.service;


import com.dav.labs.program_service.niveau.dto.requests.NiveauDtoRequest;
import com.dav.labs.program_service.niveau.dto.responses.NiveauDtoResponse;

import java.util.List;
import java.util.Optional;

public interface INiveauService {
    Optional<NiveauDtoResponse> saveNiveau(NiveauDtoRequest niveauDtoRequest);
    Optional<List<NiveauDtoResponse>> getAllNiveaux();
    Optional<NiveauDtoResponse> getNiveauById(String niveauId);
    Optional<NiveauDtoResponse> updateNiveau(String niveauId, NiveauDtoRequest niveauDtoRequest);
    boolean deleteNiveau(String niveauId);
}
