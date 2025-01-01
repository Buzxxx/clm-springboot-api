package com.clm.vendor.service;

import com.clm.dtos.VendorDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getAllVendors(Pageable pageable);
}
