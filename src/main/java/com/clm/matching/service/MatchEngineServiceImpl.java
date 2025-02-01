package com.clm.matching.service;

import com.clm.category.models.CategoryDTO;
import com.clm.category.service.CategoryService;
import com.clm.matching.models.VendorMatchOverviewResponseDTO;
import com.clm.matching.models.VendorMatchResponseDTO;
import com.clm.matching.processor.MatchEngineProcessor;
import com.clm.vendor.models.VendorResponseDTO;
import com.clm.vendor.service.VendorService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
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
    public Map<String, Object>  getMatchResults(Map<Long, List<Long>> userSelections) {
        List<VendorResponseDTO> vendors = vendorService.getAllVendors();

        List<CategoryDTO> categories = categoryService.findAll();

        Map<Long, CategoryDTO> categoryMap = categories.stream()
                .collect(Collectors.toMap(CategoryDTO::getId, Function.identity()));

        List<CategoryDTO> filteredCategories = matchEngineProcessor.prepareFilteredCategories(userSelections, categoryMap);
        List<VendorMatchResponseDTO> vendorResponses = matchEngineProcessor.prepareVendorResponses(userSelections, categoryMap, vendors);

        Map<String, Object> response = new HashMap<>();
        response.put("categories", filteredCategories);
        response.put("vendors", vendorResponses);

        return response;
    }

    @Override
    public List<VendorMatchOverviewResponseDTO> getMatchOverview(Map<Long, List<Long>> userSelections) {
        List<VendorResponseDTO> vendors = vendorService.getAllVendors();
        return matchEngineProcessor.prepareMatchOverview(userSelections, vendors);
    }


}
