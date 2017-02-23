package com.marketdata;

import com.marketdata.service.MarketDataDaoImpl;
import com.marketdata.service.MarketDataService;
import com.marketdata.service.MarketDataServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created by sumanthdommaraju on 2/20/17.
 */
@Configuration
public class JavaConfiguration {

    @Value("${api.key}")
    private String key;
    @Value("${api.url}")
    private String url;

    @Bean
    public MarketDataService marketDataService(MarketDataDaoImpl marketDataDao) {
        MarketDataService marketDataService = new MarketDataServiceImpl(LocalDate.now().minus(200, ChronoUnit.DAYS),
                                        LocalDate.now(), marketDataDao, key, url);
        return marketDataService;
    }


}
