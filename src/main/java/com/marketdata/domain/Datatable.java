package com.marketdata.domain;

import java.util.List;

/**
 * Created by sumanthdommaraju on 2/14/17.
 */
public class Datatable {

    private List<List<Object>>  data;
    private List<Column> columns;

    public List<List<Object>> getData() {
        return data;
    }

    public void setData(List<List<Object>> data) {
        this.data = data;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
