package com.clm.category.service;

import com.clm.category.models.AppType;
import com.clm.category.models.AppTypeDTO;
import com.clm.category.models.SubType;
import com.clm.category.models.SubTypeDTO;
import org.springframework.stereotype.Component;

import java.util.Set;
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
                .description(appType.getDescription())
                .image(appType.getImage())
                .build();
    }

    public AppTypeDTO toDetailsDTO(AppType appType) {
        return new AppTypeDTO(
                appType.getId(),
                appType.getName(),
                appType.getDescription(),
                appType.getImage(),
                appType.getSubTypes().stream()
                        .map(this::mapSubType)
                        .collect(Collectors.toSet())
        );
    }

    public SubTypeDTO mapSubType(SubType subType) {
        return new SubTypeDTO(
                subType.getId(),
                subType.getName(),
                subType.getCategories().stream()
                        .map(categoryMapper::toDTO)
                        .collect(Collectors.toSet())
        );
    }

    public AppType toEntity(AppTypeDTO appTypeDTO, String username) {

        return AppType.builder()
                .name(appTypeDTO.getName())
                .description(appTypeDTO.getDescription())
                .image(appTypeDTO.getImage())
                .created_by(username)
                .last_updated_by(username)
                .build();
    }

    public SubType toSubTypeEntity(AppType appType, SubTypeDTO subTypeDTO, String username) {
        return SubType.builder()
                .name(subTypeDTO.getName())
                .created_by(username)
                .last_updated_by(username)
                .appType(appType)
                .build();
    }

    public Set<SubType> toSubTypeEntitySet(AppType appType, Set<SubTypeDTO> subTypeDTOS, String username) {
        return subTypeDTOS.stream()
                .map(subTypeDTO -> { return this.toSubTypeEntity(appType, subTypeDTO, username);})
                .collect(Collectors.toSet());
    }
}
