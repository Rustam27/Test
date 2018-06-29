package com.example.valiev_ra.testapp.Main.Fragment.Stations;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.valiev_ra.testapp.Components.RecyclerItemClickListener;
import com.example.valiev_ra.testapp.Data.City;
import com.example.valiev_ra.testapp.Data.Stations;
import com.example.valiev_ra.testapp.Main.Fragment.Schedule.FragmentSchedule;
import com.example.valiev_ra.testapp.Main.Fragment.Stations.Adapter.Adapter;
import com.example.valiev_ra.testapp.Main.Fragment.Stations.Adapter.ItemList;
import com.example.valiev_ra.testapp.Main.Fragment.Stations.Adapter.ItemSelection;
import com.example.valiev_ra.testapp.Main.Listenner.OnSelectFragment;
import com.example.valiev_ra.testapp.R;
import com.example.valiev_ra.testapp.Unit.CitiesBD;
import com.example.valiev_ra.testapp.Unit.Route;

import java.util.ArrayList;
import java.util.List;


public class FragmentStations extends Fragment {
    private static OnSelectFragment onSelectFragment;
    private static Direction direction;

    private Adapter adapter;


    public static FragmentStations getInstance(Direction dir,  OnSelectFragment selectFragment) {
        onSelectFragment = selectFragment;
        direction = dir;
        return FragmentScheduleHolder.HOLDER_INSTANCE;
    }

    public static FragmentStations getInstance() {
        return FragmentScheduleHolder.HOLDER_INSTANCE;
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
        View view = inflater.inflate(R.layout.content_stations, null);

        RecyclerView rvList = view.findViewById(R.id.rv_list);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(llm);

        adapter = new Adapter(getContext(), getItems(), onSelectFragment);
        rvList.setAdapter(adapter);

        rvList.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (adapter.getFilterItems().get(position).selection())
                    return;

                Stations stations = (Stations) adapter.getFilterItems().get(position);

                switch (direction) {
                    case Arrival:
                        Route.getInstance().setArrivalStation(stations);
                        break;
                    case Departure:
                        Route.getInstance().setDepartureStations(stations);
                        break;
                }
                onSelectFragment.selectFragment(FragmentSchedule.getInstance());
            }
        }));

        return view;
    }

    private List<ItemList> getItems() {
        List<ItemList> itemLists = new ArrayList<>();

        List<City> cities = CitiesBD.getInstance(getContext()).getCitiesFrom().getCitiesFrom();

        for (City city : cities) {
            StringBuilder name = new StringBuilder();
            name.append(city.getCountryTitle());
            name.append(", ");
            name.append(city.getCityTitle());

            itemLists.add(new ItemSelection(name.toString()));
            itemLists.addAll(city.getStations());
        }

        return itemLists;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_stations, menu);
        super.onCreateOptionsMenu(menu, inflater);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView  searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s))
                    adapter.getFilter().filter(s);
                return false;
            }
        });
    }

    public enum Direction {
        Departure,
        Arrival
    }

    public static class FragmentScheduleHolder {
        @SuppressLint("StaticFieldLeak")
        static final FragmentStations HOLDER_INSTANCE = new FragmentStations();
    }
}
