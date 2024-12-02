package com.example.vendor_portal.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Vendor {

    @Id
    @GeneratedValue
    private Long id;

    private String vendorName;
    private String logo;
    private String email;
    private String website;
    private int estYr;

    private String description;

    private String vendorServices;
    private int vendorMatchPercentage;
    private boolean isVerified;

    @ElementCollection
    private List<Integer> regions;

    @ElementCollection
    private List<Integer> capabilities;

    @ElementCollection
    private List<Integer> organizationalFunctions;

    @ElementCollection
    private List<Integer> contractTypes;

    @ElementCollection
    private List<Integer> licensingModels;

    @ElementCollection
    private List<Integer> integrations;

}
