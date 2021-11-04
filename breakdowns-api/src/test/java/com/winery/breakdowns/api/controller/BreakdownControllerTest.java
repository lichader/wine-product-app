package com.winery.breakdowns.api.controller;

import com.winery.breakdowns.api.db.ComponentDto;
import com.winery.breakdowns.api.db.WineDto;
import com.winery.breakdowns.api.db.WineRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BreakdownController.class)
public class BreakdownControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    WineRepo wineRepo;

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
    public void testGetBreakdownByYear_validLotCode_expect200WithData() throws Exception {
        final var expectedResponse = readSampleFile("get-by-year-response.json");

        when(wineRepo.getWineByLotCode(exampleDto.lotCode())).thenReturn(Optional.of(exampleDto));
        this.mockMvc.perform(get("/breakdown/year/" + exampleDto.lotCode()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));

        verify(wineRepo, times(1)).getWineByLotCode(exampleDto.lotCode());
    }

    private String readSampleFile(String filename) throws IOException {
        var classLoader = getClass().getClassLoader();
        var path = classLoader.getResource("samples/" + filename).getPath();
        return Files.readString(Paths.get(path));
    }

    @Test
    public void testGetBreakdownByYear_invalidLotCode_expect404() throws Exception {
        when(wineRepo.getWineByLotCode(exampleDto.lotCode())).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/breakdown/year/" + exampleDto.lotCode())).andDo(print()).andExpect(status().isNotFound());

        verify(wineRepo, times(1)).getWineByLotCode(exampleDto.lotCode());

    }

    @Test
    public void testGetBreakdownByVariety_validLotCode_expect200WithData() throws Exception {
        final var expectedResponse = readSampleFile("get-by-variety-response.json");

        when(wineRepo.getWineByLotCode(exampleDto.lotCode())).thenReturn(Optional.of(exampleDto));
        this.mockMvc.perform(get("/breakdown/variety/" + exampleDto.lotCode()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));

        verify(wineRepo, times(1)).getWineByLotCode(exampleDto.lotCode());

    }

    @Test
    public void testGetBreakdownByVariety_invalidLotCode_expect404() throws Exception {
        when(wineRepo.getWineByLotCode(exampleDto.lotCode())).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/breakdown/variety/" + exampleDto.lotCode())).andDo(print()).andExpect(status().isNotFound());

        verify(wineRepo, times(1)).getWineByLotCode(exampleDto.lotCode());

    }

    @Test
    public void testGetBreakdownByRegion_validLotCode_expect200WithData() throws Exception {
        final var expectedResponse = readSampleFile("get-by-region-response.json");

        when(wineRepo.getWineByLotCode(exampleDto.lotCode())).thenReturn(Optional.of(exampleDto));
        this.mockMvc.perform(get("/breakdown/region/" + exampleDto.lotCode()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));

        verify(wineRepo, times(1)).getWineByLotCode(exampleDto.lotCode());

    }

    @Test
    public void testGetBreakdownByRegion_invalidLotCode_expect404() throws Exception {
        when(wineRepo.getWineByLotCode(exampleDto.lotCode())).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/breakdown/region/" + exampleDto.lotCode())).andDo(print()).andExpect(status().isNotFound());

        verify(wineRepo, times(1)).getWineByLotCode(exampleDto.lotCode());
    }

    @Test
    public void testGetBreakdownByYearAndVariety_validLotCode_expect200WithData() throws Exception {
        final var expectedResponse = readSampleFile("get-by-year-variety-response.json");

        when(wineRepo.getWineByLotCode(exampleDto.lotCode())).thenReturn(Optional.of(exampleDto));
        this.mockMvc.perform(get("/breakdown/year-variety/" + exampleDto.lotCode()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));

        verify(wineRepo, times(1)).getWineByLotCode(exampleDto.lotCode());    }

    @Test
    public void testGetBreakdownByYearAndVariety_invalidLotCode_expect404() throws Exception {

        when(wineRepo.getWineByLotCode(exampleDto.lotCode())).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/breakdown/year-variety/" + exampleDto.lotCode())).andDo(print()).andExpect(status().isNotFound());

        verify(wineRepo, times(1)).getWineByLotCode(exampleDto.lotCode());

    }
}
