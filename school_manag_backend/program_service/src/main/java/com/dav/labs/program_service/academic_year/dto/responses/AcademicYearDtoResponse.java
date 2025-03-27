package com.dav.labs.program_service.academic_year.dto.responses;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AcademicYearDtoResponse implements Serializable {
    private String id;
    private String label;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isCurrent;
    private boolean archive;
}
