package com.marketdata.service;

import com.datastax.driver.core.Session;
import com.marketdata.domain.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by sumanthdommaraju on 2/15/17.
 */
@Repository
public class MarketDataDaoImpl implements MarketDataDao{

    private final Session session;

    @Autowired
    public MarketDataDaoImpl(Session session) {
        this.session = session;
    }

    public void save(Stock stock) {
        CassandraOperations cassandraOps = new CassandraTemplate(session);
        Object obj = cassandraOps.insert(stock);
    }
}
