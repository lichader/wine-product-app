package com.winery.breakdowns.api.db;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InMemoryWineRepoImplTest {

    private InMemoryWineRepoImpl subject;

    @BeforeAll
    public void setup(){
        subject = new InMemoryWineRepoImpl();
    }

    @Test
    void getWineByLotCode_validLotCode_expectOneResult() {
        final var lotCode = "11YVCHAR001";

        final var result = subject.getWineByLotCode(lotCode);

        assertTrue(result.isPresent());
        assertEquals(lotCode, result.get().lotCode());
    }

    @Test
    void getWineByLotCode_invalidLotCode_expectNoneResult(){
        final var lotCode = "notexist";

        final var result = subject.getWineByLotCode(lotCode);
        assertTrue(result.isEmpty());
    }
}
