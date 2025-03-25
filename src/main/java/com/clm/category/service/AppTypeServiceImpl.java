package com.clm.category.service;

import com.clm.category.models.AppType;
import com.clm.category.models.AppTypeDTO;
import com.clm.category.repository.AppTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppTypeServiceImpl implements AppTypeService{

    private final AppTypeRepository appTypeRepository;
    private final AppTypeMapper appTypeMapper;

    public AppTypeServiceImpl(AppTypeRepository appTypeRepository, AppTypeMapper appTypeMapper) {
        this.appTypeRepository = appTypeRepository;
        this.appTypeMapper = appTypeMapper;
    }

    @Override
    public List<AppTypeDTO> getAllAppTypes() {
        List<AppType> appTypes = appTypeRepository.findAll();
        return appTypes.stream().map(appTypeMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public AppTypeDTO getAppTypeDetailsById(Long id) {
        AppType appType = appTypeRepository.findByIdWithSubTypesAndCategoriesAndOptions(id)
                .orElseThrow(() -> new EntityNotFoundException("AppType not found"));
        return appTypeMapper.toDetailsDTO(appType);
    }
}
