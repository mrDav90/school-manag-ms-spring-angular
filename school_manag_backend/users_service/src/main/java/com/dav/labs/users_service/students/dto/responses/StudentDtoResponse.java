package com.dav.labs.users_service.students.dto.responses;

import com.dav.labs.users_service.students.entities.Address;
import com.dav.labs.users_service.students.entities.Document;
import com.dav.labs.users_service.students.entities.Gender;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDtoResponse implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String placeOfBirth;
    private String emailPro;
    private String emailPerso;
    private String phoneNumber;
    private Address address;
    private String nationality;
    private Gender gender;
    private boolean archive;
    private String registrationNu;
    private String profilePicture;
    private Document document;
    private String tutorFullName;
    private String tutorPhoneNumber;
}
