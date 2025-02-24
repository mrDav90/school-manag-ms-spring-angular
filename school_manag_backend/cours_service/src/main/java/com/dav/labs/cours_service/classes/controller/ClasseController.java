package com.dav.labs.cours_service.classes.controller;

import com.dav.labs.cours_service.classes.dto.requests.ClasseDtoRequest;
import com.dav.labs.cours_service.classes.dto.responses.ClasseDtoResponse;
import com.dav.labs.cours_service.classes.services.impl.ClasseServiceImpl;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/classes")
//@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Getter
@Setter
public class ClasseController {
    private final ClasseServiceImpl classeService;

    @GetMapping
    public ResponseEntity<List<ClasseDtoResponse>> getAllClasses() {
        List<ClasseDtoResponse> classes = classeService.getAllClasses().get();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasseDtoResponse> getClasse(@PathVariable("id") Long id){
        Optional<ClasseDtoResponse> classe = classeService.getClasseById(id);
        return new ResponseEntity<>(classe.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClasseDtoResponse> saveClasse(@RequestBody @Valid ClasseDtoRequest classeDtoRequest){
        Optional<ClasseDtoResponse> classeDtoResponse = classeService.saveClasse(classeDtoRequest);
        return new ResponseEntity<>(classeDtoResponse.get(), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClasseDtoResponse> updateClasse(@PathVariable("id") Long id, @RequestBody @Valid ClasseDtoRequest classeDtoRequest){
        Optional<ClasseDtoResponse> classeDtoResponse = classeService.updateClasse(id, classeDtoRequest);
        return new ResponseEntity<>(classeDtoResponse.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteClasse(@PathVariable("id") Long id){
        boolean result = classeService.deleteClasse(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
