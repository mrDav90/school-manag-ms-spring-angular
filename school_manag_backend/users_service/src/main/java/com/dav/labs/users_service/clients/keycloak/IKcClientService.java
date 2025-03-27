package com.dav.labs.users_service.clients.keycloak;

public interface IKcClientService {
    CreateKcClientResponse createUser(String username, String email, String password);
    void updateUser(String userId, String newEmail);
    void deleteUser(String userId);
}
