package com.clm.matching.api;

import com.clm.matching.models.VendorMatchResponseDTO;
import com.clm.matching.service.MatchEngineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/match-engine")
public class MatchEngineController {

    private final MatchEngineService matchEngineService;

    public MatchEngineController(MatchEngineService matchEngineService) {
        this.matchEngineService = matchEngineService;
    }

    @PostMapping("/match")
    public ResponseEntity<Map<String, Object>> getMatchResults(@RequestBody Map<Long, List<Long>> userSelections) {
        Map<String, Object> matchResults = matchEngineService.getMatchResults(userSelections);
        return ResponseEntity.ok(matchResults);
    }
}
