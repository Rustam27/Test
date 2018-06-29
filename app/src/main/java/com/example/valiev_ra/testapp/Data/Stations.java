package com.example.valiev_ra.testapp.Data;

import com.example.valiev_ra.testapp.Main.Fragment.Stations.Adapter.ItemList;
import com.google.gson.annotations.SerializedName;

public class Stations extends Cities implements ItemList{
    @SerializedName("stationId")
    private int stationId;

    @SerializedName("stationTitle")
    private String stationTitle;

    public int getStationId() {
        return stationId;
    }

    public String getStationTitle() {
        return stationTitle;
    }

    @Override
    public boolean selection() {
        return false;
    }
}
