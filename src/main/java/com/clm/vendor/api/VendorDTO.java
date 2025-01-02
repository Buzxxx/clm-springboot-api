package com.clm.vendor.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class VendorDTO {

    private Long id;
    private Map<Long, List<Long>> categoryOptions;
}
