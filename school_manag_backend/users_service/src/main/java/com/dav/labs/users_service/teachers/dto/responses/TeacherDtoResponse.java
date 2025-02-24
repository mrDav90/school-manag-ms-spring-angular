package com.dav.labs.users_service.teachers.dto.responses;

import com.dav.labs.users_service.clients.models.TeachingAssignment;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TeacherDtoResponse implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailPro;
    private String phoneNumber;
    private String address;
    private boolean archive;
    private List<TeachingAssignment> teachingAssignments;
}
