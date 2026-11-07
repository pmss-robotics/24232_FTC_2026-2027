package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.math.Pose;

@Config
public class StateTransfer {
    public static States.Alliance alliance = States.Alliance.Unset;
    public static States.RobotRunMode lastRunMode = States.RobotRunMode.Utility;
    public static Pose pose;
}
