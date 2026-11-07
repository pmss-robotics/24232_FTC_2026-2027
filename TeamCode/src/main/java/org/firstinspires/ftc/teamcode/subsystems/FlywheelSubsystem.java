package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;

@Config
public class FlywheelSubsystem extends SubsystemBase {
    public static double kS = 0.0002, kV = 0.00043, kP = 0, kI = 0, kD = 0;
    public static double setpoint, onvalue = 0;

    private boolean initialized = false;
    private MotorGroup flywheels;
    private Telemetry telemetry;
    public FlywheelSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        Robot.getInstance().initSimple(hardwareMap, telemetry);
        MotorEx flywheel1 = new MotorEx(hardwareMap, "flywheel1", Motor.GoBILDA.BARE);
        MotorEx flywheel2 = new MotorEx(hardwareMap, "flywheel2", Motor.GoBILDA.BARE);

        flywheels = new MotorGroup(flywheel1, flywheel2);

        flywheels.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        flywheels.setInverted(true);
        flywheels.setRunMode(Motor.RunMode.VelocityControl);
        flywheels.setFeedforwardCoefficients(kS, kV);
        flywheels.setVeloCoefficients(kP, kI, kD);
        initialized = true;
    }

    public void setVelocity(double velocity) {
        flywheels.set(velocity);
        setpoint = velocity;
    }

    public void stop() {
        setVelocity(0);
    }

    public void start() {
        setVelocity(onvalue);
    }
    public void init() {

    }

    public void periodic() {
        if (initialized) {
            flywheels.setFeedforwardCoefficients(kS, kV);
            flywheels.setVeloCoefficients(kP, kI, kD);
            flywheels.set(setpoint);

            telemetry.addData("Setpoint (tick/s)", setpoint);
            telemetry.addData("Velocity (tick/s)", flywheels.getVelocity());
            telemetry.addData("Raw Power (V)", Robot.getInstance().getVoltage() * flywheels.getRawPower());
        }
    }
}
