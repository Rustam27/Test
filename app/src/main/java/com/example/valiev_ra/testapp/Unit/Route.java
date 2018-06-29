package com.example.valiev_ra.testapp.Unit;

import android.annotation.SuppressLint;

import com.example.valiev_ra.testapp.Data.Stations;

import java.util.Date;

public class Route {
    private Stations arrivalStation;
    private Stations departureStations;
    private Date date;

    public static Route getInstance() {
        return Route.RouteHolder.HOLDER_INSTANCE;
    }

    public static class RouteHolder {
        @SuppressLint("StaticFieldLeak")
        static final Route HOLDER_INSTANCE = new Route();
    }

    public void setArrivalStation(Stations arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public void setDepartureStations(Stations departureStations) {
        this.departureStations = departureStations;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Stations getArrivalStation() {
        return arrivalStation;
    }

    public Stations getDepartureStations() {
        return departureStations;
    }

    public Date getDate() {
        return date;
    }
}
