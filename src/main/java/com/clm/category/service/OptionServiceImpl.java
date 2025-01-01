package com.clm.category.service;

import com.clm.category.api.OptionDTO;
import com.clm.category.entity.Category;
import com.clm.category.entity.Option;
import com.clm.category.repository.CategoryRepository;
import com.clm.category.repository.OptionRepository;
import com.clm.category.utils.OptionMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OptionServiceImpl implements OptionService {
    private final OptionRepository optionRepository;
    private final CategoryRepository categoryRepository;
    private final OptionMapper optionMapper;

    public OptionServiceImpl(OptionRepository optionRepository,
                             CategoryRepository categoryRepository,
                             OptionMapper optionMapper) {
        this.optionRepository = optionRepository;
        this.categoryRepository = categoryRepository;
        this.optionMapper = optionMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OptionDTO> findAll() {
        return optionRepository.findAll().stream()
                .map(optionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OptionDTO findById(Long id) {
        return optionRepository.findById(id)
                .map(optionMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Option not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OptionDTO> findByCategoryId(Long categoryId) {
        return optionRepository.findByCategoryId(categoryId).stream()
                .map(optionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OptionDTO create(OptionDTO optionDTO) {
        Option option = optionMapper.toEntity(optionDTO);

        if (optionDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(optionDTO.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + optionDTO.getCategoryId()));
            option.setCategory(category);
        }

        option = optionRepository.save(option);
        return optionMapper.toDTO(option);
    }

    @Override
    public OptionDTO update(Long id, OptionDTO optionDTO) {
        Option option = optionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Option not found with id: " + id));

        optionMapper.updateEntityFromDTO(optionDTO, option);

        if (optionDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(optionDTO.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + optionDTO.getCategoryId()));
            option.setCategory(category);
        } else {
            option.setCategory(null);
        }

        option = optionRepository.save(option);
        return optionMapper.toDTO(option);
    }

    @Override
    public void delete(Long id) {
        if (!optionRepository.existsById(id)) {
            throw new EntityNotFoundException("Option not found with id: " + id);
        }
        optionRepository.deleteById(id);
    }

    @Override
    public OptionDTO moveToCategory(Long optionId, Long newCategoryId) {
        Option option = optionRepository.findById(optionId)
                .orElseThrow(() -> new EntityNotFoundException("Option not found with id: " + optionId));

        Category newCategory = categoryRepository.findById(newCategoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + newCategoryId));

        option.setCategory(newCategory);
        option = optionRepository.save(option);
        return optionMapper.toDTO(option);
    }

}