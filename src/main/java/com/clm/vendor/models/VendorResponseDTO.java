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
    private String software_name;
    private String description;
    private String website;
    private String logo;
    private Boolean is_verified;

    private List<CategoryDTO> categoryOptions;
}
