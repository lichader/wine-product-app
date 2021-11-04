package com.winery.breakdowns.api.controller;

import com.winery.breakdowns.api.controller.model.Breakdown;
import com.winery.breakdowns.api.controller.model.BreakdownResponse;
import com.winery.breakdowns.api.db.ComponentDto;
import com.winery.breakdowns.api.db.WineDto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BreakdownResponseBuilder {
    private final static String TYPE_YEAR = "year";
    private final static String TYPE_VARIETY = "variety";
    private final static String TYPE_REGION = "region";
    private final static String TYPE_YEAR_VARIETY = "year-variety";

    private final static List<String> TYPES = Arrays.asList(TYPE_YEAR, TYPE_VARIETY, TYPE_REGION, TYPE_YEAR_VARIETY);

    private String type;
    private WineDto wineDto;

    private Stream<ComponentDto> componentsSortedByPriorityDesc;

    public BreakdownResponseBuilder type(String type){
        if (!isValidType(type)){
            throw new IllegalArgumentException("Received wrong type: " + type);
        }
        this.type = type;
        return this;
    }

    private boolean isValidType(String type){
        return TYPES.contains(type);
    }

    public BreakdownResponseBuilder wineDto(WineDto wineDto){
        this.wineDto = wineDto;
        this.componentsSortedByPriorityDesc = this.wineDto.components().stream()
                        .sorted(
                                Comparator.comparing(ComponentDto::percentage)
                                            .reversed()
                        );
        return this;
    }

    public BreakdownResponse build(){
        // probably can be decomposed into different builder for different type in the future
        return switch (this.type){
            case TYPE_YEAR -> buildYearBreakdown();
            case TYPE_REGION -> buildRegionBreakdown();
            case TYPE_VARIETY -> buildVarietyBreakdown();
            case TYPE_YEAR_VARIETY -> buildYearVarietyBreakdown();
            default -> throw new IllegalArgumentException("Received invalid type: " + this.type);
        };
    }

    private BreakdownResponse buildYearBreakdown(){
         var breakdown = this.componentsSortedByPriorityDesc.map(o -> {
            return new Breakdown(String.valueOf(o.percentage()), String.valueOf(o.year()));
        }).collect(Collectors.toList());

        return new BreakdownResponse(TYPE_YEAR, breakdown);
    }

    private BreakdownResponse buildVarietyBreakdown(){
        var breakdown = this.componentsSortedByPriorityDesc.map(o -> {
            return new Breakdown(String.valueOf(o.percentage()), o.variety());
        }).collect(Collectors.toList());

        return new BreakdownResponse(TYPE_VARIETY, breakdown);
    }

    private BreakdownResponse buildRegionBreakdown() {
        var breakdown = this.componentsSortedByPriorityDesc.map(o -> {
            return new Breakdown(String.valueOf(o.percentage()), o.region());
        }).collect(Collectors.toList());

        return new BreakdownResponse(TYPE_REGION, breakdown);
    }

    private BreakdownResponse buildYearVarietyBreakdown() {
        var breakdown = this.componentsSortedByPriorityDesc.map(o -> {
            return new Breakdown(String.valueOf(o.percentage()), "%d%s".formatted(o.year(), o.variety()));
        }).collect(Collectors.toList());

        return new BreakdownResponse(TYPE_YEAR_VARIETY, breakdown);
    }
}


