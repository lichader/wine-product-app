package com.winery.breakdowns.api.controller.model;

import java.util.List;

public record BreakdownResponse (
        String breakdownType,
        List<Breakdown> breakdown
){}


