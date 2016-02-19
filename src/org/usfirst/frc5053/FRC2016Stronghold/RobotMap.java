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
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static ADIS16448_IMU IMU = new ADIS16448_IMU();
	public static ADXRS450_Gyro SpiGyro = new ADXRS450_Gyro();
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Encoder driveTrainLeftWheelEncoder;
    public static Encoder driveTrainRightWheelEncoder;
    public static SpeedController driveTrainMotorsLeft;
    public static SpeedController driveTrainMotorsRight;
    public static RobotDrive driveTrainRobotDrive21;
    public static AnalogGyro navigationoldAnalogGyro;
    public static Encoder leftShooterLeftShooterWheelEncoder;
    public static SpeedController leftShooterLeftShooterMotor;
    public static Encoder rightShooterRightShooterWheelEncoder;
    public static SpeedController rightShooterRightShooterMotor;
    public static SpeedController intakeIntakeMotor;
    public static SpeedController armArmMotor;
    public static Solenoid scalingExtendSolenoidExtendUp;
    public static Solenoid scalingChinUpSolenoidChinUp;
    public static Solenoid shooterKickSolenoidKicker;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Gyro gyroToUse;

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainLeftWheelEncoder = new Encoder(0, 1, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "LeftWheelEncoder", driveTrainLeftWheelEncoder);
        driveTrainLeftWheelEncoder.setDistancePerPulse(0.0347195455);
        driveTrainLeftWheelEncoder.setPIDSourceType(PIDSourceType.kRate);
        driveTrainRightWheelEncoder = new Encoder(2, 3, true, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "RightWheelEncoder", driveTrainRightWheelEncoder);
        driveTrainRightWheelEncoder.setDistancePerPulse(0.0347195455);
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
        navigationoldAnalogGyro = new AnalogGyro(0);
        LiveWindow.addSensor("Navigation", "oldAnalogGyro", navigationoldAnalogGyro);
        navigationoldAnalogGyro.setSensitivity(0.007);
        leftShooterLeftShooterWheelEncoder = new Encoder(4, 5, false, EncodingType.k4X);
        LiveWindow.addSensor("LeftShooter", "LeftShooterWheelEncoder", leftShooterLeftShooterWheelEncoder);
        leftShooterLeftShooterWheelEncoder.setDistancePerPulse(1.0);
        leftShooterLeftShooterWheelEncoder.setPIDSourceType(PIDSourceType.kRate);
        leftShooterLeftShooterMotor = new Talon(2);
        LiveWindow.addActuator("LeftShooter", "LeftShooterMotor", (Talon) leftShooterLeftShooterMotor);
        
        rightShooterRightShooterWheelEncoder = new Encoder(6, 7, false, EncodingType.k4X);
        LiveWindow.addSensor("RightShooter", "RightShooterWheelEncoder", rightShooterRightShooterWheelEncoder);
        rightShooterRightShooterWheelEncoder.setDistancePerPulse(1.0);
        rightShooterRightShooterWheelEncoder.setPIDSourceType(PIDSourceType.kRate);
        rightShooterRightShooterMotor = new Talon(3);
        LiveWindow.addActuator("RightShooter", "RightShooterMotor", (Talon) rightShooterRightShooterMotor);
        
        intakeIntakeMotor = new Talon(4);
        LiveWindow.addActuator("Intake", "IntakeMotor", (Talon) intakeIntakeMotor);
        
        armArmMotor = new Talon(5);
        LiveWindow.addActuator("Arm", "ArmMotor", (Talon) armArmMotor);
        
        scalingExtendSolenoidExtendUp = new Solenoid(0, 0);
        LiveWindow.addActuator("ScalingExtend", "SolenoidExtendUp", scalingExtendSolenoidExtendUp);
        
        scalingChinUpSolenoidChinUp = new Solenoid(0, 1);
        LiveWindow.addActuator("ScalingChinUp", "SolenoidChinUp", scalingChinUpSolenoidChinUp);
        
        shooterKickSolenoidKicker = new Solenoid(0, 2);
        LiveWindow.addActuator("ShooterKick", "SolenoidKicker", shooterKickSolenoidKicker);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
     //   gyroToUse = (Gyro) IMU;
     //   gyroToUse = navigationoldAnalogGyro;
        gyroToUse = SpiGyro;
    }
}
