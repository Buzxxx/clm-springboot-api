package com.clm.category.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AppTypeDTO {

    private Long id;
    private String name;
    private String description;
    private String image;
    private Set<SubTypeDTO> subTypes;
}
