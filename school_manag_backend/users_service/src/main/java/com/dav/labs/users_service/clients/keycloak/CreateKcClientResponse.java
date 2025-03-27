package com.dav.labs.users_service.clients.keycloak;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateKcClientResponse {
    private Boolean created;
    private String message;
}
