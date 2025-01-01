package com.clm.vendor.service;

import com.clm.dtos.VendorDTO;
import com.clm.vendor.entity.Vendor;
import com.clm.vendor.repository.VendorRepository;
import com.clm.vendor.utils.VendorMapper;
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
