package com.dav.labs.users_service.clients.keycloak;

import com.dav.labs.users_service.users.entities.Role;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;

public interface IKcClientService {
    CreateKcClientResponse createUser(
            String firstName,
            String lastName,
            String username,
            String email,
            String password,
            Role role
    );
    void updateUser(String userId, String newEmail);
    void deleteUser(String userId);
    List<RoleRepresentation> getRoles();
}
