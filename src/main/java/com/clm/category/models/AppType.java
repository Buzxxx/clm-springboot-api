package com.clm.category.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class AppType {

    @Id
    @GeneratedValue
    private Long id;

    private String name;


}
