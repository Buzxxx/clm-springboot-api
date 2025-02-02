package com.clm.vendor.service;

import com.clm.vendor.models.VendorDTO;
import com.clm.vendor.models.VendorResponseDTO;

import java.util.List;

public interface VendorService {

    public VendorResponseDTO createVendor(VendorDTO vendorDTO);
    public VendorResponseDTO getVendor(Long id);
    public List<VendorResponseDTO> getAllVendors();
    public List<VendorResponseDTO> getVendorByIds(List<Long> ids);
    public VendorResponseDTO updateVendor(Long id, VendorDTO vendorDTO);
    public void deleteVendor(Long id);
}
