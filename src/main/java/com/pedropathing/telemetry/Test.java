package com.pedropathing.telemetry;

public class Test {
    public static void main(String[] args) {
        Selector selector = new Selector()
                .addSelectable(
                        new Folder("Location")
                                .addChild(
                                        new Folder("North America")
                                                .addChild(new Selectable("America"))
                                                .addChild(new Selectable("Mexico"))
                                                .addChild(new Selectable("Canada"))
                                )
                                .addChild(
                                        new Folder("Europe")
                                                .addChild(new Selectable("Germany"))
                                                .addChild(new Selectable("France"))
                                                .addChild(new Selectable("Spain"))
                                )
                                .addChild(
                                        new Folder("Asia")
                                                .addChild(new Selectable("China"))
                                                .addChild(new Selectable("Japan"))
                                                .addChild(new Selectable("India"))
                                )
                                .addChild(
                                        new Folder("Oceania")
                                                .addChild(new Selectable("Australia"))
                                                .addChild(new Selectable("New Zealand"))
                                )
                                .addChild(
                                        new Folder("Africa")
                                                .addChild(new Selectable("South Africa"))
                                                .addChild(new Selectable("Nigeria"))
                                                .addChild(new Selectable("Kenya"))
                                )
                                .addChild(
                                        new Folder("South America")
                                                .addChild(new Selectable("Brazil"))
                                                .addChild(new Selectable("Argentina"))
                                                .addChild(new Selectable("Chile"))
                                )
                )
                .addSelectable(
                        new Folder("Device")
                                .addChild(new Selectable("Phone"))
                                .addChild(new Selectable("Tablet"))
                                .addChild(new Selectable("Laptop"))
                                .addChild(new Selectable("Desktop"))
                );
    }
}
