package com.dav.labs.users_service.students.dto.responses;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDtoResponse implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String emailPro;
    private String emailPerso;
    private String phoneNumber;
    private String address;
    private boolean archive;
    private String registrationNu;
}
