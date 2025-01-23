package com.clm.matching.models;

import com.clm.category.models.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CategoryMatchResponseDTO {

    private Long categoryId;
    private double matchPercentage;
    private List<Long> matchedOptions;
}
