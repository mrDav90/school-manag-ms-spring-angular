package com.dav.labs.program_service.sectors.controller;


import com.dav.labs.program_service.sectors.dto.requests.SectorDtoRequest;
import com.dav.labs.program_service.sectors.dto.responses.SectorDtoResponse;
import com.dav.labs.program_service.sectors.services.impl.SectorServiceImpl;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sectors")
//@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Getter
@Setter
public class SectorController {
    private final SectorServiceImpl sectorService;

    @GetMapping
    public ResponseEntity<List<SectorDtoResponse>> getAllSectors() {
        List<SectorDtoResponse> sectors = sectorService.getAllSectors().get();
        return new ResponseEntity<>(sectors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectorDtoResponse> getSector(@PathVariable("id") Long id){
        Optional<SectorDtoResponse> sector = sectorService.getSectorById(id);
        return new ResponseEntity<>(sector.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SectorDtoResponse> saveSector(@RequestBody @Valid SectorDtoRequest sectorDtoRequest){
        Optional<SectorDtoResponse> sectorDtoResponse = sectorService.saveSector(sectorDtoRequest);
        return new ResponseEntity<>(sectorDtoResponse.get(), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SectorDtoResponse> updateSector(@PathVariable("id") Long id, @RequestBody @Valid SectorDtoRequest sectorDtoRequest){
        Optional<SectorDtoResponse> sectorDtoResponse = sectorService.updateSector(id, sectorDtoRequest);
        return new ResponseEntity<>(sectorDtoResponse.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSector(@PathVariable("id") Long id){
        boolean result = sectorService.deleteSector(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
