package org.firstinspires.ftc.teamcode.commands;


import com.pedropathing.controllers.Controller;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.ManualDrive;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.util.StateTransfer;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {
    private final DriveSubsystem drive;
    private final DoubleSupplier lx, ly, rx;
    public static Vector2d inputDirection;
    public static boolean lockHeading = false;
    public static double targetHeading;
    public static Controller headingController;
    private final boolean isFieldCentric;

    public DriveCommand(DriveSubsystem drive, DoubleSupplier lx, DoubleSupplier ly, DoubleSupplier rx, boolean isFieldCentric) {
        this.drive = drive;
        this.lx = lx;
        this.ly = ly;
        this.rx = rx;
        this.isFieldCentric = isFieldCentric;
        inputDirection = StateTransfer.alliance.inputDirection();
        headingController = Controller.zero;
    }

    @Override
    public void initialize() {
        lockHeading = false;
        drive.follower.manual(DrivePowers.zero());
    }
    @Override
    public void execute() {
        DrivePowers powers;
        if (isFieldCentric) {
            powers = ManualDrive.fieldCentric(
                    ly.getAsDouble() * inputDirection.getX(),
                    lx.getAsDouble() * inputDirection.getY(),
                    rx.getAsDouble(),
                    drive.follower.pose().heading()
            );
        } else {
            powers = new DrivePowers(
                    ly.getAsDouble() * inputDirection.getX(),
                    lx.getAsDouble() * inputDirection.getY(),
                    rx.getAsDouble()
            );
        }
        if (lockHeading) {
            drive.follower.manual(
                    ManualDrive.headingLock(
                            drive.follower,
                            headingController,
                            powers,
                            targetHeading
                    )
            );
        } else {
            drive.follower.manual(powers);
        }
        drive.follower.update();
    }
}
