package com.clm.category.service;

import com.clm.category.models.*;
import com.clm.category.repository.CategoryRepository;
import com.clm.category.repository.OptionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final OptionRepository optionRepository;
    private final CategoryMapper categoryMapper;
    private final OptionService optionService;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               OptionRepository optionRepository,
                               CategoryMapper categoryMapper, OptionService optionService) {
        this.categoryRepository = categoryRepository;
        this.optionRepository = optionRepository;
        this.categoryMapper = categoryMapper;
        this.optionService = optionService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> allCategories = categoryRepository.findAllWithOptions();
        return categoryMapper.toDTOList(allCategories);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        return categoryRepository.findByIdWithOptions(id)
                .map(categoryMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
    }

    @Override
    public List<CategoryDTO> getCategoriesWithOptionsByIds(Set<Long> ids) {
        List<Category> categories = categoryRepository.findByIdsWithOptions(ids);
        return categoryMapper.toDTOList(categories);
    }

//    @Override
//    public CategoryDTO create(CategoryDTO categoryDTO) {
//        Category category = categoryMapper.toEntity(categoryDTO);
//        category = categoryRepository.save(category);
//        return categoryMapper.toDTO(category);
//    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findByIdWithOptions(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));

        categoryMapper.updateEntityFromDTO(categoryDTO, category);
        category = categoryRepository.save(category);
        return categoryMapper.toDTO(category);
    }

    @Override
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO addOptionToCategory(Long categoryId, Long optionId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));

        Option option = optionRepository.findById(optionId)
                .orElseThrow(() -> new EntityNotFoundException("Option not found with id: " + optionId));

        category.addOption(option);
        category = categoryRepository.save(category);
        return categoryMapper.toDTO(category);
    }

    @Override
    public CategoryDTO removeOptionFromCategory(Long categoryId, Long optionId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));

        Option option = optionRepository.findById(optionId)
                .orElseThrow(() -> new EntityNotFoundException("Option not found with id: " + optionId));

        category.removeOption(option);
        category = categoryRepository.save(category);
        return categoryMapper.toDTO(category);
    }

    @Override
    public Set<Category> prepareCategories(AppType appType, SubType subType, String username, Set<CategoryDTO> categoryDTOS) {
        return categoryDTOS.stream()
                .map(categoryDTO -> {
                    Category category = categoryMapper.toEntity(appType, subType, categoryDTO, username);
                    category.setOptions(optionService.prepareOptions(category, categoryDTO.getOptions(), username));
                    return category;
                }).collect(Collectors.toSet());
    }

    @Override
    public Set<Category> prepareCategoriesForUpdate(AppType appType, SubType subType, String username, Set<CategoryDTO> categoryDTOS) {
        Set<Category> categorySet = new HashSet<>();
        for(CategoryDTO categoryDTO: categoryDTOS) {
            Long id = categoryDTO.getId();
            if(id == null) {
                throw new IllegalArgumentException("Category id cannot be null");
            }
            Category category = categoryRepository.findByIdWithOptions(id)
                    .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
            categoryMapper.updateEntityFromDTO(categoryDTO, category);
            category.setAppType(appType);
            category.setSubType(subType);
            category.setLast_updated_by(username);
            category.setOptions(optionService.prepareOptionsForUpdate(category, categoryDTO.getOptions(), username));
            categorySet.add(category);
        }
        return  categorySet;
    }
}

