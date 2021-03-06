// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc5053.FRC2016Stronghold.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5053.FRC2016Stronghold.Robot;
import org.usfirst.frc5053.FRC2016Stronghold.RobotMap;

/**
 *
 */
public class ArcadeDriveStraight extends Command {
    double targetDirectionAngle = 0;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public ArcadeDriveStraight() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	targetDirectionAngle = RobotMap.gyroToUse.getAngle();
    	//RobotMap.navigationAnalogGyro.getAngle();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// This is just a simple P control, Proportional control of the line follow
    	// if we assume angle is in degrees and if we were off by 20 Degrees then we would want how much correction
    	// for example if Kp is 0.025 at 20 degrees we would have 0.5 or half the power toward rotating the robot 
    	double Kp = 1d/45d;//0.025;
    	double leftPower; 
    	double rightPower;
    	//1/90 works pretty good
    	//1/180 motor output per degree off so if it were 180 degrees off it would use full motor power to correct itself
    	double angleOff =  RobotMap.gyroToUse.getAngle() - targetDirectionAngle; // get current heading
    	double joystickPower = Robot.oi.getDriver().getY();
    	//int reverseAngleIfGoingBackwards = (int) (joystickPower/Math.abs(joystickPower));
    	double rotationPower = -angleOff*Kp;//*(reverseAngleIfGoingBackwards);
    	//RobotMap.driveTrainRobotDrive21.arcadeDrive(/*moveValue*/ joystickPower, /*rotateValue*/ rotationPower); // drive towards heading 0
    	
    	leftPower = joystickPower-rotationPower;
    	rightPower = joystickPower+rotationPower;
    	RobotMap.driveTrainRobotDrive21.tankDrive(leftPower, rightPower);
// Output to the display for debugging
    	SmartDashboard.putNumber("targetDirectionAngle",targetDirectionAngle); 
     	SmartDashboard.putNumber("Kp",Kp);
    	SmartDashboard.putNumber("angleOff",angleOff);
    	SmartDashboard.putNumber("Motor Output",joystickPower); 
        SmartDashboard.putNumber("Rotation Output",rotationPower); 
        SmartDashboard.putNumber("getAngleX()", RobotMap.IMU.getAngleX());
        SmartDashboard.putNumber("getAngleY()", RobotMap.IMU.getAngleY());
        SmartDashboard.putNumber("getAngleZ()", RobotMap.IMU.getAngleZ());
        SmartDashboard.putNumber("GetAngle()", RobotMap.gyroToUse.getAngle());
        SmartDashboard.putNumber("LeftPower", leftPower);
        SmartDashboard.putNumber("RightPower", rightPower);
                   }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	new ArcadeDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
