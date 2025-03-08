package com.clm.vendor.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

//This DTO is used for accepting payload from the API

@Data
@Builder
public class VendorDTO {
    private String name;
    private String software_name;
    private String description;
    private String website;
    private String logo;
    private Map<Long, List<Long>> categoryOptions;
}
