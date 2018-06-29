package com.example.valiev_ra.testapp.Main.Fragment.Stations.Adapter;

public class ItemSelection implements ItemList{
    private String name;

    public ItemSelection(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean selection() {
        return true;
    }
}
