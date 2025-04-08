package com.clm.matching.models;

import java.util.List;
import java.util.Map;

public class MatchOverviewRequestDTO {
    private Long apptype;
    private Map<Long, List<Long>> userSelection;

    public Long getApptype() {
        return apptype;
    }

    public void setApptype(Long apptype) {
        this.apptype = apptype;
    }

    public Map<Long, List<Long>> getUserSelection() {
        return userSelection;
    }

    public void setUserSelection(Map<Long, List<Long>> userSelection) {
        this.userSelection = userSelection;
    }
}