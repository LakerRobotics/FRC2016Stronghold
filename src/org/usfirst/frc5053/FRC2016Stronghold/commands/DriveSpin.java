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
import edu.wpi.first.wpilibj.PIDSource;

import org.usfirst.frc5053.FRC2016Stronghold.Robot;
import org.usfirst.frc5053.FRC2016Stronghold.RobotMap;
import org.usfirst.frc5053.FRC2016Stronghold.MotionControlHelper;
import org.usfirst.frc5053.FRC2016Stronghold.MotionControlPIDController;
import org.usfirst.frc5053.FRC2016Stronghold.RobotDriveSpinPIDOutput;

/**
 *
 */
public class DriveSpin extends Command {
	double turn = 0;
	double start = 0;
	double targetAngle = 0; // temp real value calculated below
	double targetTolerance = 1 ; //degrees

    MotionControlPIDController rotationSpeedPID;
    
    
    public DriveSpin(double a_turn) {
        SmartDashboard.putString("DriveSpin","entered DriveSpin(a_turn)");
		   turn = a_turn;
	       // Use requires() here to declare subsystem dependencies
	       // eg. requires(chassis);

	       // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
         
	 }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveSpin() {
        SmartDashboard.putString("DriveSpin","entered DriveSpin()");

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        //Setup the rotation profile, how fast we are going to spin & the rampUp/RampDown distance to get there
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putString("DriveSpin","entered initialize()");
//    	   	RobotMap.gyroToUse.reset();
    	   	
 //   	    rotationSpeedPID.setAbsoluteTolerance(targetTolerance);
//    	    mcPID.free();
		start = Robot.navigation.getGyro().getAngle();
		targetAngle = turn + start;
        rotationSpeedPID = Robot.navigation.createRotationPIDController(start, targetAngle, new RobotDriveSpinPIDOutput());

    }

	/**
	 * 
	 */


    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putString("DriveSpin","entered execute()");
        System.out.println("DriveSpin "+"entered execute()");
    	// get current Angle to report on
    	double currentAngle = RobotMap.gyroToUse.getAngle();
        SmartDashboard.putNumber("DriveSpin Current angle",currentAngle);
        SmartDashboard.putNumber("DriveSpin Turn",turn);
        SmartDashboard.putNumber("DriveSpin Gyro Rate",RobotMap.gyroToUse.getRate());
        SmartDashboard.putNumber("DriveSpin Gryro Target Rate",rotationSpeedPID.getMotionControlHelper().getTargetSpeed(currentAngle));
        SmartDashboard.putNumber("DriveSpin Time",this.timeSinceInitialized());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        SmartDashboard.putString("DriveSpin","entered isFinished()");
        
    	if(Math.abs(RobotMap.gyroToUse.getAngle()-targetAngle)<targetTolerance) { 
       		rotationSpeedPID.disable();
//       		mcPID.getError() 
       		System.out.println("DriveSpin Finished true");  
       	  	RobotMap.driveTrainRobotDrive21.tankDrive(0,0);
       		return  true;
       	}
       	else{
               return false;
       	}

    }

    // Called once after isFinished returns true
    protected void end() {
        SmartDashboard.putString("DriveSpin","entered end()");
        System.out.println("DriveSpin "+"entered end()");
   	rotationSpeedPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        SmartDashboard.putString("DriveSpin","entered interrupted()");
        System.out.println("DriveSpin "+"entered end()");
        end();
    }
}
