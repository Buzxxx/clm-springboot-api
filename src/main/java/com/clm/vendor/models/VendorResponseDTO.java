package com.clm.vendor.models;

import com.clm.category.models.CategoryDTO;
import com.clm.category.models.OptionDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class VendorResponseDTO {

    private Long id;
    private String name;
    private String description;

    private Map<CategoryDTO, List<OptionDTO>> categoryOptions;
}
