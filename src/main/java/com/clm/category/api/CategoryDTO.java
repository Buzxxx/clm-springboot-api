package com.clm.category.api;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CategoryDTO {

    private Long id;
    private String name;
    private Set<OptionDTO> options;
}
