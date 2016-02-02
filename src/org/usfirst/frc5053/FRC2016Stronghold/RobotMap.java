// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc5053.FRC2016Stronghold;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Encoder driveTrainLeftWheelEncoder;
    public static Encoder driveTrainRightWheelEncoder;
    public static SpeedController driveTrainMotorsLeft;
    public static SpeedController driveTrainMotorsRight;
    public static RobotDrive driveTrainRobotDrive21;
    public static AnalogGyro navigationAnalogGyro;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainLeftWheelEncoder = new Encoder(0, 1, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "LeftWheelEncoder", driveTrainLeftWheelEncoder);
        driveTrainLeftWheelEncoder.setDistancePerPulse(1.0);
        driveTrainLeftWheelEncoder.setPIDSourceType(PIDSourceType.kRate);
        driveTrainRightWheelEncoder = new Encoder(2, 3, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "RightWheelEncoder", driveTrainRightWheelEncoder);
        driveTrainRightWheelEncoder.setDistancePerPulse(1.0);
        driveTrainRightWheelEncoder.setPIDSourceType(PIDSourceType.kRate);
        driveTrainMotorsLeft = new Talon(0);
        LiveWindow.addActuator("DriveTrain", "Motors Left", (Talon) driveTrainMotorsLeft);
        
        driveTrainMotorsRight = new Talon(1);
        LiveWindow.addActuator("DriveTrain", "MotorsRight", (Talon) driveTrainMotorsRight);
        
        driveTrainRobotDrive21 = new RobotDrive(driveTrainMotorsLeft, driveTrainMotorsRight);
        
        driveTrainRobotDrive21.setSafetyEnabled(true);
        driveTrainRobotDrive21.setExpiration(0.1);
        driveTrainRobotDrive21.setSensitivity(0.5);
        driveTrainRobotDrive21.setMaxOutput(1.0);
        driveTrainRobotDrive21.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        driveTrainRobotDrive21.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        navigationAnalogGyro = new AnalogGyro(0);
        LiveWindow.addSensor("Navigation", "AnalogGyro", navigationAnalogGyro);
        navigationAnalogGyro.setSensitivity(0.007);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
