package com.clm.vendor.service;

import com.clm.category.models.CategoryDTO;
import com.clm.vendor.models.Vendor;
import com.clm.vendor.models.VendorDTO;
import com.clm.vendor.models.VendorResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface VendorMapper {

    @Mapping(target = "categoryOptions", ignore = true)
    VendorResponseDTO toResponseDTO(Vendor vendor);

    //Convert payload DTO to Entity (For API Input)
    @Mapping(target = "id", ignore = true) //Ignoring ID because DB generated
    @Mapping(target = "is_verified", ignore = true)
    @Mapping(target = "supplier_commencement_date", ignore = true)
    @Mapping(target = "business_started_date", ignore = true)
    @Mapping(target = "created_ts", ignore = true)
    @Mapping(target = "created_by", ignore = true)
    @Mapping(target = "last_updated_ts", ignore = true)
    @Mapping(target = "last_updated_by", ignore = true)
     Vendor toEntity(VendorDTO dto);

    default VendorResponseDTO toResponseDTO(Vendor vendor, List<CategoryDTO> categoryOptions) {
        VendorResponseDTO dto = toResponseDTO(vendor);
        dto.setCategoryOptions(categoryOptions);
        return dto;
    }

}
