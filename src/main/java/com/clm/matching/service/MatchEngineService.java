package com.clm.matching.service;

import com.clm.matching.models.VendorMatchOverviewResponseDTO;

import java.util.List;
import java.util.Map;

public interface MatchEngineService {

    Map<String, Object> getMatchResults(Map<Long, List<Long>> userSelections, List<Long> vendorIds);
    List<VendorMatchOverviewResponseDTO> getMatchOverview(Long apptype,Map<Long, List<Long>> userSelections);
}
