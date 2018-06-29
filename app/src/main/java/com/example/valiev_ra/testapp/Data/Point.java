package com.example.valiev_ra.testapp.Data;

import com.google.gson.annotations.SerializedName;

class Point {
    @SerializedName("longitude")
    private double longitude;

    @SerializedName("latitude")
    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
