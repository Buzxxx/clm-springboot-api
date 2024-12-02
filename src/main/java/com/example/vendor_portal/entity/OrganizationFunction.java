package com.example.vendor_portal.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationFunction {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
