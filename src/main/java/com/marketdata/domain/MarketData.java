package com.marketdata.domain;

/**
 * Created by sumanthdommaraju on 2/14/17.
 */
public class MarketData {
    private Datatable datatable;
    private Meta meta;


    public Datatable getDatatable() {
        return datatable;
    }

    public void setDatatable(Datatable datatable) {
        this.datatable = datatable;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
