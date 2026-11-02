package org.firstinspires.ftc.teamcode.util;


import static org.firstinspires.ftc.teamcode.util.CalcUtil.clampMagnitude;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Robot;

// See article
// https://medium.com/@vikramaditya.nishant/introducing-torque-current-control-for-ftc-3db75d281258
public class TorqueController {

    public static final double R = 12.0/9.2;
    public static final double W_NL = 5800.0 * 2.0 * Math.PI / 60;
    public static final double K_E = (12.0 - 2.5 * R) / W_NL;
    public static final double TICKS_PER_REV = 28.0;

    private DcMotorEx motor;
    private double targetCurrent; // Current in Amps
    private double maxCurrent; // Current in Amps

    public TorqueController(DcMotorEx motor, double currentMax, double target) {
        this.motor = motor;

        this.maxCurrent = currentMax;
        this.targetCurrent = target;
    }

    /// Provides the target current to motor using torque-current control, encoder control and
    /// @param target Current in Amps
    public void run(double target) {
        double power = (2.0 * Math.PI / TICKS_PER_REV) * motor.getVelocity() + R * clampMagnitude(target, maxCurrent);

        motor.setPower(power / Robot.getInstance().getVoltage());
    }
}
