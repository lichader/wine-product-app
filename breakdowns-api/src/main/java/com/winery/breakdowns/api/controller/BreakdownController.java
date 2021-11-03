package com.winery.breakdowns.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/breakdown")
public class BreakdownController {

    @RequestMapping(value = "/year/{lotCode}", method = RequestMethod.GET)
    public ResponseEntity<String> getBreakdownByYear(@PathVariable("lotCode") String lotCode){
        return new ResponseEntity<>("test", HttpStatus.OK);
    }

    @RequestMapping(value = "/variety/{lotCode}", method = RequestMethod.GET)
    public ResponseEntity<String> getBreakdownByVariety(@PathVariable("lotCode") String lotCode){
        return new ResponseEntity<>("test", HttpStatus.OK);
    }

    @RequestMapping(value = "/region/{lotCode}", method = RequestMethod.GET)
    public ResponseEntity<String> getBreakdownByRegion(@PathVariable("lotCode") String lotCode){
        return new ResponseEntity<>("test", HttpStatus.OK);
    }

    @RequestMapping(value = "/year-variety/{lotCode}", method = RequestMethod.GET)
    public ResponseEntity<String> getBreakdownByYearAndVariety(@PathVariable("lotCode") String lotCode){
        return new ResponseEntity<>("test", HttpStatus.OK);
    }

}
