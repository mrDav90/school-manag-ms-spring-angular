package com.dav.labs.program_service.sectors.services.impl;

import com.dav.labs.program_service.sectors.dto.requests.SectorDtoRequest;
import com.dav.labs.program_service.sectors.dto.responses.SectorDtoResponse;
import com.dav.labs.program_service.sectors.entities.SectorEntity;
import com.dav.labs.program_service.sectors.mapper.SectorMapper;
import com.dav.labs.program_service.sectors.repository.SectorRepository;
import com.dav.labs.program_service.exception.EntityExistsException;
import com.dav.labs.program_service.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SectorServiceImplTest {
    @Mock
    private SectorRepository sectorRepository;
    @InjectMocks
    private SectorServiceImpl sectorService;
    @Mock
    private SectorMapper sectorMapper;
    @Mock
    private MessageSource messageSource;

    @Test
    void saveSectorOK() {
        when(sectorRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(sectorMapper.toSectorEntity(any())).thenReturn(getSectorEntity());
        when(sectorRepository.save(any())).thenReturn(getSectorEntity());
        when(sectorMapper.toSectorDtoResponse(any())).thenReturn(getSectorDtoResponse());

        Optional<SectorDtoResponse> sectorDtoResponse = sectorService.saveSector(getSectorDtoRequest());
        assertTrue(sectorDtoResponse.isPresent());
    }

    @Test
    void saveSectorKO() {
        when(sectorRepository.findByName(anyString())).thenReturn(Optional.of(getSectorEntity()));
        when(messageSource.getMessage(eq("sector.exists"), any(), any(Locale.class))).thenReturn("the sector with name = GL is already created");

        EntityExistsException exception = assertThrows(EntityExistsException.class, () -> sectorService.saveSector(getSectorDtoRequest()));
        assertEquals("the sector with name = GL is already created", exception.getMessage());
        assertNotNull(exception);
    }

    @Test
    void getAllSectors() {
        when(sectorRepository.findAll()).thenReturn(List.of(getSectorEntity()));
        when(sectorMapper.toSectorDtoResponseList(any())).thenReturn(List.of(getSectorDtoResponse()));

        Optional<List<SectorDtoResponse>> sectors = sectorService.getAllSectors();
        assertTrue(sectors.isPresent());
        assertEquals(1, sectors.get().size());
    }

    @Test
    void getSectorByIdOK() {
        when(sectorRepository.findById(anyLong())).thenReturn(Optional.of(getSectorEntity()));
        when(sectorMapper.toSectorDtoResponse(any())).thenReturn(getSectorDtoResponse());

        Optional<SectorDtoResponse> sector = sectorService.getSectorById(1L);
        assertTrue(sector.isPresent());
        assertEquals(1L, sector.get().getId());
    }

    @Test
    void getSectorByIdKO() {
        when(sectorRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("sector.notfound"), any(), any(Locale.class))).thenReturn("Sector with id=1 is not found");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> sectorService.getSectorById(1L));
        assertEquals("Sector with id=1 is not found", exception.getMessage());
    }

    @Test
    void updateSectorOK() {
        when(sectorRepository.findById(anyLong())).thenReturn(Optional.of(getSectorEntity()));
        when(sectorRepository.save(any())).thenReturn(getSectorEntity());
        when(sectorMapper.toSectorDtoResponse(any())).thenReturn(getSectorDtoResponse());

        Optional<SectorDtoResponse> updatedSector = sectorService.updateSector(1L, getSectorDtoRequest());
        assertTrue(updatedSector.isPresent());
        assertEquals(1L, updatedSector.get().getId());
    }

    @Test
    void updateSectorKO() {
        when(sectorRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("sector.notfound"), any(), any(Locale.class)))
                .thenReturn("Sector not found");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> sectorService.updateSector(1L ,getSectorDtoRequest()));
        assertEquals("Sector not found", exception.getMessage());
    }

    @Test
    void deleteSectorOK() {
        when(sectorRepository.findById(anyLong())).thenReturn(Optional.of(getSectorEntity()));
        boolean result = sectorService.deleteSector(anyLong());
        assertTrue(result);
        verify(sectorRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteSectorKO() {
        when(sectorRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("sector.notfound"), any(), any(Locale.class)))
                .thenReturn("Sector not found");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> sectorService.deleteSector(1L));
        assertEquals("Sector not found", exception.getMessage());
    }

    private SectorDtoRequest getSectorDtoRequest(){
        SectorDtoRequest sectorDtoRequest = new SectorDtoRequest();
        sectorDtoRequest.setName("GL");
        return sectorDtoRequest;
    }
    private SectorEntity getSectorEntity(){
        SectorEntity sectorEntity = new SectorEntity();
        sectorEntity.setId(1L);
        sectorEntity.setName("GL");
        return sectorEntity;
    }
    private SectorDtoResponse getSectorDtoResponse(){
        SectorDtoResponse sectorDtoResponse = new SectorDtoResponse();
        sectorDtoResponse.setId(1L);
        sectorDtoResponse.setName("GL");
        return sectorDtoResponse;
    }

}
