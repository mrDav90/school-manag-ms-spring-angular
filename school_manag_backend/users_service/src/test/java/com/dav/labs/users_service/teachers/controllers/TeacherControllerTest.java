package com.dav.labs.users_service.teachers.controllers;

import com.dav.labs.users_service.teachers.controller.TeacherController;
import com.dav.labs.users_service.teachers.dto.requests.TeacherDtoRequest;
import com.dav.labs.users_service.teachers.dto.responses.TeacherDtoResponse;
import com.dav.labs.users_service.teachers.services.impl.TeacherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherControllerTest {
    @Mock
    private TeacherServiceImpl teacherService;

    @InjectMocks
    private TeacherController teacherController;

    private List<TeacherDtoResponse> teacherDtoResponses;
    TeacherDtoRequest teacherDtoRequest = new TeacherDtoRequest();

    @BeforeEach
    void setUp() {
        teacherDtoResponses = List.of(
            new TeacherDtoResponse("1", "Ngor", "Seck","ngorseck@gmail.com","1234567890","Dakar",false, List.of())
        );
        teacherDtoRequest.setFirstName("ngor");
        teacherDtoRequest.setLastName("seck");
        teacherDtoRequest.setEmailPro("ngorseck@groupeisi.com");
        teacherDtoRequest.setPhoneNumber("771232211");
        teacherDtoRequest.setAddress("madrid");
    }

    @Test
    void testGetAllTeachers_ReturnsOkResponse() {
        when(teacherService.getAllTeachers()).thenReturn(Optional.of(teacherDtoResponses));
        ResponseEntity<List<TeacherDtoResponse>> response = teacherController.getAllTeachers();
        assertEquals(HttpStatus.OK , response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1,response.getBody().size());
        verify(teacherService, times(1)).getAllTeachers();
    }

    @Test
    void testGetAllTeachers_ReturnsEmptyList() {
        when(teacherService.getAllTeachers()).thenReturn(Optional.of(List.of()));
        ResponseEntity<List<TeacherDtoResponse>> response = teacherController.getAllTeachers();
        assertEquals(HttpStatus.OK , response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
        verify(teacherService, times(1)).getAllTeachers();
    }

    @Test
    void testGetTeacher_ReturnsOkResponse() {
        when(teacherService.getTeacherById("1")).thenReturn(Optional.of(teacherDtoResponses.get(0)));
        ResponseEntity<TeacherDtoResponse> response = teacherController.getTeacher("1");
        assertEquals(HttpStatus.OK , response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(teacherDtoResponses.get(0),response.getBody());
        verify(teacherService, times(1)).getTeacherById("1");
    }

    @Test
    void testSaveTeacher_ReturnsOkResponse() {
        when(teacherService.saveTeacher(teacherDtoRequest)).thenReturn(Optional.of(teacherDtoResponses.get(0)));
        ResponseEntity<TeacherDtoResponse> response = teacherController.saveTeacher(teacherDtoRequest);
        assertEquals(HttpStatus.CREATED , response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(teacherDtoResponses.get(0),response.getBody());
        verify(teacherService, times(1)).saveTeacher(teacherDtoRequest);
    }

    @Test
    void testUpdateTeacher_ReturnsOkResponse() {
        when(teacherService.updateTeacher("1", teacherDtoRequest)).thenReturn(Optional.of(teacherDtoResponses.get(0)));
        ResponseEntity<TeacherDtoResponse> response = teacherController.updateTeacher("1", teacherDtoRequest);
        assertEquals(HttpStatus.OK , response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(teacherDtoResponses.get(0),response.getBody());
        verify(teacherService, times(1)).updateTeacher("1",teacherDtoRequest);
    }

    @Test
    void testDeleteTeacher_ReturnsOkResponse() {
        when(teacherService.deleteTeacher("1")).thenReturn(Boolean.TRUE);
        ResponseEntity<Boolean> response = teacherController.deleteTeacher("1");
        assertEquals(HttpStatus.OK , response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Boolean.TRUE,response.getBody());
        verify(teacherService, times(1)).deleteTeacher("1");
    }
}
