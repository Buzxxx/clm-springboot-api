package com.clm.category.utils;

import com.clm.category.api.CategoryDTO;
import com.clm.category.entity.Category;
import org.springframework.stereotype.Component;

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
                .options(category.getOptions().stream()
                        .map(optionMapper::toDTO)
                        .collect(Collectors.toSet()))
                    .build();
    }

    public Category toEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    public void updateEntityFromDTO(CategoryDTO dto, Category category) {
        category.setName(dto.getName());
    }
}
