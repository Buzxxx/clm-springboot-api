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

    private final SubTypeMapper subTypeMapper;

    public AppTypeMapper(SubTypeMapper subTypeMapper) {
        this.subTypeMapper = subTypeMapper;
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
                        .map(subTypeMapper::mapSubType)
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

    public void updateEntityFromDTO(AppTypeDTO appTypeDTO, AppType appType) {
        appType.setName(appTypeDTO.getName());
        appType.setDescription(appTypeDTO.getDescription());
        appType.setImage(appTypeDTO.getImage());
    }

}
