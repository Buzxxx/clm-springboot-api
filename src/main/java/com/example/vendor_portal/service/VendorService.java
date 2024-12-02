package com.example.vendor_portal.service;

import com.example.vendor_portal.entity.Vendor;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VendorService {

    List<Vendor> getAllVendors(Pageable pageable);
}
