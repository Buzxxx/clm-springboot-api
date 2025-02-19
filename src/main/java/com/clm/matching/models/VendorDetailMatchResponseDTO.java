package com.clm.matching.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendorDetailMatchResponseDTO {

    private Long id;
    private String name;
    private String description;
    private double matchPercentage;
    List<CategoryMatchResponseDTO> categoryMatches;
}
