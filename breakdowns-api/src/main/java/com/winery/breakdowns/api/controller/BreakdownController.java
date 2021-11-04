package com.winery.breakdowns.api.controller;

import com.winery.breakdowns.api.controller.model.BreakdownResponse;
import com.winery.breakdowns.api.db.WineRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/breakdown")
@Slf4j
public class BreakdownController {

    @Autowired
    private WineRepo wineRepo;

    @RequestMapping(value = "/year/{lotCode}", method = RequestMethod.GET)
    public ResponseEntity<BreakdownResponse> getBreakdownByYear(@PathVariable("lotCode") String lotCode){
        return processRequest("year", lotCode);
    }

    private ResponseEntity<BreakdownResponse> processRequest(String type, String lotCode){
        log.info("Received breakdown request for lot: " + lotCode + "by " + type);
        final var wine = wineRepo.getWineByLotCode(lotCode);

        if (wine.isEmpty()){
            log.error("Unable to find wine by lot: " + lotCode);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Resource not found"
            );
        }

        var response = new BreakdownResponseBuilder().type(type).wineDto(wine.get()).build();

        log.info("Returning breakdown response for lot: " + lotCode + "by " + type);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/variety/{lotCode}", method = RequestMethod.GET)
    public ResponseEntity<BreakdownResponse> getBreakdownByVariety(@PathVariable("lotCode") String lotCode){
        return processRequest("variety", lotCode);
    }

    @RequestMapping(value = "/region/{lotCode}", method = RequestMethod.GET)
    public ResponseEntity<BreakdownResponse> getBreakdownByRegion(@PathVariable("lotCode") String lotCode){
        return processRequest("region", lotCode);
    }

    @RequestMapping(value = "/year-variety/{lotCode}", method = RequestMethod.GET)
    public ResponseEntity<BreakdownResponse> getBreakdownByYearAndVariety(@PathVariable("lotCode") String lotCode){
        return processRequest("year-variety", lotCode);
    }

}
