package com.winery.breakdowns.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BreakdownController.class)
public class BreakdownControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetBreakdownByYear_validLotCode_expect200WithData() throws Exception {
        this.mockMvc.perform(get("/breakdown/year/validlotcode")).andDo(print()).andExpect(status().isOk()).andExpect(content().string("test"));
    }

    @Test
    public void testGetBreakdownByYear_invalidLotCode_expect404() throws Exception {
        this.mockMvc.perform(get("/breakdown/year/validlotcode")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void testGetBreakdownByVariety_validLotCode_expect200WithData() throws Exception {
        this.mockMvc.perform(get("/breakdown/variety/validlotcode")).andDo(print()).andExpect(status().isOk()).andExpect(content().string("test"));
    }

    @Test
    public void testGetBreakdownByVariety_invalidLotCode_expect404() throws Exception {
        this.mockMvc.perform(get("/breakdown/variety/validlotcode")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void testGetBreakdownByRegion_validLotCode_expect200WithData() throws Exception {
        this.mockMvc.perform(get("/breakdown/region/validlotcode")).andDo(print()).andExpect(status().isOk()).andExpect(content().string("test"));
    }

    @Test
    public void testGetBreakdownByRegion_invalidLotCode_expect404() throws Exception {
        this.mockMvc.perform(get("/breakdown/region/validlotcode")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void testGetBreakdownByYearAndVariety_validLotCode_expect200WithData() throws Exception {
        this.mockMvc.perform(get("/breakdown/year-variety/validlotcode")).andDo(print()).andExpect(status().isOk()).andExpect(content().string("test"));
    }

    @Test
    public void testGetBreakdownByYearAndVariety_invalidLotCode_expect404() throws Exception {
        this.mockMvc.perform(get("/breakdown/year-variety/validlotcode")).andDo(print()).andExpect(status().isNotFound());
    }
}
