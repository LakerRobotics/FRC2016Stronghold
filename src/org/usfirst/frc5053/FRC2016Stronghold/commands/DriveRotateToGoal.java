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
public class DriveRotateToGoal extends DriveSpin {
	double missAlignedShooterToCamera = -3; // in degrees
	 
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveRotateToGoal() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    	super(/*Robot.oi.getDriver().getPOV()*/);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
//Sould be called in super        requires(Robot.driveTrain);
     this.ramp = this.ramp;
     //this.ramp = this.ramp/2; // make it go to target faster (can go faster because at most traveling 26 Degrees, not 90 or 180, like for Field Align, so not much intertia will be built up. 	
     //constructorInit();         

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	super.initialize();
		rotationSpeedProfile.setTargetDistance(this.getTargetGoalAngle());
    	rotationSpeedPID.enable();
    }

	/**
	 * @return
	 */
	private double getTargetGoalAngle() {
    	double visionAngle = Robot.visionHandler.getGoalOffset();
    	targetAngle = visionAngle + missAlignedShooterToCamera + RobotMap.gyroToUse.getAngle();
		return targetAngle;
	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		rotationSpeedProfile.setTargetDistance(this.getTargetGoalAngle());
    }

    // Make this return true when this Command no longer needs to run execute()
  //USE PARENT    protected boolean isFinished() {
  //USE PARENT        return false;
  //USE PARENT    }

    // Called once after isFinished returns true
  //USE PARENT    protected void end() {
  //USE PARENT  	    rotationSpeedPID.disable();
  //USE PARENT      }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
  //USE PARENT    protected void interrupted() {
  //USE PARENT    }
}
