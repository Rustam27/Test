package com.example.valiev_ra.testapp.Main.Fragment.Stations.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.valiev_ra.testapp.Data.Stations;
import com.example.valiev_ra.testapp.Main.Fragment.Detail.FragmentDetail;
import com.example.valiev_ra.testapp.Main.Fragment.Stations.FragmentStations;
import com.example.valiev_ra.testapp.Main.Listenner.OnSelectFragment;
import com.example.valiev_ra.testapp.R;

import java.util.ArrayList;
import java.util.List;

public final class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private Context context;

    private OnSelectFragment onSelectFragment;

    private List<ItemList>
            items,
            filterItems;

    private CityFilter cityFilter;


    public Adapter(Context context, List<ItemList> items, OnSelectFragment onSelectFragment) {
        this.context = context;
        this.onSelectFragment = onSelectFragment;
        this.items = items;
        this.filterItems = new ArrayList<>();
        this.filterItems.addAll(items);
    }

    @Override
    public int getItemCount() {
        return this.filterItems.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.item_stations, viewGroup, false);
        return new ViewHolderItem(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vhItem, int position) {
        if (filterItems.get(position).selection()) {
            ViewHolderItem viewHolderSelection = (ViewHolderItem) vhItem;
            ItemSelection selection = (ItemSelection) filterItems.get(position);

            viewHolderSelection.getName().setText(selection.getName());

            viewHolderSelection.getName().setTypeface(null, Typeface.BOLD);
            viewHolderSelection.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.color_divider));
        } else {
            ViewHolderItem viewHolderItem = (ViewHolderItem) vhItem;
            final Stations station = (Stations) filterItems.get(position);

            viewHolderItem.getName().setText(station.getStationTitle());

            viewHolderItem.getName().setTypeface(null, Typeface.NORMAL);
            viewHolderItem.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white));

            viewHolderItem.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onSelectFragment.selectFragment(FragmentDetail.getInstance(station));
                    return false;
                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        if (cityFilter == null)
            cityFilter = new CityFilter(this, items);
        return cityFilter;
    }

    private static class CityFilter extends Filter {
        private final Adapter adapter;

        private final List<ItemList> originalList;
        private List<ItemList> filteredList;

        private CityFilter(Adapter adapter, List<ItemList> originalList) {
            super();
            this.adapter = adapter;
            this.originalList = originalList;
            this.filteredList = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            FilterResults results = new FilterResults();

            String s = constraint.toString();

            if (TextUtils.isEmpty(constraint.toString()) | constraint.toString().length() == 0) {
                filteredList.addAll(originalList);
            } else {
                for (ItemList itemList : originalList) {
                    //Добавить наименование группы в список
                    if (itemList.selection()) {
                        //Удалить пункт группы, если не было станций
                        if (filteredList.size() != 0 && filteredList.get(filteredList.size() - 1).selection())
                            filteredList.remove(filteredList.size() - 1);

                        filteredList.add(itemList);
                        continue;
                    }

                    Stations stations = (Stations) itemList;

                    StringBuilder name = new StringBuilder();
                    name.append(stations.getStationTitle().toLowerCase());
                    name.append(stations.getCityTitle().toLowerCase());
                    name.append(stations.getRegionTitle().toLowerCase());

                    //Поиск совпадений среди наименования страны, города, станции
                    if (name.toString().contains(s.toLowerCase().trim()))
                        filteredList.add(stations);
                }
            }
            //Удалить пункт группы, если не было станций
            if (filteredList.get(filteredList.size() - 1).selection())
                filteredList.remove(filteredList.size() - 1);

            results.values = filteredList;
            results.count = filteredList.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.filterItems.clear();
            adapter.filterItems.addAll((List<ItemList>) results.values);
            adapter.notifyDataSetChanged();
        }
    }

    public List<ItemList> getFilterItems() {
        return filterItems;
    }
}