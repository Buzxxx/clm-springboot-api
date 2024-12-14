package com.example.vendor_portal.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class VendorDTO {

    private Long id;
    private String vendorName;
    private String description;
    private List<Long> regionIds; // List of IDs for related regions
    private List<Long> contractTypeIds; // List of IDs for related contract types
    private List<Long> organizationalFunctionIds; // List of IDs for organizational functions
    private List<Long> licensingModelIds; // List of IDs for licensing models
    private List<Long> capabilityIds;
    private List<Long> integrationIds;

}
