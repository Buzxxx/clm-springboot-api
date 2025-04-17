package com.clm.category.service;

import com.clm.category.models.AppType;
import com.clm.category.models.CategoryDTO;
import com.clm.category.models.Category;
import com.clm.category.models.SubType;
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

    public Category toEntity(AppType appType, SubType subType, CategoryDTO dto, String username) {
        return Category.builder()
                .name(dto.getName())
                .image(dto.getImage())
                .description(dto.getDescription())
                .subType(subType)
                .appType(appType)
                .created_by(username)
                .last_updated_by(username)
                .build();
    }

    public void updateEntityFromDTO(CategoryDTO dto, Category category) {
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setImage(dto.getImage());
    }
}
