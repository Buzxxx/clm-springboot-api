package com.clm.vendor.service;

import com.clm.category.models.CategoryDTO;
import com.clm.category.models.OptionDTO;
import com.clm.vendor.models.VendorDTO;
import com.clm.vendor.models.VendorResponseDTO;
import com.clm.vendor.models.Vendor;
import com.clm.vendor.processor.VendorDataProcessor;
import com.clm.vendor.repository.VendorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class VendorServiceImpl implements VendorService{

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;
    private final VendorDataProcessor vendorDataProcessor;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper, VendorDataProcessor vendorDataProcessor) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
        this.vendorDataProcessor = vendorDataProcessor;
    }

    @Override
    public VendorResponseDTO createVendor(VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.toEntity(vendorDTO);
        Vendor savedVendor = vendorRepository.save(vendor);
        Map<CategoryDTO, List<OptionDTO>> categoryOptions =
                vendorDataProcessor.buildCategoryOptions(savedVendor);
        return vendorMapper.toResponseDTO(savedVendor, categoryOptions);
    }

    @Override
    public VendorResponseDTO getVendor(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vendor not found with id: " + id));
        Map<CategoryDTO, List<OptionDTO>> categoryOptions =
                vendorDataProcessor.buildCategoryOptions(vendor);
        return vendorMapper.toResponseDTO(vendor, categoryOptions);
    }

    @Override
    public List<VendorResponseDTO> getAllVendors() {
        List<Vendor> vendors = vendorRepository.findAll();

        return vendors.stream()
                .map(vendor -> {
                    Map<CategoryDTO, List<OptionDTO>> categoryOptions =
                            vendorDataProcessor.buildCategoryOptions(vendor);
                    return vendorMapper.toResponseDTO(vendor, categoryOptions);
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorResponseDTO updateVendor(Long id, VendorDTO vendorDTO) {
        if (!vendorRepository.existsById(id)) {
            throw new EntityNotFoundException("Vendor not found with id: " + id);
        }
        Vendor vendor = vendorMapper.toEntity(vendorDTO);
        Vendor savedVendor = vendorRepository.save(vendor);
        Map<CategoryDTO, List<OptionDTO>> categoryOptions =
                vendorDataProcessor.buildCategoryOptions(vendor);
        return vendorMapper.toResponseDTO(savedVendor, categoryOptions);
    }

    @Override
    public void deleteVendor(Long id) {
        if (!vendorRepository.existsById(id)) {
            throw new EntityNotFoundException("Vendor not found with id: " + id);
        }
        vendorRepository.deleteById(id);
    }
}
