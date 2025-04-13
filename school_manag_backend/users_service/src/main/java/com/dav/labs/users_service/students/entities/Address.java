package com.dav.labs.users_service.students.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
}
