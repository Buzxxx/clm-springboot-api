package com.clm.vendor.utils;

import com.clm.vendor.api.VendorDTO;
import com.clm.vendor.entity.Vendor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class VendorMapper {

    public VendorDTO toDTO(Vendor vendor) {
        return VendorDTO.builder()
                .id(vendor.getId())
                .categoryOptions(vendor.getCategoryOptions())
                .build();
    }

    public Vendor toEntity(VendorDTO dto) {
        Vendor vendor = new Vendor();
        vendor.setId(dto.getId());
        vendor.setCategoryOptions(dto.getCategoryOptions());
        return vendor;
    }

    public List<VendorDTO> toDTOList(List<Vendor> vendors) {
        return vendors.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


}
