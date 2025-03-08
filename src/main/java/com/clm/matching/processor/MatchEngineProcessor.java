package com.clm.matching.processor;

import com.clm.category.models.CategoryDTO;
import com.clm.category.models.OptionDTO;
import com.clm.matching.models.CategoryMatchResponseDTO;
import com.clm.matching.models.OptionMatchResponseDTO;
import com.clm.matching.models.VendorMatchOverviewResponseDTO;
import com.clm.matching.models.VendorDetailMatchResponseDTO;
import com.clm.vendor.models.VendorResponseDTO;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class MatchEngineProcessor {

    public List<CategoryDTO> prepareFilteredCategories(Map<Long, List<Long>> userSelections, Map<Long, CategoryDTO> categoryMap) {
        return categoryMap.values().stream()
                .filter(category -> userSelections.containsKey(category.getId())) // Only include categories in userSelections
                .map(category -> {
                    List<Long> selectedOptionIds = userSelections.get(category.getId());
                    Set<OptionDTO> filteredOptions = category.getOptions().stream()
                            .filter(option -> selectedOptionIds.contains(option.getId())) // Only include matching options
                            .collect(Collectors.toSet());

                    return new CategoryDTO(
                        category.getId(),
                        category.getName(),
                        category.getDescription(),
                        category.getImage(), 
                        filteredOptions
                    );
                })
                .collect(Collectors.toList());
    }

    public List<VendorDetailMatchResponseDTO> prepareVendorResponsesForMatchDetails(Map<Long, List<Long>> userSelections,
                                                                                    Map<Long, CategoryDTO> categoryMap,
                                                                                    List<VendorResponseDTO> vendors) {
        return vendors.stream()
                .map(vendor -> {
                    List<CategoryMatchResponseDTO> categoryMatches = prepareCategoryMatches(
                            userSelections,
                            vendor.getCategoryOptions()
                    );

                    int vendorMatchPercentage = calculateVendorMatchPercentage(vendor.getCategoryOptions(), userSelections);

                    return new VendorDetailMatchResponseDTO(
                            vendor.getId(),
                            vendor.getName(),
                            vendor.getDescription(),
                            vendorMatchPercentage,
                            categoryMatches
                    );
                })
                .collect(Collectors.toList());
    }


    public List<CategoryMatchResponseDTO> prepareCategoryMatches(Map<Long, List<Long>> userSelections,
                                                         List<CategoryDTO> vendorCategories) {
        // Create a map of vendor categories for quick access
        Map<Long, CategoryDTO> vendorCategoryMap = vendorCategories.stream()
                .collect(Collectors.toMap(CategoryDTO::getId, Function.identity()));

        // Process all user-selected categories
        return userSelections.keySet().stream()
                .map(categoryId -> {
                    CategoryDTO vendorCategory = vendorCategoryMap.get(categoryId);
                    List<Long> selectedOptionIds = userSelections.get(categoryId);

                    if (vendorCategory != null) {
                        // Vendor has this category, calculate matched options
                        List<Long> matchedOptions = vendorCategory.getOptions().stream()
                                .map(OptionDTO::getId)
                                .filter(selectedOptionIds::contains)
                                .collect(Collectors.toList());

                        int matchPercentage = calculateMatchPercentage(matchedOptions.size(), selectedOptionIds.size());
                        return new CategoryMatchResponseDTO(categoryId, matchPercentage, matchedOptions);
                    } else {
                        // Vendor does not have this category, set matchPercentage to 0.0
                        return new CategoryMatchResponseDTO(categoryId, 0, Collections.emptyList());
                    }
                })
                .collect(Collectors.toList());
    }

    public int calculateVendorMatchPercentage(List<CategoryDTO> vendorCategories, Map<Long, List<Long>> userSelections) {
        long matchedOptionCount = vendorCategories.stream()
                .mapToLong(category -> category.getOptions().stream()
                        .map(OptionDTO::getId)
                        .filter(optionId -> userSelections.getOrDefault(category.getId(), Collections.emptyList())
                                .contains(optionId))
                        .count())
                .sum();
        long totalUserSelections = userSelections.values().stream()
                .mapToLong(List::size)
                .sum();

        return calculateMatchPercentage(matchedOptionCount, totalUserSelections);
    }

    public List<VendorMatchOverviewResponseDTO> prepareMatchOverview(Map<Long, List<Long>> userSelections, List<VendorResponseDTO> vendors) {
        return vendors.stream()
                .map(vendor -> VendorMatchOverviewResponseDTO.builder()
                        .id(vendor.getId())
                        .name(vendor.getName())
                        .description(vendor.getDescription())
                        .matchPercentage(calculateVendorMatchPercentage(vendor.getCategoryOptions(), userSelections))
                        .build())
                .sorted(Comparator.comparingDouble(VendorMatchOverviewResponseDTO::getMatchPercentage).reversed())
                .toList();
    }

    private int calculateMatchPercentage(long matchedCount, long totalCount) {
        return totalCount == 0 ? 0 : (int) Math.round((matchedCount * 100.0) / totalCount);
    }

}
