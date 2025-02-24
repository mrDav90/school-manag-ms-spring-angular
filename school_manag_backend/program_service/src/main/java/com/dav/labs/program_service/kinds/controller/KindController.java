package com.dav.labs.program_service.kinds.controller;

import com.dav.labs.program_service.kinds.dto.requests.KindDtoRequest;
import com.dav.labs.program_service.kinds.dto.responses.KindDtoResponse;
import com.dav.labs.program_service.kinds.services.impl.KindServiceImpl;
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
@RequestMapping("/api/v1/kinds")
//@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Getter
@Setter
public class KindController {
    private final KindServiceImpl kindService;

    @GetMapping
    public ResponseEntity<List<KindDtoResponse>> getAllKinds() {
        List<KindDtoResponse> kinds = kindService.getAllKinds().get();
        return new ResponseEntity<>(kinds, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KindDtoResponse> getKind(@PathVariable("id") Long id){
        Optional<KindDtoResponse> kind = kindService.getKindById(id);
        return new ResponseEntity<>(kind.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<KindDtoResponse> saveKind(@RequestBody @Valid KindDtoRequest kindDtoRequest){
        Optional<KindDtoResponse> kindDtoResponse = kindService.saveKind(kindDtoRequest);
        return new ResponseEntity<>(kindDtoResponse.get(), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<KindDtoResponse> updateKind(@PathVariable("id") Long id, @RequestBody @Valid KindDtoRequest kindDtoRequest){
        Optional<KindDtoResponse> kindDtoResponse = kindService.updateKind(id, kindDtoRequest);
        return new ResponseEntity<>(kindDtoResponse.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteKind(@PathVariable("id") Long id){
        boolean result = kindService.deleteKind(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
