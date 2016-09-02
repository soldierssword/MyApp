package com.example.johnson.myapp;

/**
 * Created by Johnson on 2016/8/29.
 */
public class RetData {


    public RetData() {
    }

    public RetData(Double convertedamount, Double currency, String date, String fromCurrency, String time, String toCurrency) {
        this.convertedamount = convertedamount;
        this.currency = currency;
        this.date = date;
        this.fromCurrency = fromCurrency;
        this.time = time;
        this.toCurrency = toCurrency;
    }

    public Double getConvertedamount() {

        return convertedamount;
    }

    public void setConvertedamount(Double convertedamount) {
        this.convertedamount = convertedamount;
    }

    public Double getCurrency() {
        return currency;
    }

    public void setCurrency(Double currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getToCurrency() {
        return toCurrency;
    }
    private String fromCurrency;
    private String toCurrency;
    private String date;
    private String time;
    private Double currency;////当前汇率
    private Double convertedamount; //转化后的金额

    @Override
    public String toString() {
        return "retData[fromCurrency="+this.fromCurrency
                +",toCurrency=" +this.toCurrency
                +",date="+this.date
                +",currency="+this.currency
                +",convertedamount="+this.convertedamount
                +"]";
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }
}
