package com.clm.category.models;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CategoryDTO {

    private Long id;
    private String name;
    private String description;
    private Set<OptionDTO> options;
}
