package com.clm.matching.service;

import com.clm.matching.models.VendorMatchResponseDTO;

import java.util.List;
import java.util.Map;

public interface MatchEngineService {

    List<VendorMatchResponseDTO> getMatchResults(Map<Long, List<Long>> userSelections);
}
