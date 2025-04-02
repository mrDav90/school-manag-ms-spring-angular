package com.dav.labs.users_service.teachers.impl;

import com.dav.labs.users_service.exception.EntityExistsException;
import com.dav.labs.users_service.exception.EntityNotFoundException;
import com.dav.labs.users_service.teachers.dto.requests.TeacherDtoRequest;
import com.dav.labs.users_service.teachers.dto.responses.TeacherDtoResponse;
import com.dav.labs.users_service.teachers.entities.TeacherEntity;
import com.dav.labs.users_service.teachers.mapper.TeacherMapper;
import com.dav.labs.users_service.teachers.repository.TeacherRepository;
import com.dav.labs.users_service.teachers.services.impl.TeacherServiceImpl;
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
class TeacherServiceImplTest {
    @Mock
    private TeacherRepository teacherRepository;
    @InjectMocks
    private TeacherServiceImpl teacherService;
    @Mock
    private TeacherMapper teacherMapper;
    @Mock
    private MessageSource messageSource;

    @Test
    void saveTeacherOK() {
        when(teacherRepository.findByEmailPro(anyString())).thenReturn(Optional.empty());
        when(teacherMapper.toTeacherEntity(any())).thenReturn(getTeacherEntity());
        when(teacherRepository.save(any())).thenReturn(getTeacherEntity());
        when(teacherMapper.toTeacherDtoResponse(any())).thenReturn(getTeacherDtoResponse());

        Optional<TeacherDtoResponse> teacherDtoResponse = teacherService.saveTeacher(getTeacherDtoRequest());
        assertTrue(teacherDtoResponse.isPresent());
    }

    @Test
    void saveTeacherKO() {
        when(teacherRepository.findByEmailPro(anyString())).thenReturn(Optional.of(getTeacherEntity()));
        when(messageSource.getMessage(eq("teacher.exists"), any(), any(Locale.class))).thenReturn("the teacher with email = david@gmail.com is already created");

        EntityExistsException exception = assertThrows(EntityExistsException.class, () -> teacherService.saveTeacher(getTeacherDtoRequest()));
        assertEquals("the teacher with email = david@gmail.com is already created", exception.getMessage());
        assertNotNull(exception);
    }

    @Test
    void getAllTeachers() {
        when(teacherRepository.findAll()).thenReturn(List.of(getTeacherEntity()));
        when(teacherMapper.toTeacherDtoResponseList(any())).thenReturn(List.of(getTeacherDtoResponse()));

        Optional<List<TeacherDtoResponse>> teachers = teacherService.getAllTeachers();
        assertTrue(teachers.isPresent());
        assertEquals(1, teachers.get().size());
    }

    @Test
    void getTeacherByIdOK() {
        when(teacherRepository.findById(anyString())).thenReturn(Optional.of(getTeacherEntity()));
        when(teacherMapper.toTeacherDtoResponse(any())).thenReturn(getTeacherDtoResponse());

        Optional<TeacherDtoResponse> teacher = teacherService.getTeacherById("1");
        assertTrue(teacher.isPresent());
        assertEquals("1", teacher.get().getId());
    }

    @Test
    void getTeacherByIdKO() {
        when(teacherRepository.findById(anyString())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("teacher.notfound"), any(), any(Locale.class))).thenReturn("Teacher with id=1 is not found");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> teacherService.getTeacherById("1"));
        assertEquals("Teacher with id=1 is not found", exception.getMessage());
    }

    @Test
    void updateTeacherOK() {
        when(teacherRepository.findById(anyString())).thenReturn(Optional.of(getTeacherEntity()));
        when(teacherRepository.save(any())).thenReturn(getTeacherEntity());
        when(teacherMapper.toTeacherDtoResponse(any())).thenReturn(getTeacherDtoResponse());

        Optional<TeacherDtoResponse> updatedTeacher = teacherService.updateTeacher("1", getTeacherDtoRequest());
        assertTrue(updatedTeacher.isPresent());
        assertEquals("1", updatedTeacher.get().getId());
    }

    @Test
    void updateTeacherKO() {
        when(teacherRepository.findById(anyString())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("teacher.notfound"), any(), any(Locale.class)))
                .thenReturn("Teacher not found");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> teacherService.updateTeacher("1" ,getTeacherDtoRequest()));
        assertEquals("Teacher not found", exception.getMessage());
    }

    @Test
    void deleteTeacherOK() {
        when(teacherRepository.findById(anyString())).thenReturn(Optional.of(getTeacherEntity()));
        boolean result = teacherService.deleteTeacher(anyString());
        assertTrue(result);
        verify(teacherRepository, times(1)).deleteById(anyString());
    }

    @Test
    void deleteTeacherKO() {
        when(teacherRepository.findById(anyString())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("teacher.notfound"), any(), any(Locale.class)))
                .thenReturn("Teacher not found");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> teacherService.deleteTeacher("1"));
        assertEquals("Teacher not found", exception.getMessage());
    }

    private TeacherDtoRequest getTeacherDtoRequest(){
        TeacherDtoRequest teacherDtoRequest = new TeacherDtoRequest();
        teacherDtoRequest.setFirstName("ngor");
        teacherDtoRequest.setLastName("seck");
        teacherDtoRequest.setEmailPro("ngorseck@groupeisi.com");
        teacherDtoRequest.setPhoneNumber("771232211");
        teacherDtoRequest.setAddress("madrid");
        return teacherDtoRequest;
    }
    private TeacherEntity getTeacherEntity(){
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setId("1");
        teacherEntity.setFirstName("ngor");
        teacherEntity.setLastName("seck");
        teacherEntity.setEmailPro("ngorseck@groupeisi.com");
        teacherEntity.setPhoneNumber("771232211");
        teacherEntity.setAddress("madrid");
        teacherEntity.setArchive(false);
        return teacherEntity;
    }
    private TeacherDtoResponse getTeacherDtoResponse(){
        TeacherDtoResponse teacherDtoResponse = new TeacherDtoResponse();
        teacherDtoResponse.setId("1");
        teacherDtoResponse.setFirstName("ngor");
        teacherDtoResponse.setLastName("seck");
        teacherDtoResponse.setEmailPro("ngorseck@groupeisi.com");
        teacherDtoResponse.setPhoneNumber("771232211");
        teacherDtoResponse.setAddress("madrid");
        teacherDtoResponse.setArchive(false);
        return teacherDtoResponse;
    }
}
