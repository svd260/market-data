package com.marketdata.domain;

import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sumanthdommaraju on 2/14/17.
 */
@Table(value = "stock")
public class Stock implements Serializable{

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private String ticker;
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Date date;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double volume;
    private Double exDividend;
    private Double splitRatio;
    private Double adjustedOpen;
    private Double adjustedHigh;
    private Double adjustedLow;
    private Double adjustedClose;
    private Double adjustedVolume;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getExDividend() {
        return exDividend;
    }

    public void setExDividend(Double exDividend) {
        this.exDividend = exDividend;
    }

    public Double getSplitRatio() {
        return splitRatio;
    }

    public void setSplitRatio(Double splitRatio) {
        this.splitRatio = splitRatio;
    }

    public Double getAdjustedOpen() {
        return adjustedOpen;
    }

    public void setAdjustedOpen(Double adjustedOpen) {
        this.adjustedOpen = adjustedOpen;
    }

    public Double getAdjustedHigh() {
        return adjustedHigh;
    }

    public void setAdjustedHigh(Double adjustedHigh) {
        this.adjustedHigh = adjustedHigh;
    }

    public Double getAdjustedLow() {
        return adjustedLow;
    }

    public void setAdjustedLow(Double adjustedLow) {
        this.adjustedLow = adjustedLow;
    }

    public Double getAdjustedClose() {
        return adjustedClose;
    }

    public void setAdjustedClose(Double adjustedClose) {
        this.adjustedClose = adjustedClose;
    }

    public Double getAdjustedVolume() {
        return adjustedVolume;
    }

    public void setAdjustedVolume(Double adjustedVolume) {
        this.adjustedVolume = adjustedVolume;
    }


}
