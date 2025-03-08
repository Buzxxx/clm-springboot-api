package com.clm.vendor.models;

import com.clm.category.models.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


//This DTO is used for returning response back to the frontend.
public class VendorResponseDTO {

    private Long id;
    private String name;
    private String software_name;
    private String description;
    private String website;
    private String logo;
    private Boolean is_verified;
    private LocalDate supplier_commencement_date;
    private LocalDate business_started_date;
    private LocalDateTime created_ts;
    private String created_by;
    private LocalDateTime last_updated_ts;
    private String last_updated_by;
    private List<CategoryDTO> categoryOptions;
}
