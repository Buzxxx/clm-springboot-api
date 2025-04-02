package com.clm.category.models;

import lombok.*;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AppTypeDTO {

    private Long id;
    private String name;
    private List<SubTypeDTO> subTypes;
}
