package com.winery.breakdowns.api.controller;

import com.winery.breakdowns.api.controller.BreakdownResponseBuilder;
import com.winery.breakdowns.api.controller.model.Breakdown;
import com.winery.breakdowns.api.controller.model.BreakdownResponse;
import com.winery.breakdowns.api.db.ComponentDto;
import com.winery.breakdowns.api.db.WineDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BreakdownResponseBuilderTest {

    private WineDto exampleDto;

    @BeforeEach
    void setup() {
        exampleDto = new WineDto(
                "15MPPN002-VK",
                100000.0,
                Optional.of("2015 Mornington Peninsula Pinot Noir - Vintage Kerr special batch"),
                "T100-03",
                Optional.of("Filtered"),
                "Vintage Kerr",
                Arrays.asList(
                        new ComponentDto(60.0, 2015, "Pinot Noir", "Mornington"),
                        new ComponentDto(5.5, 2014, "Pinot Noir", "Yarra Valley"),
                        new ComponentDto(9.5, 2015, "Pinot Noir", "Mornington"),
                        new ComponentDto(25.0, 2013, "Cabernet", "Mornington")
                )
        );
    }

    @Test
    public void buildBreakdownByYear_validType_expectResponseBuilt(){
        var builder = new BreakdownResponseBuilder();

        final var actualResponse = builder.type("year").wineDto(exampleDto).build();

        final var expectedResponse = new BreakdownResponse(
                "year",
                Arrays.asList(
                        new Breakdown("60.0", "2015"),
                        new Breakdown("25.0", "2013"),
                        new Breakdown("9.5", "2015"),
                        new Breakdown("5.5", "2014")
                )
        );

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void buildBreakdownByVariety_validType_expectResponseBuilt(){
        var builder = new BreakdownResponseBuilder();

        final var actualResponse = builder.type("variety").wineDto(exampleDto).build();

        final var expectedResponse = new BreakdownResponse(
                "variety",
                Arrays.asList(
                        new Breakdown("60.0", "Pinot Noir"),
                        new Breakdown("25.0", "Cabernet"),
                        new Breakdown("9.5", "Pinot Noir"),
                        new Breakdown("5.5", "Pinot Noir")
                )
        );

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void buildBreakdownByRegion_validType_expectResponseBuilt(){
        var builder = new BreakdownResponseBuilder();

        final var actualResponse = builder.type("region").wineDto(exampleDto).build();

        final var expectedResponse = new BreakdownResponse(
                "region",
                Arrays.asList(
                        new Breakdown("60.0", "Mornington"),
                        new Breakdown("25.0", "Mornington"),
                        new Breakdown("9.5", "Mornington"),
                        new Breakdown("5.5", "Yarra Valley")
                )
        );

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void buildBreakdownByYearAndVariety_validType_expectResponseBuilt(){
        var builder = new BreakdownResponseBuilder();

        final var actualResponse = builder.type("year-variety").wineDto(exampleDto).build();

        final var expectedResponse = new BreakdownResponse(
                "year-variety",
                Arrays.asList(
                        new Breakdown("60.0", "2015Pinot Noir"),
                        new Breakdown("25.0", "2013Cabernet"),
                        new Breakdown("9.5", "2015Pinot Noir"),
                        new Breakdown("5.5", "2014Pinot Noir")
                )
        );

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void buildBreakdownByVariety_invalidType_expectErrorThrown(){
        var builder = new BreakdownResponseBuilder();

        var exception = assertThrows(IllegalArgumentException.class, () -> {
            builder.type("invalidtype");
        }, "Expect an illegal argument exception");

        assertEquals("Received wrong type: invalidtype", exception.getMessage());
    }
}
