package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.util.LoopTimer;

@Disabled
@Config
@TeleOp
public class Example extends CommandOpMode {
    GamepadEx driver1, driver2;
    DriveSubsystem drive;
    LoopTimer timer;

    public static double driveMult = 1;
    public static double turnMult = 1;

    @Override
    public void initialize() {
        // Add FTCDashboard telemetry to telemetry
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        // Extend default methods on gamepads
        driver1 = new GamepadEx(gamepad1);
        driver2 = new GamepadEx(gamepad2);

        // Loop Timer Module
        timer = new LoopTimer(telemetry, "Main");

        // Initialize Subsystems
        drive = new DriveSubsystem(Constants.create(hardwareMap), Pose.zero(), telemetry);

        DriveCommand driveCommand = new DriveCommand(drive,
                () -> -driver1.getLeftX() * driveMult,
                () -> driver1.getLeftY() * driveMult,
                () -> -driver1.getRightX() * turnMult,
                true);

        new GamepadButton(driver1, GamepadKeys.Button.A)
                .whenReleased(new InstantCommand(() -> drive.follower.setPose(Pose.zero())));

        schedule(driveCommand);
    }
}
