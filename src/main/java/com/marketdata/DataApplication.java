package com.marketdata;

import com.marketdata.service.MarketDataServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DataApplication {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext applicationContext = new SpringApplicationBuilder(DataApplication.class).web(false).run(args);
		MarketDataServiceImpl marketDataService = applicationContext.getBean(MarketDataServiceImpl.class);
		long start = System.currentTimeMillis();
		marketDataService.execute();
		System.out.println("took " + ((System.currentTimeMillis() - start)/1000)+ " secs");

	}
}
