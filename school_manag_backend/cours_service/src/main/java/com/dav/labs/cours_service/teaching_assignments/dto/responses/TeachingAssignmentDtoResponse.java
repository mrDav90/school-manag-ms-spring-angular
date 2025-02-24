package com.dav.labs.cours_service.teaching_assignments.dto.responses;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TeachingAssignmentDtoResponse implements Serializable {
    private Long id;
    private Long teacherId;
    private String teacherName;
    private Long courseId;
    private String courseName;
    private Long classeId;
    private String classeName;
}
