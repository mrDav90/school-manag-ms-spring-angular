package com.dav.labs.users_service.students.controllers;

import com.dav.labs.users_service.students.controller.StudentController;
import com.dav.labs.users_service.students.dto.requests.StudentDtoRequest;
import com.dav.labs.users_service.students.dto.responses.StudentDtoResponse;
import com.dav.labs.users_service.students.services.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentServiceImpl studentService;

    @InjectMocks
    private StudentController studentController;

    private List<StudentDtoResponse> studentDtoResponses;
    StudentDtoRequest studentDtoRequest = new StudentDtoRequest();

    @BeforeEach
    void setUp() {
        studentDtoResponses = List.of(
                new StudentDtoResponse("1", "Kangni", "David","david@gmail.com","david@groupeisi.com","1234567890","Dakar",false,"1234"),
                new StudentDtoResponse("2", "Kakashi", "Hatake","kakashi@gmail.com","kakashi@groupeisi.com","1234567850","Konoha",false,"1235")
        );

        studentDtoRequest.setFirstName("Kangni");
        studentDtoRequest.setLastName("David");
        studentDtoRequest.setEmailPerso("david@gmail.com");
        studentDtoRequest.setEmailPro("david@groupeisi.com");
        studentDtoRequest.setAddress("Dakar");
        studentDtoRequest.setPhoneNumber("1234567890");
        studentDtoRequest.setRegistrationNu("1234");
    }

    @Test
    void testGetAllStudents_ReturnsOkResponse() {
        when(studentService.getAllStudents()).thenReturn(Optional.of(studentDtoResponses));
        ResponseEntity<List<StudentDtoResponse>> response = studentController.getAllStudents();
        assertEquals(HttpStatus.OK , response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2,response.getBody().size());
        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    void testGetAllStudents_ReturnsEmptyList() {
        when(studentService.getAllStudents()).thenReturn(Optional.of(List.of()));
        ResponseEntity<List<StudentDtoResponse>> response = studentController.getAllStudents();
        assertEquals(HttpStatus.OK , response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    void testGetPaginatedStudents_ReturnsOkResponse() {
        Page<StudentDtoResponse> page = new PageImpl<>(studentDtoResponses);
        when(studentService.getStudents(0,2)).thenReturn(page);
        ResponseEntity<Page<StudentDtoResponse>> response = studentController.getStudents(0,2);
        assertEquals(HttpStatus.OK , response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2,response.getBody().getTotalElements());
        assertEquals(0 , response.getBody().getNumber());
        verify(studentService, times(1)).getStudents(0,2);
    }

    @Test
    void testGetPaginatedStudents_ReturnsEmptyList() {
        Page<StudentDtoResponse> page = new PageImpl<>(List.of());
        when(studentService.getStudents(0,2)).thenReturn(page);
        ResponseEntity<Page<StudentDtoResponse>> response = studentController.getStudents(0,2);
        assertEquals(HttpStatus.OK , response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
        verify(studentService, times(1)).getStudents(0,2);
    }

    @Test
    void testGetStudent_ReturnsOkResponse() {
        when(studentService.getStudentById("1")).thenReturn(Optional.of(studentDtoResponses.get(0)));
        ResponseEntity<StudentDtoResponse> response = studentController.getStudent("1");
        assertEquals(HttpStatus.OK , response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(studentDtoResponses.get(0),response.getBody());
        verify(studentService, times(1)).getStudentById("1");
    }

    @Test
    void testSaveStudent_ReturnsOkResponse() {
        when(studentService.saveStudent(studentDtoRequest)).thenReturn(Optional.of(studentDtoResponses.get(0)));
        ResponseEntity<StudentDtoResponse> response = studentController.saveStudent(studentDtoRequest);
        assertEquals(HttpStatus.CREATED , response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(studentDtoResponses.get(0),response.getBody());
        verify(studentService, times(1)).saveStudent(studentDtoRequest);
    }

    @Test
    void testUpdateStudent_ReturnsOkResponse() {
        when(studentService.updateStudent("1", studentDtoRequest)).thenReturn(Optional.of(studentDtoResponses.get(0)));
        ResponseEntity<StudentDtoResponse> response = studentController.updateStudent("1", studentDtoRequest);
        assertEquals(HttpStatus.OK , response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(studentDtoResponses.get(0),response.getBody());
        verify(studentService, times(1)).updateStudent("1",studentDtoRequest);
    }

    @Test
    void testDeleteStudent_ReturnsOkResponse() {
        when(studentService.deleteStudent("1")).thenReturn(Boolean.TRUE);
        ResponseEntity<Boolean> response = studentController.deleteStudent("1");
        assertEquals(HttpStatus.OK , response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Boolean.TRUE,response.getBody());
        verify(studentService, times(1)).deleteStudent("1");
    }
}
