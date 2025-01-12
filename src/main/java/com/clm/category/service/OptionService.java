package com.clm.category.service;

import com.clm.category.models.OptionDTO;

import java.util.List;

public interface OptionService {

    List<OptionDTO> findAll();
    OptionDTO findById(Long id);
    List<OptionDTO> findByCategoryId(Long categoryId);
    OptionDTO create(OptionDTO optionDTO);
    OptionDTO update(Long id, OptionDTO optionDTO);
    void delete(Long id);
    OptionDTO moveToCategory(Long optionId, Long newCategoryId);

}
