package com.dav.labs.program_service.sectors.services;


import com.dav.labs.program_service.sectors.dto.requests.SectorDtoRequest;
import com.dav.labs.program_service.sectors.dto.responses.SectorDtoResponse;

import java.util.List;
import java.util.Optional;

public interface ISectorService {
    Optional<SectorDtoResponse> saveSector(SectorDtoRequest sectorDtoRequest);
    Optional<List<SectorDtoResponse>> getAllSectors();
    Optional<SectorDtoResponse> getSectorById(Long sectorId);
    Optional<SectorDtoResponse> updateSector(Long sectorId, SectorDtoRequest sectorDtoRequest);
    boolean deleteSector(Long sectorId);
}
