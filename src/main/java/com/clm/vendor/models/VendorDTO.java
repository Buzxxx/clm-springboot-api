package com.clm.vendor.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class VendorDTO {

    private Long id;
    private String name;
    private String description;
    private Map<Long, List<Long>> categoryOptions;
}
