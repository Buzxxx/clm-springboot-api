package com.clm.vendor.service;

import com.clm.vendor.api.VendorDTO;
import com.clm.vendor.entity.Vendor;
import com.clm.vendor.repository.VendorRepository;
import com.clm.vendor.utils.VendorMapper;
import jakarta.persistence.EntityNotFoundException;
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
    public VendorDTO createVendor(VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.toEntity(vendorDTO);
        return vendorMapper.toDTO(vendorRepository.save(vendor));
    }

    @Override
    public VendorDTO getVendor(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vendor not found with id: " + id));
        return vendorMapper.toDTO(vendor);
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorMapper.toDTOList(vendorRepository.findAll());
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        if (!vendorRepository.existsById(id)) {
            throw new EntityNotFoundException("Vendor not found with id: " + id);
        }
        Vendor vendor = vendorMapper.toEntity(vendorDTO);
        vendor.setId(id);
        return vendorMapper.toDTO(vendorRepository.save(vendor));
    }

    @Override
    public void deleteVendor(Long id) {
        if (!vendorRepository.existsById(id)) {
            throw new EntityNotFoundException("Vendor not found with id: " + id);
        }
        vendorRepository.deleteById(id);
    }
}
