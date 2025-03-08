package com.clm.vendor.models;

import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class VendorDTO {
    private Long id;
    private String name;
    private String software_name;
    private String description;
    private String website;
    private String logo;
    private Boolean is_verified;
    private LocalDateTime supplier_commencement_date;
    private LocalDateTime business_started_date;
    private LocalDateTime created_ts;
    private String created_by;
    private LocalDateTime last_updated_ts;
    private String last_updated_by;
    private Map<Long, List<Long>> categoryOptions;
}
