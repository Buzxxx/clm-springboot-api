package com.clm.matching.models;

import com.clm.category.models.OptionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionMatchResponseDTO {

    private Long id;
    private String name;    private boolean isMatch;
}
