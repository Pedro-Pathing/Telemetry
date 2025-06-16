package com.pedropathing.telemetry;

import java.util.ArrayList;

public class Selector {
    private ArrayList<Selectable> selectables;

    /**
     * Constructor for Selector
     */
    public Selector() {
        this.selectables = new ArrayList<Selectable>();
    }

    public ArrayList<Selectable> getSelectables() {
        return selectables;
    }

    public Selector addSelectable(Selectable selectable) {
        if (selectable != null) {
            selectables.add(selectable);
        }
        return this;
    }

    public boolean hasSelectables() {
        return !selectables.isEmpty();
    }
}
