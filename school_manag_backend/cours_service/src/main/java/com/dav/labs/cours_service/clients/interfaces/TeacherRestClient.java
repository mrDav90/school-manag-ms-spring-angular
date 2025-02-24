package com.dav.labs.cours_service.clients.interfaces;


import com.dav.labs.cours_service.clients.models.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("http://localhost:8081")
public interface TeacherRestClient {
    @GetMapping("/api/v1/teachers/{id}")
    Teacher getTeacherById(@PathVariable("id") Long id);
}
