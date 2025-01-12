package com.clm.matching.service;

import com.clm.category.models.CategoryDTO;
import com.clm.category.service.CategoryService;
import com.clm.matching.models.VendorMatchResponseDTO;
import com.clm.matching.processor.MatchEngineProcessor;
import com.clm.vendor.models.VendorResponseDTO;
import com.clm.vendor.service.VendorService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MatchEngineServiceImpl implements MatchEngineService{

    private final VendorService vendorService;
    private final CategoryService categoryService;
    private final MatchEngineProcessor matchEngineProcessor;

    public MatchEngineServiceImpl(VendorService vendorService, CategoryService categoryService, MatchEngineProcessor matchEngineProcessor) {
        this.vendorService = vendorService;
        this.categoryService = categoryService;
        this.matchEngineProcessor = matchEngineProcessor;
    }

    @Override
    public List<VendorMatchResponseDTO> getMatchResults(Map<Long, List<Long>> userSelections) {
        List<VendorResponseDTO> vendors = vendorService.getAllVendors();

        List<CategoryDTO> categories = categoryService.findAll();

        Map<Long, CategoryDTO> categoryMap = categories.stream()
                .collect(Collectors.toMap(CategoryDTO::getId, Function.identity()));

        return matchEngineProcessor.calculateMatches(vendors, categoryMap, userSelections);
    }


}
