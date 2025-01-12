package com.clm.matching.models;

import com.clm.vendor.models.VendorResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VendorMatchResponseDTO {

    private VendorResponseDTO vendorDTO;
    private double matchPercentage;
    List<CategoryMatchResponseDTO> categories;
}
