package com.clm.matching.models;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MatchDetailsRequestDTO {

    private Map<Long, List<Long>> userSelections;
    private List<Long> vendorIds;
}
