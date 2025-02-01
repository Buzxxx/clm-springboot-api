package com.clm.matching.processor;

import com.clm.category.models.CategoryDTO;
import com.clm.category.models.OptionDTO;
import com.clm.matching.models.CategoryMatchResponseDTO;
import com.clm.matching.models.OptionMatchResponseDTO;
import com.clm.matching.models.VendorMatchOverviewResponseDTO;
import com.clm.matching.models.VendorMatchResponseDTO;
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
                            filteredOptions
                    );
                })
                .collect(Collectors.toList());
    }

    public List<VendorMatchResponseDTO> prepareVendorResponses(Map<Long, List<Long>> userSelections,
                                                          Map<Long, CategoryDTO> categoryMap,
                                                          List<VendorResponseDTO> vendors) {
        return vendors.stream()
                .map(vendor -> {
                    List<CategoryMatchResponseDTO> categoryMatches = prepareCategoryMatches(
                            userSelections,
                            vendor.getCategoryOptions()
                    );

                    double vendorMatchPercentage = calculateVendorMatchPercentage(categoryMatches);

                    return new VendorMatchResponseDTO(
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

                        double matchPercentage = matchedOptions.isEmpty() ? 0.0
                                : (matchedOptions.size() * 100.0 / selectedOptionIds.size());

                        return new CategoryMatchResponseDTO(categoryId, matchPercentage, matchedOptions);
                    } else {
                        // Vendor does not have this category, set matchPercentage to 0.0
                        return new CategoryMatchResponseDTO(categoryId, 0.0, Collections.emptyList());
                    }
                })
                .collect(Collectors.toList());
    }

    public double calculateVendorMatchPercentage(List<CategoryMatchResponseDTO> categoryMatches) {
        if (categoryMatches.isEmpty()) return 0.0;
        double totalPercentage = categoryMatches.stream()
                .mapToDouble(CategoryMatchResponseDTO::getMatchPercentage)
                .sum();
        return totalPercentage / categoryMatches.size();
    }

    public List<VendorMatchOverviewResponseDTO> prepareMatchOverview(Map<Long, List<Long>> userSelections, List<VendorResponseDTO> vendors) {
        return vendors.stream()
                .map(vendor -> {
                    List<OptionMatchResponseDTO> allOptions = new ArrayList<>();
                    int matchedOptionCount = 0;
                    int totalUserSelectedOptions = userSelections.values().stream().mapToInt(List::size).sum();

                    for (CategoryDTO category : vendor.getCategoryOptions()) {
                        List<Long> selectedOptionIds = userSelections.getOrDefault(category.getId(), Collections.emptyList());

                        List<OptionMatchResponseDTO> vendorOptions = category.getOptions().stream()
                                .map(option -> new OptionMatchResponseDTO(
                                        option.getId(),
                                        option.getName(),
                                        selectedOptionIds.contains(option.getId())
                                )).toList();
                        allOptions.addAll(vendorOptions);
                        matchedOptionCount += vendorOptions.stream().filter(OptionMatchResponseDTO::isMatch).count();
                    }

                    double matchPercentage = totalUserSelectedOptions == 0 ? 0.0 : (matchedOptionCount * 100.0 / totalUserSelectedOptions);

                    return new VendorMatchOverviewResponseDTO(
                            vendor.getId(),
                            vendor.getName(),
                            vendor.getDescription(),
                            matchPercentage,
                            allOptions
                    );
                })
                .toList();
    }


}
