package com.marketdata.service;

import com.marketdata.domain.Stock;

/**
 * Created by sumanthdommaraju on 2/22/17.
 */
public interface MarketDataDao {
    void save(Stock stock);
}
