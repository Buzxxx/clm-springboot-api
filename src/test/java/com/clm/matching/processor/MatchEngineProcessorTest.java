package com.clm.matching.processor;

import com.clm.category.models.CategoryDTO;
import com.clm.category.models.OptionDTO;
import com.clm.matching.models.CategoryMatchResponseDTO;
import com.clm.matching.models.VendorDetailMatchResponseDTO;
import com.clm.matching.models.VendorMatchOverviewResponseDTO;
import com.clm.vendor.models.VendorResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchEngineProcessorTest {

    private MatchEngineProcessor matchEngineProcessor;

    private Map<Long, List<Long>> userSelections;
    private List<VendorResponseDTO> vendorList;
    private List<CategoryDTO> categoryList;

    @BeforeEach
    void setUp() {
        matchEngineProcessor = new MatchEngineProcessor();

        // User selected options
        userSelections = new HashMap<>();
        userSelections.put(1L, Arrays.asList(10L, 20L, 30L, 31L, 32L, 33L, 34L, 35L, 36L, 37L)); // 10 options
        userSelections.put(2L, Arrays.asList(40L, 41L, 42L, 43L)); // 4 options

        // Vendor's options for each category
        CategoryDTO category1 = new CategoryDTO(1L, "Category 1", "Desc 1", "",
                Set.of(new OptionDTO(10L, "Option 10", 1L), new OptionDTO(20L, "Option 20", 1L),
                        new OptionDTO(30L, "Option 30", 1L), new OptionDTO(31L, "Option 31",1L),
                        new OptionDTO(32L, "Option 32", 1L))); // 5 matches out of 10

        CategoryDTO category2 = new CategoryDTO(2L, "Category 2", "Desc 2", "",
                Set.of(new OptionDTO(40L, "Option 40", 2L), new OptionDTO(41L, "Option 41", 2L),
                        new OptionDTO(42L, "Option 42",2L), new OptionDTO(43L, "Option 43", 2L))); // 4 matches out of 4

        categoryList = List.of(category1, category2);

        // Vendor list (with categories)
        VendorResponseDTO vendorA = VendorResponseDTO.builder(). id(1L).name("Vendor A"). description("Description A").categoryOptions(List.of(category1, category2)).build();
        vendorList = List.of(vendorA);
    }

    @Test
    void testPrepareMatchOverview() {
        List<VendorMatchOverviewResponseDTO> result = matchEngineProcessor.prepareMatchOverview(userSelections, vendorList);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(64, result.get(0).getMatchPercentage(), "Match Overview percentage should be correct");
    }

    @Test
    void testPrepareVendorResponsesForMatchDetails() {
        Map<Long, CategoryDTO> categoryMap = Map.of(1L, categoryList.get(0), 2L, categoryList.get(1));

        List<VendorDetailMatchResponseDTO> result = matchEngineProcessor.prepareVendorResponsesForMatchDetails(userSelections, categoryMap, vendorList);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(64, result.get(0).getMatchPercentage(), "Match Details percentage should be correct");
    }

    @Test
    void testPrepareCategoryMatches() {
        List<CategoryMatchResponseDTO> result = matchEngineProcessor.prepareCategoryMatches(userSelections, categoryList);

        assertEquals(2, result.size());

        // Category 1: 5 matches out of 10 = 50%
        assertEquals(1L, result.get(0).getCategoryId());
        assertEquals(50, result.get(0).getMatchPercentage(), "Category 1 match percentage should be 50%");

        // Category 2: 4 matches out of 4 = 100%
        assertEquals(2L, result.get(1).getCategoryId());
        assertEquals(100, result.get(1).getMatchPercentage(), "Category 2 match percentage should be 100%");
    }
}
