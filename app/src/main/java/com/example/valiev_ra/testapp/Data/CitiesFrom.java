package com.example.valiev_ra.testapp.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CitiesFrom {
    @SerializedName("citiesFrom")
    private List<City> citiesFrom;

    public List<City> getCitiesFrom() {
        return citiesFrom;
    }
}
