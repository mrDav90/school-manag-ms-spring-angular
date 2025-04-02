package com.dav.labs.users_service.clients.feign.interfaces;

import com.dav.labs.users_service.clients.feign.models.AcademicYearDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "program-service", url = "http://localhost:8084")
public interface AcademicYearRestClient {
    @GetMapping("/api/v1/academic-year/{id}")
    AcademicYearDto getAcademicYearById(@PathVariable("id") String id);
}

