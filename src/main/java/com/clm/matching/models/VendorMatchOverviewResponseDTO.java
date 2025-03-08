package com.clm.matching.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VendorMatchOverviewResponseDTO {

    private Long id;
    private String name;
    private String description;
    private int matchPercentage;
}
