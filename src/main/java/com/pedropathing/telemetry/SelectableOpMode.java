package com.pedropathing.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class SelectableOpMode extends OpMode {
    private final Selector<Supplier<OpMode>> selector;
    private OpMode selectedOpMode;
    private final static String[] MESSAGE = {
            "Use the d-pad to move the cursor.",
            "Press right bumper to select.",
            "Press left bumper to go back."
    };

    public SelectableOpMode(String name, Consumer<SelectScope<Supplier<OpMode>>> opModes) {
        selector = Selector.create(name, opModes, MESSAGE);
        selector.onSelect(opModeSupplier -> {
            selectedOpMode = opModeSupplier.get();
            selectedOpMode.init();
        });
    }

    @Override
    public void init() {
    }

    @Override
    public final void init_loop() {
        if (selectedOpMode == null) {
            if (gamepad1.dpad_up || gamepad2.dpad_up) selector.decrementSelected();
            else if (gamepad1.dpad_down || gamepad2.dpad_down) selector.incrementSelected();
            else if (gamepad1.right_bumper || gamepad2.right_bumper) selector.select();
            else if (gamepad1.left_bumper || gamepad2.left_bumper) selector.goBack();

            for (String line : selector.getLines()) {
                telemetry.addLine(line);
            }
        } else selectedOpMode.init_loop();
    }

    @Override
    public final void start() {
        if (selectedOpMode == null) throw new RuntimeException("No OpMode selected!");
        selectedOpMode.gamepad1 = gamepad1;
        selectedOpMode.gamepad2 = gamepad2;
        selectedOpMode.telemetry = telemetry;
        selectedOpMode.hardwareMap = hardwareMap;
        selectedOpMode.start();
    }

    @Override
    public final void loop() {
        selectedOpMode.loop();
    }

    @Override
    public final void stop() {
        if (selectedOpMode != null) selectedOpMode.stop();
    }
}
