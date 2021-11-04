package com.winery.breakdowns.api.db;

public record ComponentDto (
        double percentage,
        int year,
        String variety,
        String region
) {
}
