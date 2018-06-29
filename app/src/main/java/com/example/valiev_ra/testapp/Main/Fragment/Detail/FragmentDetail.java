package com.example.valiev_ra.testapp.Main.Fragment.Detail;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.valiev_ra.testapp.Data.Stations;
import com.example.valiev_ra.testapp.R;

public class FragmentDetail extends Fragment {
    private static Stations stations;

    public static FragmentDetail getInstance(Stations s) {
        stations = s;
        return FragmentDetail.FragmentAboutHolder.HOLDER_INSTANCE;
    }

    public static FragmentDetail getInstance() {
        return FragmentDetail.FragmentAboutHolder.HOLDER_INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_detail, null);

        TextView tvRegion = view.findViewById(R.id.tv_region);
        TextView tvCity = view.findViewById(R.id.tv_city);
        TextView tvCountry = view.findViewById(R.id.tv_country);
        TextView tvStation = view.findViewById(R.id.tv_station);

        tvCity.setText("Город: " + stations.getCityTitle());
        tvRegion.setText("Регион: " + stations.getRegionTitle());
        tvCountry.setText("Страна: " + stations.getCountryTitle());
        tvStation.setText("Станция: " + stations.getStationTitle());

        return view;
    }

    public static class FragmentAboutHolder {
        @SuppressLint("StaticFieldLeak")
        static final FragmentDetail HOLDER_INSTANCE = new FragmentDetail();
    }
}