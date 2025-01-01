package com.clm.category.service;

import com.clm.category.api.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO findById(Long id);
    CategoryDTO create(CategoryDTO categoryDTO);
    CategoryDTO update(Long id, CategoryDTO categoryDTO);
    void delete(Long id);
    CategoryDTO addOptionToCategory(Long categoryId, Long optionId);
    CategoryDTO removeOptionFromCategory(Long categoryId, Long optionId);
}
