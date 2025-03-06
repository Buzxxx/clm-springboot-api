package com.clm.category.service;

import com.clm.category.models.CategoryDTO;
import com.clm.category.models.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    private final OptionMapper optionMapper;


    public CategoryMapper(OptionMapper optionMapper) {
        this.optionMapper = optionMapper;
    }

    public CategoryDTO toDTO(Category category) {
           return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .image(category.getImage())
                .options(category.getOptions().stream()
                        .map(optionMapper::toDTO)
                        .collect(Collectors.toSet()))
                    .build();
    }

    public List<CategoryDTO> toDTOList(List<Category> categories) {
        return categories.stream()
                .map(this::toDTO) // Reuse the existing single-entity mapping method
                .collect(Collectors.toList());
    }

    public Category toEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }

    public void updateEntityFromDTO(CategoryDTO dto, Category category) {
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
    }
}
