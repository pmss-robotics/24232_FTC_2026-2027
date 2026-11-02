package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.math.Pose;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

@Config
public class Robot extends com.seattlesolvers.solverslib.command.Robot {
    public static double VOLTAGE_SENSOR_POLLING_RATE = 5; // Hertz


    private static final Robot instance = new Robot();

    public static Robot getInstance() {
        return instance;
    }

    private VoltageSensor voltageSensor;
    private double cachedVoltage;
    private ElapsedTime voltageTimer;
    public DriveSubsystem drive;

    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        setBulkReading(hardwareMap, LynxModule.BulkCachingMode.MANUAL);
        voltageSensor = hardwareMap.voltageSensor.iterator().next();

        drive = new DriveSubsystem(Constants.create(hardwareMap), telemetry);
    }


    public void updateLoop() {

    }
    public double getVoltage() {
        if (voltageTimer == null) {
            cachedVoltage = voltageSensor.getVoltage();
            voltageTimer = new ElapsedTime();
            voltageTimer.reset();
        } else if (voltageTimer.milliseconds() > (1.0 / VOLTAGE_SENSOR_POLLING_RATE) * 1000) {
            cachedVoltage = voltageSensor.getVoltage();
        }
        if (Double.isNaN(cachedVoltage) || cachedVoltage <= 0) {
            cachedVoltage = 12;
        }

        return cachedVoltage;
    }


}
