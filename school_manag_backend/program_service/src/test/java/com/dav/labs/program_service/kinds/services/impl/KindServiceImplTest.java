package com.dav.labs.program_service.kinds.services.impl;

import com.dav.labs.program_service.exception.EntityExistsException;
import com.dav.labs.program_service.exception.EntityNotFoundException;
import com.dav.labs.program_service.kinds.dto.requests.KindDtoRequest;
import com.dav.labs.program_service.kinds.dto.responses.KindDtoResponse;
import com.dav.labs.program_service.kinds.entities.KindEntity;
import com.dav.labs.program_service.kinds.mapper.KindMapper;
import com.dav.labs.program_service.kinds.repository.KindRepository;
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
public class KindServiceImplTest {
    @Mock
    private KindRepository kindRepository;
    @InjectMocks
    private KindServiceImpl kindService;
    @Mock
    private KindMapper kindMapper;
    @Mock
    private MessageSource messageSource;

    @Test
    void saveKindOK() {
        when(kindRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(kindMapper.toKindEntity(any())).thenReturn(getKindEntity());
        when(kindRepository.save(any())).thenReturn(getKindEntity());
        when(kindMapper.toKindDtoResponse(any())).thenReturn(getKindDtoResponse());

        Optional<KindDtoResponse> kindDtoResponse = kindService.saveKind(getKindDtoRequest());
        assertTrue(kindDtoResponse.isPresent());
    }

    @Test
    void saveKindKO() {
        when(kindRepository.findByName(anyString())).thenReturn(Optional.of(getKindEntity()));
        when(messageSource.getMessage(eq("kind.exists"), any(), any(Locale.class))).thenReturn("the kind with name = master is already created");

        EntityExistsException exception = assertThrows(EntityExistsException.class, () -> kindService.saveKind(getKindDtoRequest()));
        assertEquals("the kind with name = master is already created", exception.getMessage());
        assertNotNull(exception);
    }

    @Test
    void getAllKinds() {
        when(kindRepository.findAll()).thenReturn(List.of(getKindEntity()));
        when(kindMapper.toKindDtoResponseList(any())).thenReturn(List.of(getKindDtoResponse()));

        Optional<List<KindDtoResponse>> kinds = kindService.getAllKinds();
        assertTrue(kinds.isPresent());
        assertEquals(1, kinds.get().size());
    }

    @Test
    void getKindByIdOK() {
        when(kindRepository.findById(anyLong())).thenReturn(Optional.of(getKindEntity()));
        when(kindMapper.toKindDtoResponse(any())).thenReturn(getKindDtoResponse());

        Optional<KindDtoResponse> kind = kindService.getKindById(1L);
        assertTrue(kind.isPresent());
        assertEquals(1L, kind.get().getId());
    }

    @Test
    void getKindByIdKO() {
        when(kindRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("kind.notfound"), any(), any(Locale.class))).thenReturn("Kind with id=1 is not found");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> kindService.getKindById(1L));
        assertEquals("Kind with id=1 is not found", exception.getMessage());
    }

    @Test
    void updateKindOK() {
        when(kindRepository.findById(anyLong())).thenReturn(Optional.of(getKindEntity()));
        when(kindRepository.save(any())).thenReturn(getKindEntity());
        when(kindMapper.toKindDtoResponse(any())).thenReturn(getKindDtoResponse());

        Optional<KindDtoResponse> updatedKind = kindService.updateKind(1L, getKindDtoRequest());
        assertTrue(updatedKind.isPresent());
        assertEquals(1L, updatedKind.get().getId());
    }

    @Test
    void updateKindKO() {
        when(kindRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("kind.notfound"), any(), any(Locale.class)))
                .thenReturn("Kind not found");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> kindService.updateKind(1L ,getKindDtoRequest()));
        assertEquals("Kind not found", exception.getMessage());
    }

    @Test
    void deleteKindOK() {
        when(kindRepository.findById(anyLong())).thenReturn(Optional.of(getKindEntity()));
        boolean result = kindService.deleteKind(anyLong());
        assertTrue(result);
        verify(kindRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteKindKO() {
        when(kindRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("kind.notfound"), any(), any(Locale.class)))
                .thenReturn("Kind not found");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> kindService.deleteKind(1L));
        assertEquals("Kind not found", exception.getMessage());
    }

    private KindDtoRequest getKindDtoRequest(){
        KindDtoRequest kindDtoRequest = new KindDtoRequest();
        kindDtoRequest.setName("master");
        kindDtoRequest.setDescription("Niveau master");
        return kindDtoRequest;
    }
    private KindEntity getKindEntity(){
        KindEntity kindEntity = new KindEntity();
        kindEntity.setId(1L);
        kindEntity.setName("master");
        kindEntity.setDescription("Niveau master");
        return kindEntity;
    }
    private KindDtoResponse getKindDtoResponse(){
        KindDtoResponse kindDtoResponse = new KindDtoResponse();
        kindDtoResponse.setId(1L);
        kindDtoResponse.setName("master");
        kindDtoResponse.setDescription("Niveau master");
        return kindDtoResponse;
    }

}
