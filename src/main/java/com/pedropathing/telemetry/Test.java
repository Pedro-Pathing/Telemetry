package com.pedropathing.telemetry;

public class Test {
    public static void main(String[] args) {
        Selector selector = new Selector()
                .addSelectable(
                        new Folder("Localization")
                                .addChild(
                                        new Folder("English")
                                                .addChild(new Selectable("en-US"))
                                                .addChild(new Selectable("en-GB"))
                                )
                                .addChild(
                                        new Folder("Spanish")
                                                .addChild(new Selectable("es-ES"))
                                                .addChild(new Selectable("es-MX"))
                                )
                                .addChild(
                                        new Folder("Spanish")
                                                .addChild(new Selectable("es-ES"))
                                                .addChild(new Selectable("es-MX"))
                                )
                );

        System.out.println(selector.toString());
    }
}
