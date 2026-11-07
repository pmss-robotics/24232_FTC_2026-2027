package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.math.Pose;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManagerImpl;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.ScheduleCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.StartEndCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;

import org.firstinspires.ftc.robotcore.external.State;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.FlywheelSubsystem;
import org.firstinspires.ftc.teamcode.util.StateTransfer;
import org.firstinspires.ftc.teamcode.util.States;

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
    public FlywheelSubsystem flywheel;

    public void init(HardwareMap hardwareMap, Telemetry telemetry, States.RobotRunMode mode) {

        setBulkReading(hardwareMap, LynxModule.BulkCachingMode.MANUAL);
        voltageSensor = hardwareMap.voltageSensor.iterator().next();


        // Create Subsystems

        if (StateTransfer.lastRunMode.poseSaved() && !mode.reset()) {
            drive = new DriveSubsystem(Constants.create(hardwareMap), StateTransfer.pose, telemetry);
        } else {
            drive = new DriveSubsystem(Constants.create(hardwareMap), telemetry);
        }
        flywheel = new FlywheelSubsystem(hardwareMap, telemetry);

        // Initialize the powered subsystems.
        if (mode.initMovement()) {
            drive.init();
            flywheel.init();
        } else {
            // Cannot Move in TeleOp transition period.
            schedule(new SequentialCommandGroup(
            ));
        }
    }

    public void initSimple(HardwareMap hardwareMap, Telemetry telemetry) {
        setBulkReading(hardwareMap, LynxModule.BulkCachingMode.MANUAL);
        voltageSensor = hardwareMap.voltageSensor.iterator().next();
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
