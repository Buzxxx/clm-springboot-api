package com.clm.category.service;

import com.clm.category.models.Category;
import com.clm.category.models.OptionDTO;
import com.clm.category.models.Option;
import org.springframework.stereotype.Component;

@Component
public class OptionMapper {

    public OptionDTO toDTO(Option option) {
       return OptionDTO.builder()
                .id(option.getId())
                .name(option.getName())
               .categoryId(option.getCategory().getId())
                .build();
    }

    public Option toEntity(OptionDTO dto, Category category, String username) {
        return Option.builder()
                .name(dto.getName())
                .category(category)
                .build();
    }

    public void updateEntityFromDTO(OptionDTO optionDTO, Option option, Category category, String username)
    {
        option.setName(optionDTO.getName());
        option.setCategory(category);
        option.setLast_updated_by(username);
    }
}
