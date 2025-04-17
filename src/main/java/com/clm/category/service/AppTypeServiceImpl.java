package com.clm.category.service;

import com.clm.category.models.AppType;
import com.clm.category.models.AppTypeDTO;
import com.clm.category.models.SubType;
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
    private final SubTypeService subTypeService;

    public AppTypeServiceImpl(AppTypeRepository appTypeRepository, AppTypeMapper appTypeMapper, CategoryService categoryService, SubTypeService subTypeService) {
        this.appTypeRepository = appTypeRepository;
        this.appTypeMapper = appTypeMapper;
        this.categoryService = categoryService;
        this.subTypeService = subTypeService;
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
        Set<SubType> subTypes = subTypeService.prepareSubTypes(appType, appTypeDTO.getSubTypes(), username);
        appType.setSubTypes(subTypes);
        appTypeRepository.save(appType);
    }

    @Override
    public void updateAppType(AppTypeDTO appTypeDTO, String username) {
        Long id = appTypeDTO.getId();
        if(id == null)
            throw  new IllegalArgumentException("AppType id cannot be null");
        AppType appType = appTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No AppType for given id " + id));
        appTypeMapper.updateEntityFromDTO(appTypeDTO, appType);
        appType.setLast_updated_by(username);
        appType.setSubTypes(subTypeService.prepareSubTypesForUpdate(appType, appTypeDTO.getSubTypes(), username));
        appTypeRepository.save(appType);
    }

}
