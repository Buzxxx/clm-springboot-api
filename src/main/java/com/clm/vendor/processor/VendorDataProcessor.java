package com.clm.vendor.processor;

import com.clm.category.models.CategoryDTO;
import com.clm.category.models.OptionDTO;
import com.clm.category.service.CategoryService;
import com.clm.vendor.models.Vendor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class VendorDataProcessor {

    private final CategoryService categoryService;

    public VendorDataProcessor(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Build the category-options map for a given vendor.
     */
    public Map<CategoryDTO, List<OptionDTO>> buildCategoryOptions(Vendor vendor) {
        Map<Long, List<Long>> vendorCategoryOptions = vendor.getCategoryOptions();

        // Fetch categories with options using CategoryService
        List<CategoryDTO> categories = categoryService.getCategoriesWithOptionsByIds(
                vendorCategoryOptions.keySet()
        );

        // Build category-options mapping
        return categories.stream()
                .collect(Collectors.toMap(
                        category -> category,
                        category -> category.getOptions().stream()
                                .filter(option -> vendorCategoryOptions.getOrDefault(
                                        category.getId(), List.of()
                                ).contains(option.getId()))
                                .collect(Collectors.toList())
                ));
    }
}
