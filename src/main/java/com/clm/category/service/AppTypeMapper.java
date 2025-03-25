package com.clm.category.service;

import com.clm.category.models.AppType;
import com.clm.category.models.AppTypeDTO;
import com.clm.category.models.SubType;
import com.clm.category.models.SubTypeDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AppTypeMapper {

    private final CategoryMapper categoryMapper;

    public AppTypeMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public AppTypeDTO toDTO(AppType appType) {
        return AppTypeDTO
                .builder()
                .id(appType.getId())
                .name(appType.getName())
                .build();
    }

    public AppTypeDTO toDetailsDTO(AppType appType) {
        return new AppTypeDTO(
                appType.getId(),
                appType.getName(),
                appType.getSubTypes().stream()
                        .map(this::mapSubType)
                        .collect(Collectors.toList())
        );
    }

    public SubTypeDTO mapSubType(SubType subType) {
        return new SubTypeDTO(
                subType.getId(),
                subType.getName(),
                subType.getCategories().stream()
                        .map(categoryMapper::toDTO)
                        .collect(Collectors.toList())
        );
    }
}
