package com.clm.category.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data @AllArgsConstructor @NoArgsConstructor
public class SubTypeDTO {

    private Long id;
    private String name;
    private Set<CategoryDTO> categories;
}
