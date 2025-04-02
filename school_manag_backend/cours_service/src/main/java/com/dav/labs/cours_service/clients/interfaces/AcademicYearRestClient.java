package com.dav.labs.cours_service.clients.interfaces;

import com.dav.labs.cours_service.clients.models.AcademicYear;
import com.dav.labs.cours_service.clients.models.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "program-service", url = "http://localhost:8084")
public interface AcademicYearRestClient {
    @GetMapping("/api/v1/academic-year/{id}")
    AcademicYear getAcademicYearById(@PathVariable("id") String id);
}
