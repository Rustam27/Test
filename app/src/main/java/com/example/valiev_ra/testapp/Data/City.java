package com.example.valiev_ra.testapp.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class City extends Cities {
    @SerializedName("stations")
    private List<Stations> stations;

    public List<Stations> getStations() {
        return stations;
    }
}
