package com.pedropathing.telemetry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Folder extends Selectable {
    private List<Selectable> children;

    /** Constructor for Folder with children and parent */
    public Folder(String name, Folder parent) {
        super(name, parent);
        this.children = new ArrayList<Selectable>();
    }

    /** Constructor for Folder with children located in the root folder */
    public Folder(String name) {
        this(name, null);
    }

    public List<Selectable> getChildren() {
        return children;
    }

    public Folder addChild(Selectable child) {
        if (child != null) {
            children.add(child);
            child.setParent(this);
        }
        return this;
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("\n");
        for (Selectable child : children) {
            sb.append("  ").append(child.toString()).append("\n");
        }
        return sb.toString();
    }
}
