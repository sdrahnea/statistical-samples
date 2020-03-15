package com.udc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;

public class Covid19 {

    @JsonProperty("date")
    private String date;

    @JsonProperty("countries")
    private List<Covid19Country> countries = new LinkedList<>();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Covid19Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Covid19Country> countries) {
        this.countries = countries;
    }
}
