package com.dav.labs.cours_service.clients.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
public class AcademicYear {
    private String id;
    private String label;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isCurrent;
    private boolean archive;
}
