package com.dav.labs.program_service.academic_year.service.impl;

import com.dav.labs.program_service.academic_year.dto.requests.AcademicYearDtoRequest;
import com.dav.labs.program_service.academic_year.dto.responses.AcademicYearDtoResponse;
import com.dav.labs.program_service.academic_year.entities.AcademicYearEntity;
import com.dav.labs.program_service.exception.EntityExistsException;
import com.dav.labs.program_service.exception.EntityNotFoundException;
import com.dav.labs.program_service.academic_year.mapper.AcademicYearMapper;
import com.dav.labs.program_service.academic_year.repository.AcademicYearRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AcademicYearServiceImplTest {
    @Mock
    private AcademicYearRepository academicYearRepository;
    @InjectMocks
    private AcademicYearServiceImpl academicYearService;
    @Mock
    private AcademicYearMapper academicYearMapper;
    @Mock
    private MessageSource messageSource;

    @Test
    void saveAcademicYearOK() {
        when(academicYearRepository.findByLabel(anyString())).thenReturn(Optional.empty());
        when(academicYearMapper.toAcademicYearEntity(any())).thenReturn(getAcademicYearEntity());
        when(academicYearRepository.save(any())).thenReturn(getAcademicYearEntity());
        when(academicYearMapper.toAcademicYearDtoResponse(any())).thenReturn(getAcademicYearDtoResponse());

        Optional<AcademicYearDtoResponse> academicYearDtoResponse = academicYearService.saveAcademicYear(getAcademicYearDtoRequest());
        assertTrue(academicYearDtoResponse.isPresent());
    }

    @Test
    void saveAcademicYearKO() {
        when(academicYearRepository.findByLabel(anyString())).thenReturn(Optional.of(getAcademicYearEntity()));
        when(messageSource.getMessage(eq("academicYear.exists"), any(), any(Locale.class))).thenReturn("the Academic Year with name = 2025-2026 is already created");

        EntityExistsException exception = assertThrows(EntityExistsException.class, () -> academicYearService.saveAcademicYear(getAcademicYearDtoRequest()));
        assertEquals("the Academic Year with name = 2025-2026 is already created", exception.getMessage());
        assertNotNull(exception);
    }

    @Test
    void getAllAcademicYears() {
        when(academicYearRepository.findAll()).thenReturn(List.of(getAcademicYearEntity()));
        when(academicYearMapper.toAcademicYearDtoResponseList(anyList())).thenReturn(List.of(getAcademicYearDtoResponse()));

        Optional<List<AcademicYearDtoResponse>> academicYears = academicYearService.getAllAcademicYears();
        assertTrue(academicYears.isPresent());
        assertEquals(1, academicYears.get().size());
    }

    @Test
    void getAcademicYearByIdOK() {
        when(academicYearRepository.findById("xyz")).thenReturn(Optional.of(getAcademicYearEntity()));
        when(academicYearMapper.toAcademicYearDtoResponse(any())).thenReturn(getAcademicYearDtoResponse());

        Optional<AcademicYearDtoResponse> academicYear = academicYearService.getAcademicYearById("xyz");
        assertTrue(academicYear.isPresent());
        assertEquals("xyz", academicYear.get().getId());
    }

    @Test
    void getAcademicYearByIdKO() {
        when(academicYearRepository.findById(anyString())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("academicYear.notfound"), any(), any(Locale.class))).thenReturn("AcademicYear with id=xyz is not found");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> academicYearService.getAcademicYearById("xyz"));
        assertEquals("AcademicYear with id=xyz is not found", exception.getMessage());
    }

    @Test
    void updateAcademicYearOK() {
        when(academicYearRepository.findById(anyString())).thenReturn(Optional.of(getAcademicYearEntity()));
        when(academicYearRepository.save(any())).thenReturn(getAcademicYearEntity());
        when(academicYearMapper.toAcademicYearDtoResponse(any())).thenReturn(getAcademicYearDtoResponse());

        Optional<AcademicYearDtoResponse> updatedAcademicYear = academicYearService.updateAcademicYear("xyz", getAcademicYearDtoRequest());
        assertTrue(updatedAcademicYear.isPresent());
        assertEquals("xyz", updatedAcademicYear.get().getId());
    }

    @Test
    void updateAcademicYearKO() {
        when(academicYearRepository.findById(anyString())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("academicYear.notfound"), any(), any(Locale.class)))
                .thenReturn("AcademicYear not found");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> academicYearService.updateAcademicYear("xyz" ,getAcademicYearDtoRequest()));
        assertEquals("AcademicYear not found", exception.getMessage());
    }

    @Test
    void deleteAcademicYearOK() {
        when(academicYearRepository.findById(anyString())).thenReturn(Optional.of(getAcademicYearEntity()));
        boolean result = academicYearService.deleteAcademicYear(anyString());
        assertTrue(result);
        verify(academicYearRepository, times(1)).deleteById(anyString());
    }

    @Test
    void deleteAcademicYearKO() {
        when(academicYearRepository.findById(anyString())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("academicYear.notfound"), any(), any(Locale.class)))
                .thenReturn("AcademicYear not found");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> academicYearService.deleteAcademicYear("xyz"));
        assertEquals("AcademicYear not found", exception.getMessage());
    }

    private AcademicYearDtoRequest getAcademicYearDtoRequest(){
        AcademicYearDtoRequest academicYearDtoRequest = new AcademicYearDtoRequest();
        academicYearDtoRequest.setLabel("2025-2026");
        academicYearDtoRequest.setStartDate(LocalDateTime.of(2025,10,10,10,0,0));
        academicYearDtoRequest.setEndDate(LocalDateTime.of(2026,7,20,18,0,0));
        return academicYearDtoRequest;
    }
    private AcademicYearEntity getAcademicYearEntity(){
        AcademicYearEntity academicYearEntity = new AcademicYearEntity();
        academicYearEntity.setId("xyz");
        academicYearEntity.setLabel("2025-2026");
        academicYearEntity.setStartDate(LocalDateTime.of(2025,10,10, 10,0,0));
        academicYearEntity.setEndDate(LocalDateTime.of(2026,7,20, 18,0,0));
        academicYearEntity.setIsCurrent(true);
        academicYearEntity.setArchive(false);
        return academicYearEntity;
    }
    private AcademicYearDtoResponse getAcademicYearDtoResponse(){
        AcademicYearDtoResponse academicYearDtoResponse = new AcademicYearDtoResponse();
        academicYearDtoResponse.setId("xyz");
        academicYearDtoResponse.setLabel("2025-2026");
        academicYearDtoResponse.setStartDate(LocalDate.of(2025,10,10));
        academicYearDtoResponse.setEndDate(LocalDate.of(2026,7,20));
        academicYearDtoResponse.setCurrent(true);
        academicYearDtoResponse.setArchive(false);
        return academicYearDtoResponse;
    }
}
