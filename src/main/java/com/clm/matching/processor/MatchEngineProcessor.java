package com.clm.matching.processor;

import com.clm.category.models.CategoryDTO;
import com.clm.category.models.OptionDTO;
import com.clm.matching.models.CategoryMatchResponseDTO;
import com.clm.matching.models.OptionMatchResponseDTO;
import com.clm.matching.models.VendorMatchResponseDTO;
import com.clm.vendor.models.VendorResponseDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MatchEngineProcessor {

    public Map<Long, CategoryDTO> mapCategoriesById(List<CategoryDTO> categoryList) {
        return categoryList.stream()
                .collect(Collectors.toMap(CategoryDTO::getId, category -> category));
    }

    public List<VendorMatchResponseDTO> calculateMatches(
            List<VendorResponseDTO> vendors,
            Map<Long, CategoryDTO> categoryMap,
            Map<Long, List<Long>> userSelections) {

        List<VendorMatchResponseDTO> matchResponses = new ArrayList<>();

        for(VendorResponseDTO vendor : vendors) {
            Map<CategoryDTO, List<OptionDTO>> vendorCategories = vendor.getCategoryOptions();

            double totalMatchPercentage = 0.0;
            List<CategoryMatchResponseDTO> categoryMatchResponseDTOS = new ArrayList<>();

            for(Map.Entry<Long, List<Long>> entry: userSelections.entrySet()) {
                Long categoryId = entry.getKey();
                List<Long> selectedOptionIds = entry.getValue();

                CategoryDTO category = categoryMap.get(categoryId);
                List<OptionDTO> vendorOptions = vendorCategories.getOrDefault(category, List.of());

                CategoryMatchResponseDTO categoryMatchResponseDTO = calculateCategoryMatch(
                        category,
                        vendorOptions,
                        selectedOptionIds
                );
                categoryMatchResponseDTOS.add(categoryMatchResponseDTO);

                totalMatchPercentage += categoryMatchResponseDTO.getMatchPercentage();

            }

            double overallMatchPercentage = totalMatchPercentage / userSelections.size();

            matchResponses.add(VendorMatchResponseDTO.builder()
                    .vendorDTO(vendor)
                    .matchPercentage(overallMatchPercentage)
                    .categories(categoryMatchResponseDTOS)
                    .build());
        }
        return matchResponses;
    }

    private CategoryMatchResponseDTO calculateCategoryMatch(
            CategoryDTO category,
            List<OptionDTO> vendorOptions,
            List<Long> selectedOptionIds) {

        int matchedOptions = 0;

        List<OptionMatchResponseDTO> optionMatchResponseDTOS = new ArrayList<>();

        for(Long selectedOptionId: selectedOptionIds) {
            OptionDTO selectedOption = category.getOptions().stream()
                    .filter(option -> option.getId().equals(selectedOptionId))
                    .findFirst()
                    .orElse(null);

            boolean isMatch = vendorOptions.stream()
                    .anyMatch(option -> option.getId().equals(selectedOptionId));
            if (isMatch) matchedOptions++;

            optionMatchResponseDTOS.add(OptionMatchResponseDTO.builder().optionDTO(selectedOption).isMatch(isMatch).build());

        }

        double categoryMatchPercentage = (matchedOptions / (double) selectedOptionIds.size()) * 100;

        return CategoryMatchResponseDTO.builder()
                .categoryDTO(category)
                .matchPercentage(categoryMatchPercentage)
                .options(optionMatchResponseDTOS)
                .build();
    }
}
