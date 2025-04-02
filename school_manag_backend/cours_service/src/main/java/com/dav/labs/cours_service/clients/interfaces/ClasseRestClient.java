package com.dav.labs.cours_service.clients.interfaces;

import com.dav.labs.cours_service.clients.models.Classe;
import com.dav.labs.cours_service.clients.models.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "program-service", url = "http://localhost:8084")
public interface ClasseRestClient {
    @GetMapping("/api/v1/classes/{id}")
    Classe getClasseById(@PathVariable("id") String id);
}
