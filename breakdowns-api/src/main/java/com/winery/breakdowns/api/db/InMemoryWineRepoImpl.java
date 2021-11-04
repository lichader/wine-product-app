package com.winery.breakdowns.api.db;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InMemoryWineRepoImpl implements WineRepo{
    public Map<String, WineDto> store;

    public InMemoryWineRepoImpl(){
        var tempMap = new HashMap<String, WineDto>();

        var wine11YVCHAR001 = new WineDto(
                "11YVCHAR001",
                1000.0,
                Optional.of("2011 Yarra Valley Chardonnay"),
                "T25-01",
                Optional.of("Ready for bottling"),
                "YV Wines Pty Ltd",
                Arrays.asList(
                        new ComponentDto(5.0, 2011, "Pinot Noir", "Mornington"),
                        new ComponentDto(80.0, 2011, "Chardonnay", "Yarra Valley"),
                        new ComponentDto(5.0, 2010, "Pinot Noir", "Macedon"),
                        new ComponentDto(10.0, 2010, "Chardonnay", "Macedon")
                )
        );
        tempMap.put(wine11YVCHAR001.lotCode(), wine11YVCHAR001);

        var wine12YVCHAR001 = new WineDto(
                "11YVCHAR002",
                5077.0,
                Optional.empty(),
                "T25-06",
                Optional.empty(),
                "YV Wines P/L and Vintage Kerr Joint Venture",
                Arrays.asList(
                        new ComponentDto(5.0, 2011, "Pinot Noir", "Mornington"),
                        new ComponentDto(80.0, 2011, "Chardonnay", "Yarra Valley"),
                        new ComponentDto(5.0, 2010, "Pinot Noir", "Macedon"),
                        new ComponentDto(10.0, 2010, "Chardonnay", "Macedon")
                )
        );
        tempMap.put(wine12YVCHAR001.lotCode(), wine11YVCHAR001);

        var wine15MPPN002VK = new WineDto(
                "15MPPN002-VK",
                100000.0,
                Optional.of("2015 Mornington Peninsula Pinot Noir - Vintage Kerr special batch"),
                "T100-03",
                Optional.of("Filtered"),
                "Vintage Kerr",
                Arrays.asList(
                        new ComponentDto(60.0, 2015, "Pinot Noir", "Mornington"),
                        new ComponentDto(2.0, 2015, "Pinot Noir", "Yarra Valley"),
                        new ComponentDto(5.0, 2014, "Pinot Noir", "Yarra Valley"),
                        new ComponentDto(3.0, 2010, "Chardonnay", "Macedon"),
                        new ComponentDto(1.0, 2015, "Shiraz", "Mornington"),
                        new ComponentDto(2.0, 2015, "Zinfandel", "Macedon"),
                        new ComponentDto(2.0, 2014, "Malbec", "Port Phillip"),
                        new ComponentDto(10.0, 2014, "Pinot Noir", "Mornington"),
                        new ComponentDto(10.0, 2015, "Pinot Noir", "Mornington"),
                        new ComponentDto(5.0, 2013, "Cabernet", "Mornington")
                )
        );
        tempMap.put(wine12YVCHAR001.lotCode(), wine11YVCHAR001);

        store = Collections.unmodifiableMap(tempMap);
    }

    @Override
    public Optional<WineDto> getWineByLotCode(String lotCode) {
        return Optional.ofNullable(store.get(lotCode));

    }
}
