package com.dav.labs.program_service.academic_year.service;

import com.dav.labs.program_service.academic_year.dto.requests.AcademicYearDtoRequest;
import com.dav.labs.program_service.academic_year.dto.responses.AcademicYearDtoResponse;

import java.util.List;
import java.util.Optional;

public interface IAcademicYearService {
    Optional<AcademicYearDtoResponse> saveAcademicYear(AcademicYearDtoRequest academicYearDtoRequest);
    Optional<List<AcademicYearDtoResponse>> getAllAcademicYears();
    Optional<AcademicYearDtoResponse> getAcademicYearById(String academicYearId);
    Optional<AcademicYearDtoResponse> updateAcademicYear(String academicYearId, AcademicYearDtoRequest academicYearDtoRequest);
    boolean deleteAcademicYear(String academicYearId);
}
