package com.dav.labs.users_service.users.controllers;

import com.dav.labs.users_service.users.controller.UserController;
import com.dav.labs.users_service.users.dto.requests.UserDtoRequest;
import com.dav.labs.users_service.users.dto.responses.UserDtoResponse;
import com.dav.labs.users_service.users.services.impl.UserServiceImpl;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    private List<UserDtoResponse> userDtoResponses;
    UserDtoRequest userDtoRequest = new UserDtoRequest();

    @BeforeEach
    void setUp() {
        userDtoResponses = List.of(
                new UserDtoResponse(1L, "david@gmail.com", "1xji_BS2Bnxbuz62")
        );
        userDtoRequest.setEmailPro("david@gmail.com");
        userDtoRequest.setToken("1xji_BS2Bnxbuz62");
    }

    @Test
    void testGetAllUsers_ReturnsOkResponse() {
        when(userService.getAllUsers()).thenReturn(Optional.of(userDtoResponses));
        ResponseEntity<List<UserDtoResponse>> response = userController.getAllUsers();
        assertEquals(HttpStatus.OK , response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1,response.getBody().size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetAllUsers_ReturnsEmptyList() {
        when(userService.getAllUsers()).thenReturn(Optional.of(List.of()));
        ResponseEntity<List<UserDtoResponse>> response = userController.getAllUsers();
        assertEquals(HttpStatus.OK , response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetUser_ReturnsOkResponse() {
        when(userService.getUserById(1L)).thenReturn(Optional.of(userDtoResponses.get(0)));
        ResponseEntity<UserDtoResponse> response = userController.getUser(1L);
        assertEquals(HttpStatus.OK , response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userDtoResponses.get(0),response.getBody());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void testSaveUser_ReturnsOkResponse() {
        when(userService.saveUser(userDtoRequest)).thenReturn(Optional.of(userDtoResponses.get(0)));
        ResponseEntity<UserDtoResponse> response = userController.saveUser(userDtoRequest);
        assertEquals(HttpStatus.CREATED , response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userDtoResponses.get(0),response.getBody());
        verify(userService, times(1)).saveUser(userDtoRequest);
    }

    @Test
    void testUpdateUser_ReturnsOkResponse() {
        when(userService.updateUser(1L, userDtoRequest)).thenReturn(Optional.of(userDtoResponses.get(0)));
        ResponseEntity<UserDtoResponse> response = userController.updateUser(1L, userDtoRequest);
        assertEquals(HttpStatus.OK , response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userDtoResponses.get(0),response.getBody());
        verify(userService, times(1)).updateUser(1L,userDtoRequest);
    }

    @Test
    void testDeleteUser_ReturnsOkResponse() {
        when(userService.deleteUser(1L)).thenReturn(Boolean.TRUE);
        ResponseEntity<Boolean> response = userController.deleteUser(1L);
        assertEquals(HttpStatus.OK , response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Boolean.TRUE,response.getBody());
        verify(userService, times(1)).deleteUser(1L);
    }
}
