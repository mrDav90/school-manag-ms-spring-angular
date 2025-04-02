package com.dav.labs.users_service.clients.feign.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClasseDto implements Serializable {
    private String id;
    private String name;
    private String sectorId;
    private String sectorName;
    private boolean archive;
}
