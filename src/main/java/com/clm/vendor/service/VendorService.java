package com.clm.vendor.service;

import com.clm.vendor.api.VendorDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VendorService {

    public VendorDTO createVendor(VendorDTO vendorDTO);
    public VendorDTO getVendor(Long id);
    public List<VendorDTO> getAllVendors();
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO);
    public void deleteVendor(Long id);
}
