package com.winery.breakdowns.api.db;

import java.util.List;
import java.util.Optional;

public record WineDto(
    String lotCode,
    double volume,
    Optional<String> description,
    String tankCode,
    Optional<String> productState,
    String ownerName,
    List<ComponentDto> components
){}