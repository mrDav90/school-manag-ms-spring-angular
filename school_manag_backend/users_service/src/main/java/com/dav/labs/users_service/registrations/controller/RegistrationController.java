package com.dav.labs.users_service.registrations.controller;

import com.dav.labs.users_service.registrations.dto.requests.RegistrationDtoRequest;
import com.dav.labs.users_service.registrations.dto.responses.RegistrationDtoResponse;
import com.dav.labs.users_service.registrations.services.IRegistrationService;
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
@RequestMapping("/api/v1/registrations")
@RequiredArgsConstructor
@Getter
@Setter
public class RegistrationController {
    private final IRegistrationService registrationService;


    @GetMapping
    public ResponseEntity<List<RegistrationDtoResponse>> getAllRegistrations() {
        List<RegistrationDtoResponse> registrations = registrationService.getAllRegistrations().get();
        return new ResponseEntity<>(registrations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDtoResponse> getRegistration(@PathVariable("id") String id){
        Optional<RegistrationDtoResponse> registration = registrationService.getRegistrationById(id);
        return new ResponseEntity<>(registration.get(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<RegistrationDtoResponse> saveRegistration(@RequestBody @Valid RegistrationDtoRequest registrationDtoRequest){
        Optional<RegistrationDtoResponse> registrationDtoResponse = registrationService.saveRegistration(registrationDtoRequest);
        return new ResponseEntity<>(registrationDtoResponse.get(), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RegistrationDtoResponse> updateRegistration(@PathVariable("id") String id, @RequestBody @Valid RegistrationDtoRequest registrationDtoRequest){
        Optional<RegistrationDtoResponse> registrationDtoResponse = registrationService.updateRegistration(id, registrationDtoRequest);
        return new ResponseEntity<>(registrationDtoResponse.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRegistration(@PathVariable("id") String id){
        boolean result = registrationService.deleteRegistration(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
