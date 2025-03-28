package com.clm.category.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class SubTypeDTO {

    private Long id;
    private String name;
    private List<CategoryDTO> categories;
}
