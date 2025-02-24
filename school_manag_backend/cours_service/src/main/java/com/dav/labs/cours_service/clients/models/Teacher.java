package com.dav.labs.cours_service.clients.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Teacher {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailPro;
    private String phoneNumber;
    private String address;
    private boolean archive;
}
