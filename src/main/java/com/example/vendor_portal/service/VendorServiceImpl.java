package com.example.vendor_portal.service;

import com.example.vendor_portal.entity.Vendor;
import com.example.vendor_portal.repository.VendorRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService{

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }


    @Override
    public List<Vendor> getAllVendors(Pageable pageable) {
        return vendorRepository.findAll(pageable).getContent();
    }
}
