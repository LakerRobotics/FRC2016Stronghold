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
import org.usfirst.frc5053.FRC2016Stronghold.rotateRobotPIDOutput;

/**
 *
 */
public class Turn90 extends Command {
	double turn = 90;//default in degrees
	double targetTolerance = 10 ; //degrees

    double     ramp =  10; //degrees
    double maxspeed = 50; //degrees/second
    double    start =   0; //degrees
    MotionControlHelper rotationSpeedProfile; 
    
    final double Kp = 0.005;
    final double Ki = 0.0005;
    final double Kd = 0.0;
    MotionControlPIDController rotationSpeedPID;
    
    public Turn90(double a_turn) {
        SmartDashboard.putString("Turn90CodeLocation","entered Turn90(a_turn)");
		   turn = a_turn;
	       // Use requires() here to declare subsystem dependencies
	       // eg. requires(chassis);

	       // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
     rotationSpeedProfile = new MotionControlHelper(turn, ramp, maxspeed, start, (PIDSource) RobotMap.gyroToUse,new rotateRobotPIDOutput());
 	 rotationSpeedPID = new MotionControlPIDController(Kp,Ki,Kd, rotationSpeedProfile );
         
	 }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public Turn90() {
        SmartDashboard.putString("Turn90CodeLocation","entered Turn90()");

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        //Setup the rotation profile, how fast we are going to spin & the rampUp/RampDown distance to get there
        rotationSpeedProfile = new MotionControlHelper(turn, ramp, maxspeed, start, (PIDSource) RobotMap.gyroToUse,new rotateRobotPIDOutput());
        rotationSpeedPID = new MotionControlPIDController(Kp,Ki,Kd, rotationSpeedProfile );
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putString("Turn90CodeLocation","entered initialize()");
    	   	RobotMap.gyroToUse.reset();
    	    rotationSpeedPID.setAbsoluteTolerance(targetTolerance);
//    	    mcPID.free();
    	    rotationSpeedPID.setOutputRange(-1.0, 1.0);
    	    rotationSpeedPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putString("Turn90CodeLocation","entered execute()");
        System.out.println("Turn90CodeLocation "+"entered execute()");
        SmartDashboard.putBoolean("Turn isenabled",rotationSpeedPID.isEnabled());
    	// get current Angle to report on
    	double currentAngle = RobotMap.gyroToUse.getAngle();
        SmartDashboard.putNumber("Turn angle",currentAngle);
        SmartDashboard.putNumber("Turn Gyro Rate",RobotMap.gyroToUse.getRate());
        SmartDashboard.putNumber("Turn Gryro Target Rate",rotationSpeedProfile.getTargetSpeed(currentAngle));
        SmartDashboard.putNumber("Turn Time",this.timeSinceInitialized());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        SmartDashboard.putString("Turn90CodeLocation","entered isFinished()");
    	if(Math.abs(RobotMap.gyroToUse.getAngle()) > Math.abs(turn)) { //TODO make it within tolerance
//       		rotationSpeedPID.disable();
//       		mcPID.getError()
       		System.out.println("Turn Finished true");  
       	  	RobotMap.driveTrainRobotDrive21.tankDrive(0,0);
       		return true;
       	}
       	else{
               return false;
       	}

    }

    // Called once after isFinished returns true
    protected void end() {
        SmartDashboard.putString("Turn90CodeLocation","entered end()");
        System.out.println("Turn90CodeLocation "+"entered end()");
   	rotationSpeedPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        SmartDashboard.putString("Turn90CodeLocation","entered interrupted()");
        System.out.println("Turn90CodeLocation "+"entered end()");
        rotationSpeedPID.disable();
    }
}
