package com.salesa.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyRate {

    @JsonProperty("ccy")
    private String currency;
    @JsonProperty("base_ccy")
    private String baseCurrency;
    private double buy;
    private double sale;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "currency='" + currency + '\'' +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", buy=" + buy +
                ", sale=" + sale +
                '}';
    }
}
