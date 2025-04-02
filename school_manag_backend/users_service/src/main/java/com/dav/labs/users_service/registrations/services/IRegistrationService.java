package com.dav.labs.users_service.registrations.services;

import com.dav.labs.users_service.registrations.dto.requests.RegistrationDtoRequest;
import com.dav.labs.users_service.registrations.dto.responses.RegistrationDtoResponse;

import java.util.List;
import java.util.Optional;

public interface IRegistrationService {
    Optional<RegistrationDtoResponse> saveRegistration(RegistrationDtoRequest registrationDtoRequest);
    Optional<List<RegistrationDtoResponse>> getAllRegistrations();
    Optional<RegistrationDtoResponse> getRegistrationById(String registrationId);
    Optional<RegistrationDtoResponse> updateRegistration(String registrationId, RegistrationDtoRequest registrationDtoRequest);
    boolean deleteRegistration(String userId);
}
