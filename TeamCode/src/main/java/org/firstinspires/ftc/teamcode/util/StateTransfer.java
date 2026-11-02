package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.math.Pose;

@Config
public class StateTransfer {
    public static States.Alliance alliance = States.Alliance.Unset;
    public static States.RobotRunMode lastRunMode = States.RobotRunMode.Utility;
    public static Pose pose;

    public static Pose autoDefault = Pose.zero();
    public static Pose teleOpDefault = Pose.zero();
    public static Pose utilityDefault = Pose.zero();

    public static Pose getPose(States.RobotRunMode runMode) {
        if (StateTransfer.lastRunMode.poseSaved() && !StateTransfer.lastRunMode.reset()) {
            return StateTransfer.pose;
        } else {
            switch (runMode) {
                case Auto: return autoDefault;
                case TeleOp: return teleOpDefault;
                case Utility: return utilityDefault;
                default: return new Pose(Double.NaN,Double.NaN,Double.NaN); // Should never occur
            }
        }
    }
}
