package com.clm.vendor.service;

import com.clm.category.models.CategoryDTO;
import com.clm.category.models.OptionDTO;
import com.clm.vendor.models.VendorDTO;
import com.clm.vendor.models.VendorResponseDTO;
import com.clm.vendor.models.Vendor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;



@Component
public class VendorMapper {

    public VendorResponseDTO toResponseDTO(Vendor vendor, List<CategoryDTO> categoryOptions) {
        return VendorResponseDTO.builder()
                .id(vendor.getId())
                .name(vendor.getName())
                .software_name(vendor.getSoftware_name())
                .description(vendor.getDescription())
                .website(vendor.getWebsite())
                .logo(vendor.getLogo())
                .is_verified(vendor.getIs_verified())
                .categoryOptions(categoryOptions)
                .build();
    }

    public Vendor toEntity(VendorDTO dto) {
        Vendor vendor = new Vendor();
        vendor.setName(dto.getName());
        vendor.setDescription(dto.getDescription());
        vendor.setCategoryOptions(dto.getCategoryOptions());
        return vendor;
    }

}
