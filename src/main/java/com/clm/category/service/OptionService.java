package com.clm.category.service;

import com.clm.category.models.Category;
import com.clm.category.models.Option;
import com.clm.category.models.OptionDTO;

import java.util.List;
import java.util.Set;

public interface OptionService {

    List<OptionDTO> findAll();
    OptionDTO findById(Long id);
    List<OptionDTO> findByCategoryId(Long categoryId);
//    OptionDTO create(OptionDTO optionDTO);
//    OptionDTO update(Long id, OptionDTO optionDTO);
    void delete(Long id);
    OptionDTO moveToCategory(Long optionId, Long newCategoryId);

    Set<Option> prepareOptions(Category category, Set<OptionDTO> optionDTOS, String username);
    Set<Option> prepareOptionsForUpdate(Category category, Set<OptionDTO> optionDTOS, String username);
}
