package com.example.vendor_portal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Capability {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Capability(String name) {
        this.name = name;
    }
}
