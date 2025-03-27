package com.dav.labs.program_service.sector.service;


import com.dav.labs.program_service.sector.dto.requests.SectorDtoRequest;
import com.dav.labs.program_service.sector.dto.responses.SectorDtoResponse;

import java.util.List;
import java.util.Optional;

public interface ISectorService {
    Optional<SectorDtoResponse> saveSector(SectorDtoRequest sectorDtoRequest);
    Optional<List<SectorDtoResponse>> getAllSectors();
    Optional<SectorDtoResponse> getSectorById(String sectorId);
    Optional<SectorDtoResponse> updateSector(String sectorId, SectorDtoRequest sectorDtoRequest);
    boolean deleteSector(String sectorId);
}
