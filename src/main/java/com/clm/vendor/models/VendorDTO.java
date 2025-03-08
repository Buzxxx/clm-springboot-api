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
    private String software_name;
    private String description;
    private String website;
    private String logo;
    private Boolean is_verified;
    private java.util.Date supplier_commencement_date;
    private java.util.Date business_started_date;
    private java.util.Date created_ts;
    private String created_by;
    private java.util.Date last_updated_ts;
    private String last_updated_by;
    private Map<Long, List<Long>> categoryOptions;
}
