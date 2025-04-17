package com.clm.category.service;

import com.clm.category.models.AppType;
import com.clm.category.models.Category;
import com.clm.category.models.SubType;
import com.clm.category.models.SubTypeDTO;
import com.clm.category.repository.SubTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SubTypeServiceImpl implements SubTypeService{

    private final SubTypeMapper subTypeMapper;
    private final CategoryService categoryService;
    private final SubTypeRepository subTypeRepository;

    public SubTypeServiceImpl(SubTypeMapper subTypeMapper, CategoryService categoryService, SubTypeRepository subTypeRepository) {
        this.subTypeMapper = subTypeMapper;
        this.categoryService = categoryService;
        this.subTypeRepository = subTypeRepository;
    }

    @Override
    public Set<SubType> prepareSubTypes(AppType appType, Set<SubTypeDTO> subTypeDTOS, String username) {
        return subTypeDTOS.stream()
                .map(subTypeDTO -> {
                    SubType subType = subTypeMapper.toEntity(appType, subTypeDTO, username);
                    Set<Category> categories =  categoryService.prepareCategories(appType, subType, username, subTypeDTO.getCategories());
                    subType.setCategories(categories);
                    return subType;
                })
                .collect(Collectors.toSet());

    }

    @Override
    public Set<SubType> prepareSubTypesForUpdate(AppType appType, Set<SubTypeDTO> subTypeDTOS, String username) {
        Set<SubType> subTypeSet = new HashSet<>();
        for(SubTypeDTO subTypeDTO: subTypeDTOS) {
            Long id = subTypeDTO.getId();
            if(id == null) {
                throw  new IllegalArgumentException("SubType id cannot be null");
            }
            SubType subType = subTypeRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("No Subtype found with given id " + id));
            subTypeMapper.updateEntityFromDTO(subTypeDTO, subType);
            subType.setAppType(appType);
            subType.setLast_updated_by(username);
            subType.setCategories(categoryService.prepareCategoriesForUpdate(appType, subType, username, subTypeDTO.getCategories()));
            subTypeSet.add(subType);
        }
        return subTypeSet;
    }
}
