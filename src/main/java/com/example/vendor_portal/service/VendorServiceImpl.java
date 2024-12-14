package com.example.vendor_portal.service;

import com.example.vendor_portal.dtos.VendorDTO;
import com.example.vendor_portal.entity.Vendor;
import com.example.vendor_portal.repository.VendorRepository;
import com.example.vendor_portal.utils.VendorMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService{

    private final VendorRepository vendorRepository;

    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }


    @Override
    public List<VendorDTO> getAllVendors(Pageable pageable) {
        List<Vendor> vendors = vendorRepository.findAll(pageable).getContent();

        return vendors.stream().map(vendorMapper::toVendorDTO).collect(Collectors.toList());
    }
}
