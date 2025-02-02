package com.clm.matching.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VendorMatchOverviewResponseDTO {

    private Long id;
    private String name;
    private String description;
    private double matchPercentage;
    private List<OptionMatchResponseDTO> options;
}
