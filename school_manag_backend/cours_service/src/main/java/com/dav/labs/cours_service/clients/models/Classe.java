package com.dav.labs.cours_service.clients.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Classe {
    private String id;
    private String name;
    private String sectorId;
    private String sectorName;
    private boolean archive;
}
