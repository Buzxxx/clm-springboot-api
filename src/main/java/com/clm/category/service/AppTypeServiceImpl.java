package com.clm.category.service;

import com.clm.category.models.*;
import com.clm.category.repository.AppTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppTypeServiceImpl implements AppTypeService{

    private final AppTypeRepository appTypeRepository;
    private final AppTypeMapper appTypeMapper;
    private final CategoryService categoryService;

    public AppTypeServiceImpl(AppTypeRepository appTypeRepository, AppTypeMapper appTypeMapper, CategoryService categoryService) {
        this.appTypeRepository = appTypeRepository;
        this.appTypeMapper = appTypeMapper;
        this.categoryService = categoryService;
    }

    @Transactional
    @Override
    public List<AppTypeDTO> getAllAppTypes() {
        List<AppType> appTypes = appTypeRepository.findAll();
        return appTypes.stream().map(appTypeMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public AppTypeDTO getAppTypeDetailsById(Long id) {
        AppType appType = appTypeRepository.findByIdWithSubTypesAndCategoriesAndOptions(id)
                .orElseThrow(() -> new EntityNotFoundException("AppType not found"));
        return appTypeMapper.toDetailsDTO(appType);
    }

    @Override
    @Transactional
    public void createAppType(AppTypeDTO appTypeDTO, String username) {
        AppType appType = appTypeMapper.toEntity(appTypeDTO, username);
        Set<SubType> subTypes = prepareSubTypes(appType, appTypeDTO.getSubTypes(), username);
        appType.setSubTypes(subTypes);
        appTypeRepository.save(appType);
    }

    @Override
    public Set<SubType> prepareSubTypes(AppType appType, Set<SubTypeDTO> subTypeDTOS, String username) {

        return subTypeDTOS.stream()
                .map(subTypeDTO -> {
                    SubType subType = appTypeMapper.toSubTypeEntity(appType, subTypeDTO, username);
                    Set<Category> categories =  categoryService.prepareCategories(appType, subType, username, subTypeDTO.getCategories());
                    subType.setCategories(categories);
                    return subType;
                })
                .collect(Collectors.toSet());
    }
}
