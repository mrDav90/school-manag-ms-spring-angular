package com.dav.labs.program_service.kinds.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KindDtoRequest implements Serializable {
    @NotBlank(message = "Name is required!")
    private String name;
    private String description;
}
