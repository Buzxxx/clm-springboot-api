package com.clm.vendor.processor;

import com.clm.category.models.CategoryDTO;
import com.clm.category.models.OptionDTO;
import com.clm.category.service.CategoryService;
import com.clm.vendor.models.Vendor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
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
    public List<CategoryDTO> buildCategoryOptions(Vendor vendor) {
        Map<Long, List<Long>> vendorCategoryOptions = vendor.getCategoryOptions();

        // Fetch categories with options using CategoryService
        List<CategoryDTO> categories = categoryService.getCategoriesWithOptionsByIds(
                vendorCategoryOptions.keySet()
        );

        categories.forEach(category -> {
            List<OptionDTO> filteredOptions = category.getOptions().stream()
                    .filter(option -> vendorCategoryOptions.getOrDefault(
                            category.getId(), List.of()
                    ).contains(option.getId()))
                    .toList();
            System.out.println("Filtered Options: " + category.getId() + " :" + filteredOptions);
            category.setOptions(new HashSet<>(filteredOptions));
        });
        return categories;
    }
}
