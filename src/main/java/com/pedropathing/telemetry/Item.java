package com.pedropathing.telemetry;

import org.jetbrains.annotations.NotNull;

public final class Item<T> extends Selectable<T> {

    public final T value;

    /**
     * Constructs a {@link Item} with the given name and value.
     *
     * @param name the name of the item
     * @param value the value of the item
     */
    public Item(@NotNull String name, @NotNull T value) {
        super(name);
        this.value = value;
    }
}
