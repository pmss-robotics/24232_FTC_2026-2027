package org.firstinspires.ftc.teamcode.util;

public class CalcUtil {

    public static double signedClamp(double value, double min, double max) {
        return Math.signum(value) * Math.max(Math.abs(min), Math.min( Math.abs(value), Math.abs(max)));
    }

    public static double clampMagnitude(double value, double max) {
        return Math.signum(value) * Math.min(Math.abs(value), max);
    }
}
