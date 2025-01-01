package com.clm.category.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionDTO {

    private Long id;
    private String name;
    private Long categoryId;
}
