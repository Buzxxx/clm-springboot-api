package com.clm.vendor.models;

import com.clm.category.models.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorResponseDTO {

    private Long id;
    private String name;
    private String description;

    private List<CategoryDTO> categoryOptions;
}
