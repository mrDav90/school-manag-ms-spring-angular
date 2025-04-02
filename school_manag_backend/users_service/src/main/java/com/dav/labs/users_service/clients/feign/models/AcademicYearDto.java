package com.dav.labs.users_service.clients.feign.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AcademicYearDto implements Serializable {
    private String id;
    private String label;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isCurrent;
    private boolean archive;
}
