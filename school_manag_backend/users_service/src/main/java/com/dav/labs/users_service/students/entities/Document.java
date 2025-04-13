package com.dav.labs.users_service.students.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Document implements Serializable {
    private String cin;
    private String birthCertificate;
}
