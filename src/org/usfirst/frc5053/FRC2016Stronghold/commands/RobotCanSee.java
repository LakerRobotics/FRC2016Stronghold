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
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5053.FRC2016Stronghold.Robot;
import org.usfirst.frc5053.FRC2016Stronghold.RobotMap;

/**
 *
 */
public class RobotCanSee extends Command {
 //   int countToTakePicture = 100;
 //   int currentCountToTakePicture = 0;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public RobotCanSee() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.visionHandler);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// only run vision system image processing every so many calls, example every once every 10 calls
//    	if(currentCountToTakePicture == countToTakePicture){
////    		Robot.visionHandler.getGoalOffset();
//    	}
//    	else if(currentCountToTakePicture <= countToTakePicture){
//    		currentCountToTakePicture++;
//    	} else {
//    		currentCountToTakePicture = 0;
//    	}

    		
 /*   	SmartDashboard.putNumber("Vision Goal Offset", Robot.visionHandler.getGoalOffset());
        String temp2 = "";
        try{
     	   temp2 = SmartDashboard.getString("Robot Name");
        }
        catch(NetworkTableKeyNotDefined e){
     	   System.out.println("Could not find 'Goal High Low' in SmartDashboard network table");
        }
        boolean runningOnLilGeek;
        if(temp2.equals("Lil Geek")){
     	   runningOnLilGeek = true;
        } 
        else{
     	   runningOnLilGeek = false;
        }
        
        if(runningOnLilGeek){
            RobotMap.driveTrainLeftWheelEncoder.setDistancePerPulse(0.069439091/2);
            RobotMap.driveTrainRightWheelEncoder.setDistancePerPulse(0.069439091/2);
            SmartDashboard.putString("Robot On","Lil Geek");
        }
        else{
            SmartDashboard.putString("Robot On","Rudy");
        }
        try{
     	   SmartDashboard.putString("position", SmartDashboard.getString("Start Position"));
        }
        catch(NetworkTableKeyNotDefined e){
  	      System.out.println("Could not find 'Start Position' in SmartDashboard network table");
        }
        */
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
