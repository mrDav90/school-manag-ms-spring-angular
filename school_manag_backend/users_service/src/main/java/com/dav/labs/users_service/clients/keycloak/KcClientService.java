package com.dav.labs.users_service.clients.keycloak;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.core.Response;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class KcClientService implements IKcClientService {
    private Keycloak keycloakAdmin;

    @Value("${keycloak.server-url}")
    private String serverUrl;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.client-id}")
    private String clientId;
    @Value("${keycloak.client-secret}")
    private String clientSecret;
    @Value("${keycloak.username}")
    private String username;
    @Value("${keycloak.password}")
    private String password;

    @PostConstruct
    public void initKeycloakAdmin() {

        this.keycloakAdmin = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                //.grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .grantType(OAuth2Constants.PASSWORD)
                .username(username)
                .password(password)
                .build();
    }

    public CreateKcClientResponse createUser(String username, String email, String password) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEmail(email);
        user.setEnabled(true);
        user.setEmailVerified(true);
        user.setRequiredActions(Collections.emptyList());

        Response response = keycloakAdmin.realm(realm).users().create(user);

        if (response.getStatus() == 201) {
            String userId = keycloakAdmin.realm(realm).users()
                    .search(username)
                    .get(0).getId();

            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setTemporary(false);
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(password);
            //user.setCredentials(Collections.singletonList(credential));

            System.out.println(keycloakAdmin.realm(realm).users().get(userId));
            keycloakAdmin.realm(realm).users().get(userId).resetPassword(credential);

            return new CreateKcClientResponse(true,"Created user successfully");
        } else {
            return new CreateKcClientResponse(false,"Error"+ response.getStatus());
        }
    }

    public void updateUser(String userId, String newEmail) {
        UserRepresentation user = keycloakAdmin.realm(realm).users().get(userId).toRepresentation();
        user.setEmail(newEmail);
        keycloakAdmin.realm(realm).users().get(userId).update(user);
    }

    public void deleteUser(String username) {
        UsersResource usersResource = keycloakAdmin.realm(realm).users();
        List<UserRepresentation> users = usersResource.search(username);

        if (users.isEmpty()) {
            System.out.println("Utilisateur non trouvé !");
            return;
        }
        String userId = users.get(0).getId();
        usersResource.get(userId).remove();
        //keycloakAdmin.realm(realm).users().get(username).remove();
    }


}
