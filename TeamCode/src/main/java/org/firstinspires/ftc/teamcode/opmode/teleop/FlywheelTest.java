package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.subsystems.FlywheelSubsystem;
import org.firstinspires.ftc.teamcode.util.LoopTimer;


@Config
@TeleOp
public class FlywheelTest extends CommandOpMode {
    GamepadEx driver1, driver2;
    FlywheelSubsystem flywheel;
    LoopTimer timer;

    @Override
    public void initialize() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        driver1 = new GamepadEx(gamepad1);
        driver2 = new GamepadEx(gamepad2);

        timer = new LoopTimer(telemetry, "Main");
        flywheel = new FlywheelSubsystem(hardwareMap, telemetry);


        new GamepadButton(driver1, GamepadKeys.Button.A)
                .whenPressed(flywheel::stop);

        new GamepadButton(driver2, GamepadKeys.Button.B)
                .whenPressed(flywheel::start);

    }

}
