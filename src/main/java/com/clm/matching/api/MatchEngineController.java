package com.clm.matching.api;

import com.clm.matching.models.MatchDetailsRequestDTO;
import com.clm.matching.models.MatchOverviewRequestDTO;
import com.clm.matching.models.VendorMatchOverviewResponseDTO;
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

    // Constructor to initialize MatchEngineService
    public MatchEngineController(MatchEngineService matchEngineService) {
        this.matchEngineService = matchEngineService;
    }

    /**
     * Endpoint to get match results based on user selections and vendor IDs.
     *
     * @param matchDetailsRequestDTO Request body containing user selections and vendor IDs.
     * @return ResponseEntity containing match results as a map.
     */
    @PostMapping("/matchDetails")
    public ResponseEntity<Map<String, Object>> getMatchResults(@RequestBody MatchDetailsRequestDTO matchDetailsRequestDTO) {
        Map<String, Object> matchResults = matchEngineService.getMatchResults(matchDetailsRequestDTO.getUserSelections(), matchDetailsRequestDTO.getVendorIds());
        return ResponseEntity.ok(matchResults);
    }

    /**
     * Endpoint to get an overview of vendor matches based on user selections.
     *
     * @param userSelections Request body containing user selections mapped to vendor IDs.
     * @return ResponseEntity containing a list of vendor match overview responses.
     * 
     * {apptype:1,userSelection:{2:[2,5,6,6],4:[2,3,5,4,54]}}
     */
    @PostMapping("/matchOverview")
    public ResponseEntity<List<VendorMatchOverviewResponseDTO>> getMatchOverview(
            @RequestBody MatchOverviewRequestDTO request) {

        if (request.getApptype() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        List<VendorMatchOverviewResponseDTO> response = matchEngineService.getMatchOverview(request.getApptype(),request.getUserSelection());
        return ResponseEntity.ok(response);
    }
    
}
