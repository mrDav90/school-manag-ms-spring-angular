package com.dav.labs.users_service.clients.feign.interfaces;

import com.dav.labs.users_service.clients.feign.models.ClasseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "program-service", url = "http://localhost:8084")
public interface ClasseRestClient {
    @GetMapping("/api/v1/classes/{id}")
    ClasseDto getClasseById(@PathVariable("id") String id);
}
