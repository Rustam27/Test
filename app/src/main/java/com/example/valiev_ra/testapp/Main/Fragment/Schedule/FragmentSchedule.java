package com.example.valiev_ra.testapp.Main.Fragment.Schedule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.valiev_ra.testapp.Main.Fragment.Stations.FragmentStations;
import com.example.valiev_ra.testapp.Main.Listenner.OnSelectFragment;
import com.example.valiev_ra.testapp.R;
import com.example.valiev_ra.testapp.Unit.Route;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class FragmentSchedule extends Fragment {
    private OnSelectFragment onSelectFragment;

    private Button
            btnDate,
            btnArrival,
            btnDeparture;

    public static FragmentSchedule getInstance() {
        return FragmentScheduleHolder.HOLDER_INSTANCE;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        onSelectFragment = (OnSelectFragment) context;
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
        View view = inflater.inflate(R.layout.content_schedule, null);

        btnDate = view.findViewById(R.id.btn_date);
        btnArrival = view.findViewById(R.id.btn_arrival);
        btnDeparture = view.findViewById(R.id.btn_departure);

        View.OnClickListener onClickListener = view1 -> {
            switch (view1.getId()) {
                case R.id.btn_arrival:
                    onSelectFragment.selectFragment(FragmentStations.getInstance(FragmentStations.Direction.Arrival, onSelectFragment));
                    break;
                case R.id.btn_departure:
                    onSelectFragment.selectFragment(FragmentStations.getInstance(FragmentStations.Direction.Departure, onSelectFragment));
                    break;
                case R.id.btn_date:
                    com.example.valiev_ra.testapp.Components.DatePicker.showDatePicker(getContext(), new Date(), date -> {
                        Route.getInstance().setDate(date);

                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
                        btnDate.setText(sdf.format(Route.getInstance().getDate()));
                    });
                    break;
            }
        };

        btnDate.setOnClickListener(onClickListener);
        btnArrival.setOnClickListener(onClickListener);
        btnDeparture.setOnClickListener(onClickListener);

        Route route = Route.getInstance();

        btnDate.setText(route.getDate() == null ? "Выберите дату" : route.getDate().getTime() + "");
        btnArrival.setText(route.getArrivalStation() == null ? "Выберите станцию" : route.getArrivalStation().getStationTitle());
        btnDeparture.setText(route.getDepartureStations() == null ? "Выберите станцию" : route.getDepartureStations().getStationTitle());

        return view;
    }

    public static class FragmentScheduleHolder {
        @SuppressLint("StaticFieldLeak")
        static final FragmentSchedule HOLDER_INSTANCE = new FragmentSchedule();
    }
}
