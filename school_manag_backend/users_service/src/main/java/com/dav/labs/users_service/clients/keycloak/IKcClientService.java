package com.dav.labs.users_service.clients.keycloak;

public interface IKcClientService {
    CreateKcClientResponse createUser(
            String firstName,
            String lastName,
            String username,
            String email,
            String password
    );
    void updateUser(String userId, String newEmail);
    void deleteUser(String userId);
}
