package com.clm.vendor.processor;

import com.clm.category.models.CategoryDTO;
import com.clm.category.models.OptionDTO;
import com.clm.category.service.CategoryService;
import com.clm.vendor.models.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VendorDataProcessorTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private VendorDataProcessor vendorDataProcessor;

    private Vendor vendor;
    private List<CategoryDTO> mockCategories;

    @BeforeEach
    void setUp() {
        // Vendor category options (category ID -> List of option IDs)
        Map<Long, List<Long>> categoryOptions = Map.of(
                1L, List.of(10L, 20L, 30L),
                2L, List.of(40L, 50L)
        );

        vendor = Vendor.builder(). id(1L).name("Vendor A").categoryOptions(categoryOptions).build();

        // Mock category data
        CategoryDTO category1 = new CategoryDTO(1L, "Category 1", "Desc 1", "",
                Set.of(new OptionDTO(10L, "Option 10", 1L), new OptionDTO(20L, "Option 20", 1L),
                        new OptionDTO(30L, "Option 30", 1L), new OptionDTO(35L, "Option 35", 1L))); // 3 matches, 1 extra

        CategoryDTO category2 = new CategoryDTO(2L, "Category 2", "Desc 2", "",
                Set.of(new OptionDTO(40L, "Option 40", 2L), new OptionDTO(50L, "Option 50", 2L),
                        new OptionDTO(60L, "Option 60", 2L))); // 2 matches, 1 extra

        mockCategories = List.of(category1, category2);
    }

    @Test
    void testBuildCategoryOptions() {
        when(categoryService.getCategoriesWithOptionsByIds(Set.of(1L, 2L))).thenReturn(mockCategories);

        List<CategoryDTO> result = vendorDataProcessor.buildCategoryOptions(vendor);

        assertEquals(2, result.size(), "Vendor should have two categories");

        // Check Category 1 options
        assertEquals(3, result.get(0).getOptions().size(), "Category 1 should only have the vendor’s selected options");
        assertEquals(Set.of(10L, 20L, 30L),
                result.get(0).getOptions().stream().map(OptionDTO::getId).collect(Collectors.toSet()),
                "Category 1 options should be correctly filtered");

        // Check Category 2 options
        assertEquals(2, result.get(1).getOptions().size(), "Category 2 should only have the vendor’s selected options");
        assertEquals(Set.of(40L, 50L),
                result.get(1).getOptions().stream().map(OptionDTO::getId).collect(Collectors.toSet()),
                "Category 2 options should be correctly filtered");
    }
}
