package com.clm.category.service;

import com.clm.category.models.AppType;
import com.clm.category.models.SubType;
import com.clm.category.models.SubTypeDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SubTypeMapper {

    private final CategoryMapper categoryMapper;

    public SubTypeMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public SubType toEntity(AppType appType, SubTypeDTO subTypeDTO, String username) {
        return SubType.builder()
                .name(subTypeDTO.getName())
                .created_by(username)
                .last_updated_by(username)
                .appType(appType)
                .build();
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


    public void updateEntityFromDTO(SubTypeDTO subTypeDTO, SubType subType) {
        subType.setName(subTypeDTO.getName());
    }
}
