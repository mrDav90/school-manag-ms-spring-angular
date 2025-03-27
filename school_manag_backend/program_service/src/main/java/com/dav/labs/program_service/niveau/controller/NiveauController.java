package com.dav.labs.program_service.niveau.controller;


import com.dav.labs.program_service.niveau.dto.requests.NiveauDtoRequest;
import com.dav.labs.program_service.niveau.dto.responses.NiveauDtoResponse;
import com.dav.labs.program_service.niveau.service.impl.NiveauServiceImpl;
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
@RequestMapping("/api/v1/niveaux")
//@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Getter
@Setter
public class NiveauController {
    private final NiveauServiceImpl niveauService;

    @GetMapping
    public ResponseEntity<List<NiveauDtoResponse>> getAllNiveaux() {
        List<NiveauDtoResponse> niveaux = niveauService.getAllNiveaux().get();
        return new ResponseEntity<>(niveaux, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NiveauDtoResponse> getNiveau(@PathVariable("id") String id){
        Optional<NiveauDtoResponse> niveau = niveauService.getNiveauById(id);
        return new ResponseEntity<>(niveau.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NiveauDtoResponse> saveNiveau(@RequestBody @Valid NiveauDtoRequest niveauDtoRequest){
        Optional<NiveauDtoResponse> niveauDtoResponse = niveauService.saveNiveau(niveauDtoRequest);
        return new ResponseEntity<>(niveauDtoResponse.get(), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<NiveauDtoResponse> updateNiveau(@PathVariable("id") String id, @RequestBody @Valid NiveauDtoRequest niveauDtoRequest){
        Optional<NiveauDtoResponse> niveauDtoResponse = niveauService.updateNiveau(id, niveauDtoRequest);
        return new ResponseEntity<>(niveauDtoResponse.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteNiveau(@PathVariable("id") String id){
        boolean result = niveauService.deleteNiveau(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
