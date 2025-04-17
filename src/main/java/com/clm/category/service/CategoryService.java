package com.clm.category.service;

import com.clm.category.models.AppType;
import com.clm.category.models.Category;
import com.clm.category.models.CategoryDTO;
import com.clm.category.models.SubType;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO findById(Long id);
    List<CategoryDTO> getCategoriesWithOptionsByIds(Set<Long> ids);
//    CategoryDTO create(CategoryDTO categoryDTO);
    CategoryDTO update(Long id, CategoryDTO categoryDTO);
    void delete(Long id);
    CategoryDTO addOptionToCategory(Long categoryId, Long optionId);
    CategoryDTO removeOptionFromCategory(Long categoryId, Long optionId);
    Set<Category> prepareCategories(AppType appType, SubType subType, String username, Set<CategoryDTO> categoryDTOS);
    Set<Category> prepareCategoriesForUpdate(AppType appType, SubType subType, String username, Set<CategoryDTO> categoryDTOS);
}
