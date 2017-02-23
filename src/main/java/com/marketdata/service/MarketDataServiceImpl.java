package com.marketdata.service;

import com.marketdata.domain.Datatable;
import com.marketdata.domain.MarketData;
import com.marketdata.domain.Stock;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by sumanthdommaraju on 2/15/17.
 */

public class MarketDataServiceImpl implements MarketDataService{

    private final String key;
    private final String url;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final MarketDataDao marketDataDao;
    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public MarketDataServiceImpl(LocalDate fromDate, LocalDate toDate, MarketDataDao marketDataDao, String key, String url) {
        this.fromDate = fromDate;//new Date(fromDate.getTime());
        this.toDate = toDate;//new Date(toDate.getTime());
        this.marketDataDao = marketDataDao;
        this.key = key;
        this.url = url;
    }

    public void execute() throws InterruptedException {
        final RestTemplate restTemplate = new RestTemplate();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Callable<Void>> callables = createCallables(restTemplate);
        List<Future<Void>> futures = executorService.invokeAll(callables);
//        int i = 1;
//        for(Future<Void> f: futures) {
//            while (!f.isDone()) {
//                System.out.println("waiting for "+i+" to complete");
//            }
//            System.out.println((i++)+" done");
//        }
    }

    private List<Callable<Void>> createCallables(RestTemplate restTemplate) {
        List<LocalDate> dates = getDates();
        List<Callable<Void>> callables = dates.stream().map(date -> new Callable<Void>() {
            @Override
            public Void call() {
                String link = url+"date="+date.toString().replace("-","")+"&api_key="+key;
                System.out.println(link);   //TODO put logging
                MarketData marketData = restTemplate.getForObject(link , MarketData.class);
                Datatable datatable = marketData.getDatatable();
                datatable.getData().stream().map(obj -> convertToStock(obj)).filter(optionalStock -> optionalStock.isPresent())
                        .forEach(optionalStock -> marketDataDao.save(optionalStock.get()));
                return null;
            }
        }).collect(Collectors.toList());
        return callables;
    }

    private Optional<Stock> convertToStock(List<Object> objs)  {  //TODO use optional
        Stock stock = null;
        try {
            stock = new Stock();
            stock.setTicker((String) objs.get(0));
            stock.setDate(formatter.get().parse((String) objs.get(1)));
            stock.setOpen((Double) objs.get(2));
            stock.setHigh((Double) objs.get(3));
            stock.setLow((Double) objs.get(4));
            stock.setClose((Double) objs.get(5));
            stock.setVolume((Double) objs.get(6));
            stock.setExDividend((Double) objs.get(7));
            stock.setSplitRatio((Double) objs.get(8));
            stock.setAdjustedOpen((Double) objs.get(9));
            stock.setAdjustedHigh((Double) objs.get(10));
            stock.setAdjustedLow((Double) objs.get(11));
            stock.setAdjustedClose((Double) objs.get(12));
            stock.setAdjustedVolume((Double) objs.get(13));
        } catch (Exception e) {
            //TODO log
            System.out.println("error:"+objs);
        }
        return Optional.of(stock);
    }
    //"A","2016-09-12",44.59,45.81,44.47,45.75,1824161.0,0.0,1.0,44.353092610845,45.566610731168,44.23373017278,45.506929512136,1824161.0

    private List<LocalDate> getDates() {
        List<LocalDate> dates = new ArrayList<>();  //TODO Set initial capacity to `days`.
        LocalDate tempDate = fromDate;
        dates.add(fromDate);
        while(tempDate.isBefore(toDate)) {
            tempDate = tempDate.plusDays(1);
            dates.add(tempDate);
        }
        return dates;
    }
}
