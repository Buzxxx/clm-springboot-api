package com.clm.matching.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VendorMatchOverviewResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Boolean is_verified;
    private String software_name;
    private LocalDate business_started_date;
    private int matchPercentage;
}
