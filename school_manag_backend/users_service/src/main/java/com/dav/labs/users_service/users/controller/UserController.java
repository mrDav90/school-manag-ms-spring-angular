package com.dav.labs.users_service.users.controller;


import com.dav.labs.users_service.clients.kafka.producer.KafkaProducer;
import com.dav.labs.users_service.clients.keycloak.IKcClientService;
import com.dav.labs.users_service.users.dto.requests.UserDtoRequest;
import com.dav.labs.users_service.users.dto.responses.UserDtoResponse;
import com.dav.labs.users_service.users.services.IUserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Getter
@Setter
public class UserController {
    private final IUserService userService;
    private final KafkaProducer kafkaProducer;
    private final IKcClientService kcClientService;

    @GetMapping
    public ResponseEntity<List<UserDtoResponse>> getAllUsers() {
        List<UserDtoResponse> users = userService.getAllUsers().get();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleRepresentation>> getRoles() {
        List<RoleRepresentation> roles = kcClientService.getRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> getUser(@PathVariable("id") Long id){
        Optional<UserDtoResponse> user = userService.getUserById(id);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

//    @GetMapping("/me")
//    public ResponseEntity<UserDtoResponse> getMe(Authentication authentication){
//        Optional<UserDtoResponse> user = userService.getUserById(id);
//        return new ResponseEntity<>(user.get(), HttpStatus.OK);
//    }

    @PostMapping("/message")
    public ResponseEntity<String> sendMessage(
            @RequestBody String message
    ) {
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Message queued successfully");
    }

    @PostMapping
    public ResponseEntity<UserDtoResponse> saveUser(@RequestBody @Valid UserDtoRequest userDtoRequest){
        Optional<UserDtoResponse> userDtoResponse = userService.saveUser(userDtoRequest);
        return new ResponseEntity<>(userDtoResponse.get(), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDtoResponse> updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserDtoRequest userDtoRequest){
        Optional<UserDtoResponse> userDtoResponse = userService.updateUser(id, userDtoRequest);
        return new ResponseEntity<>(userDtoResponse.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id){
        boolean result = userService.deleteUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
