package com.example.vendor_portal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Region {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Region(String name) {
        this.name = name;
    }
}
