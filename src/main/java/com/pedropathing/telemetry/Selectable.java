package com.pedropathing.telemetry;

public class Selectable {
    private String name;
    private Folder parent;

    /** Constructor for Selectable */
    public Selectable(String name, Folder parent) {
        this.name = name;
        this.parent = parent;
    }

    /** Constructor for Selectable in root folder */
    public Selectable(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }

    public Folder getParent() {
        if (parent == null) {
            throw new IllegalStateException("This Selectable does not have a parent folder.");
        }
        return parent;
    }

    public void setParent(Folder parent) {
        if (this.parent != null) {
            throw new IllegalStateException("This Selectable already has a parent folder.");
        }
        this.parent = parent;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        String prefix = ">";
        if (parent == null)
            prefix = "-";
        return prefix + " " + name;
    }
}
