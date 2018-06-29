package com.example.valiev_ra.testapp.Data;

import com.google.gson.annotations.SerializedName;

public abstract class Cities {
    @SerializedName("countryTitle")
    private String countryTitle;

    @SerializedName("point")
    private Point point;

    @SerializedName("districtTitle")
    private String districtTitle;

    @SerializedName("cityId")
    private int cityId;

    @SerializedName("cityTitle")
    private String cityTitle;

    @SerializedName("regionTitle")
    private String regionTitle;

    public String getCountryTitle() {
        return countryTitle;
    }

    public Point getPoint() {
        return point;
    }

    public String getDistrictTitle() {
        return districtTitle;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public String getRegionTitle() {
        return regionTitle;
    }
}
