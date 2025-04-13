package com.dav.labs.users_service.students.dto.requests;

import com.dav.labs.users_service.students.entities.Address;
import com.dav.labs.users_service.students.entities.Document;
import com.dav.labs.users_service.students.entities.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDtoRequest implements Serializable {
    @NotBlank(message = "Firstname is required!")
    private String firstName;
    @NotBlank(message = "Lastname is required!")
    private String lastName;
    @NotNull(message = "Birthdate is required!")
    private Date birthDate;
    @NotBlank(message = "placeOfBirth is required!")
    private String placeOfBirth;
    @NotBlank(message = "Personal email is required!")
    private String emailPerso;
    @NotBlank(message = "PhoneNumber is required!")
    private String phoneNumber;
    private Address address;
    @NotBlank(message = "Nationality is required!")
    private String nationality;
    @NotNull(message = "Gender is required!")
    private Gender gender;
    private String profilePicture;
    private Document document;
    private String tutorFullName;
    private String tutorPhoneNumber;
}
