package com.clm.matching.models;

import com.clm.category.models.CategoryDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryMatchResponseDTO {

    private CategoryDTO categoryDTO;
    private double matchPercentage;
    private List<OptionMatchResponseDTO> options;
}
