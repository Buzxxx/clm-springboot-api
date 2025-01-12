package com.clm.category.service;

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

    public Option toEntity(OptionDTO dto) {
        Option option = new Option();
        option.setName(dto.getName());
        return option;
    }

    public void updateEntityFromDTO(OptionDTO optionDTO, Option option) {
        option.setName(optionDTO.getName());
    }
}
