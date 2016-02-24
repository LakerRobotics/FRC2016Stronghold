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

import org.usfirst.frc5053.FRC2016Stronghold.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc5053.FRC2016Stronghold.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton driveStraight;
    public JoystickButton driveStrightPID;
    public JoystickButton shooter;
    public JoystickButton shooterOff;
    public Joystick driver;
    public JoystickButton intakeSpin;
    public JoystickButton intakeSpinOff;
    public JoystickButton intakeReverse;
    public JoystickButton intakeReverseOff;
    public JoystickButton intakeReverseFast;
    public JoystickButton intakeReverseFastOff;
    public JoystickButton scalerReach;
    public JoystickButton scalerPullUp;
    public JoystickButton kickShooter;
    public JoystickButton armNeutral;
    public JoystickButton armGetBall;
    public JoystickButton armLiftPorticullis;
    public JoystickButton armScale;
    public JoystickButton arcadeArm;
    public JoystickButton shooterIntake;
    public JoystickButton shooterIntakeOff;
    public Joystick operator;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        operator = new Joystick(1);
        
        shooterIntakeOff = new JoystickButton(operator, 10);
        shooterIntakeOff.whenReleased(new ShooterSpin(0));
        shooterIntake = new JoystickButton(operator, 10);
        shooterIntake.whileHeld(new ShooterSpin(-25));
        arcadeArm = new JoystickButton(operator, 1);
        arcadeArm.whileHeld(new ArcadeArmControl());
        armScale = new JoystickButton(operator, 9);
        armScale.whileHeld(new ArmSetpoints(0.163));
        armLiftPorticullis = new JoystickButton(operator, 8);
        armLiftPorticullis.whileHeld(new ArmSetpoints(0.289));
        armGetBall = new JoystickButton(operator, 7);
        armGetBall.whileHeld(new ArmSetpoints(0.272));
        armNeutral = new JoystickButton(operator, 6);
        armNeutral.whileHeld(new ArmSetpoints(0.163));
        kickShooter = new JoystickButton(operator, 5);
        kickShooter.whileHeld(new ShooterKicker());
        scalerPullUp = new JoystickButton(operator, 4);
        scalerPullUp.whileHeld(new ScalerChinUp());
        scalerReach = new JoystickButton(operator, 3);
        scalerReach.whileHeld(new ScalerReachUp());
        intakeReverseFastOff = new JoystickButton(operator, 4);
        intakeReverseFastOff.whenReleased(new SpinIntake(0));
        intakeReverseFast = new JoystickButton(operator, 4);
        intakeReverseFast.whileHeld(new SpinIntake(-1));
        intakeReverseOff = new JoystickButton(operator, 2);
        intakeReverseOff.whenReleased(new SpinIntake(0));
        intakeReverse = new JoystickButton(operator, 2);
        intakeReverse.whileHeld(new SpinIntake(1));
        intakeSpinOff = new JoystickButton(operator, 3);
        intakeSpinOff.whenReleased(new SpinIntake(0));
        intakeSpin = new JoystickButton(operator, 3);
        intakeSpin.whileHeld(new SpinIntake(-0.5));
        driver = new Joystick(0);
        
        shooterOff = new JoystickButton(driver, 2);
        shooterOff.whenReleased(new ShooterSpin(0));
        shooter = new JoystickButton(driver, 2);
        shooter.whileHeld(new ShooterSpin(25));
        driveStrightPID = new JoystickButton(driver, 3);
        driveStrightPID.whileHeld(new ArcadeDriveStrightPID());
        driveStraight = new JoystickButton(driver, 1);
        driveStraight.whileHeld(new ArcadeDriveStraight());


        // SmartDashboard Buttons
        SmartDashboard.putData("ArmSetpoints: Neutral", new ArmSetpoints(0.163));
        SmartDashboard.putData("ArmSetpoints: GetBall", new ArmSetpoints(0.272));
        SmartDashboard.putData("ArmSetpoints: LiftPorticullis", new ArmSetpoints(0.289));
        SmartDashboard.putData("ArmSetpoints: Scale", new ArmSetpoints(0.163));
        SmartDashboard.putData("ArcadeArmControl", new ArcadeArmControl());
        SmartDashboard.putData("Auton: LowBarLefGoal", new Auton(1, "Left", "Low"));
        SmartDashboard.putData("Auton: LowBarRightGoal", new Auton(1, "Right", "Low"));
        SmartDashboard.putData("ArcadeDrive", new ArcadeDrive());
        SmartDashboard.putData("ArcadeDriveStraight", new ArcadeDriveStraight());
        SmartDashboard.putData("ArcadeDriveStrightPID", new ArcadeDriveStrightPID());
        SmartDashboard.putData("DriveForward: default", new DriveForward(50));
        SmartDashboard.putData("DriveSpin", new DriveSpin());
        SmartDashboard.putData("AllwaysTrackLocation", new AllwaysTrackLocation());
        SmartDashboard.putData("SpinIntake: Forward", new SpinIntake(1));
        SmartDashboard.putData("SpinIntake: Reverse", new SpinIntake(-0.5));
        SmartDashboard.putData("SpinIntake: Stop", new SpinIntake(0));
        SmartDashboard.putData("SpinIntake: ReverseFast", new SpinIntake(-1));
        SmartDashboard.putData("SquareDrive", new SquareDrive());
        SmartDashboard.putData("ScalerReachUp", new ScalerReachUp());
        SmartDashboard.putData("ScalerChinUp", new ScalerChinUp());
        SmartDashboard.putData("ShooterKicker", new ShooterKicker());
        SmartDashboard.putData("KickBall", new KickBall());
        SmartDashboard.putData("ShooterSpin: Default", new ShooterSpin(25));
        SmartDashboard.putData("ShooterSpin: Off", new ShooterSpin(0));
        SmartDashboard.putData("ShooterSpin: Intake", new ShooterSpin(-25));
        SmartDashboard.putData("CrossRoughTerrain", new CrossRoughTerrain());
        SmartDashboard.putData("CrossRockWall", new CrossRockWall());
        SmartDashboard.putData("CrossRamparts", new CrossRamparts());
        SmartDashboard.putData("CrossMoat", new CrossMoat());
        SmartDashboard.putData("CrossLowBar", new CrossLowBar());
        SmartDashboard.putData("CrossDrawbridge", new CrossDrawbridge());
        SmartDashboard.putData("CrossPorticullis", new CrossPorticullis());
        SmartDashboard.putData("CrossChevalDeFrise", new CrossChevalDeFrise());
        SmartDashboard.putData("LowBarAuton", new LowBarAuton());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriver() {
        return driver;
    }

    public Joystick getOperator() {
        return operator;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

