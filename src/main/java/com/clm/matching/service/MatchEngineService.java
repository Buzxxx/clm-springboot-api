package com.clm.matching.service;

import com.clm.matching.models.VendorMatchResponseDTO;

import java.util.List;
import java.util.Map;

public interface MatchEngineService {

    Map<String, Object> getMatchResults(Map<Long, List<Long>> userSelections);
}
