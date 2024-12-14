package com.example.vendor_portal.service;

import com.example.vendor_portal.dtos.VendorDTO;
import com.example.vendor_portal.entity.Vendor;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getAllVendors(Pageable pageable);
}
