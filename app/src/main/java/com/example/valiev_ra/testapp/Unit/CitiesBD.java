package com.example.valiev_ra.testapp.Unit;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.valiev_ra.testapp.Data.CitiesFrom;
import com.example.valiev_ra.testapp.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class CitiesBD {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private CitiesFrom citiesFrom;

    CitiesBD(Context context) {
        String myJson = inputStreamToString(context.getResources().openRawResource(R.raw.all_stations));
        citiesFrom = new Gson().fromJson(myJson, CitiesFrom.class);
    }

    public static CitiesBD getInstance(Context c) {
        context = c;

        return CitiesBD.CitiesBDHolder.HOLDER_INSTANCE;
    }

    private String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            return new String(bytes);
        } catch (IOException e) {
            return null;
        }
    }

    public CitiesFrom getCitiesFrom() {
        return citiesFrom;
    }

    public static class CitiesBDHolder {
        @SuppressLint("StaticFieldLeak")
        static final CitiesBD HOLDER_INSTANCE = new CitiesBD(context);
    }
}
