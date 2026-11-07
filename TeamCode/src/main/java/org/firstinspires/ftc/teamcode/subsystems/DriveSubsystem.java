package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class DriveSubsystem extends SubsystemBase {
    public Follower follower;
    private Telemetry telemetry;
    public DriveSubsystem(Follower follower, Pose startPose, Telemetry telemetry) {
        this.follower = follower;
        this.telemetry = telemetry;
        follower.setPose(startPose);
    }

    public DriveSubsystem(Follower follower, Telemetry telemetry) {
        this.follower = follower;
        this.telemetry = telemetry;
        follower.setPose(Pose.zero());
    }

    public void init() {
        //
    }

    @Override
    public void periodic() {
        follower.update();

        Pose pose = getPose();
        telemetry.addData("x", pose.x());
        telemetry.addData("y",pose.y());
        telemetry.addData("heading (deg)", Math.toDegrees(pose.heading()));
    }


    public Pose getPose() {
        return follower.pose();
    }
}
