package com.winery.breakdowns.api.db;

import java.util.Optional;

public interface WineRepo {
    Optional<WineDto> getWineByLotCode(String lotCode);
}
