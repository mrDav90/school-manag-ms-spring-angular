package com.dav.labs.users_service.teachers.dto.requests;

import com.dav.labs.users_service.clients.models.TeachingAssignment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDtoRequest implements Serializable {
    @NotBlank(message = "Firstname is required!")
    private String firstName;
    @NotBlank(message = "Lastname is required!")
    private String lastName;
    @NotBlank(message = "Email is required!")
    private String emailPro;
    @NotBlank(message = "PhoneNumber is required!")
    private String phoneNumber;
    private String address;
    private List<TeachingAssignment> teachingAssignments;
}
