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

import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5053.FRC2016Stronghold.MotionControlHelper;
import org.usfirst.frc5053.FRC2016Stronghold.MotionControlPIDController;
import org.usfirst.frc5053.FRC2016Stronghold.Robot;
import org.usfirst.frc5053.FRC2016Stronghold.RobotDriveArcadeStraightPIDOutput;
import org.usfirst.frc5053.FRC2016Stronghold.RobotMap;

/**
 *
 */
public class DriveForward extends Command {
	MotionControlPIDController motionControlHelper = null  ;
//TODO    private final PIDController drivePowerPID;
// Use these to get going:
// setSetpoint() -  Sets where the PID controller should move the system
//                  to
// enable() - Enables the PID controller.
double targetAngle = 0;	
double distance = 0; //Temp, will be set actual value below s
MotionControlPIDController speedFollowerPID;
double targetTolerance = 1 ; //inch

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveForward(double a_distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    	distance = -a_distance; //TODO we really should not have to negate the value
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {

        SmartDashboard.putNumber("Timeinit",this.timeSinceInitialized());
       	//RobotMap.gyroToUse.reset();//TODO don't reset the gyro.
    	targetAngle = RobotMap.gyroToUse.getAngle();	
        RobotMap.driveTrainLeftWheelEncoder.reset();
        RobotMap.driveTrainRightWheelEncoder.reset();
        double maxspeed = 6*12; //first number is Ft/sec the *12 changes it to in/sec
        double ramp = 7; //inches distance to go from start to maxspeed and maxspeed to 0 at the end
        double start = 0; //inches 
        RobotMap.driveTrainLeftWheelEncoder.setPIDSourceType(PIDSourceType.kRate);

        // Setup the motion control (i.e. how fast we are going as we move towards our destination and plus, rampUp/rampDown distances)
        // and use the driveStraight PIDOutput to pass along the speed we want to the PID Controller
        MotionControlHelper speedControl = new MotionControlHelper(distance, ramp, maxspeed, start,
        		                                    RobotMap.driveTrainLeftWheelEncoder,
        		                                    new RobotDriveArcadeStraightPIDOutput(RobotMap.gyroToUse, targetAngle));

        // setup the Pid to control how we follow the Speed
        final double Kp = 0.005;
        final double Ki = 0.00;
        final double Kd = 0.0;

    	speedFollowerPID = new MotionControlPIDController(Kp, Ki, Kd, speedControl );
    	speedFollowerPID.setAbsoluteTolerance(targetTolerance);
    	speedFollowerPID.setOutputRange(-1, 1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {{
    	speedFollowerPID.enable();
//SmartDashboard.putData((NamedSendable) RobotMap.IMU);
	   double angle = RobotMap.gyroToUse.getAngle(); // get current heading
       SmartDashboard.putNumber("DriveForward angle", angle);
       
       SmartDashboard.putNumber("DriveForward Left",RobotMap.driveTrainLeftWheelEncoder.getDistance());
       SmartDashboard.putNumber("DriveForward Right",RobotMap.driveTrainRightWheelEncoder.getDistance());
       SmartDashboard.putNumber("DriveForward Left Rate",RobotMap.driveTrainLeftWheelEncoder.getRate());
//       SmartDashboard.putNumber("Left Target Rate",targetSpeed);
       System.out.println("Time="+this.timeSinceInitialized()
                         +" encoderDist="+RobotMap.driveTrainLeftWheelEncoder.getDistance()
//                         +" Left Target Rate="+targetSpeed);
);       
            

   }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putString("autonForward", "isFinished()");
  /*   if(speedFollowerPID.onTarget());
     {
    	 speedFollowerPID.disable();
     }
    	return speedFollowerPID.onTarget();
    	return false; */
    	
    	if(Math.abs(RobotMap.driveTrainLeftWheelEncoder.getDistance()-distance)<targetTolerance) { 
       		speedFollowerPID.disable();
//       		mcPID.getError() 
       		System.out.println("Turn Finished true");  
       	  	RobotMap.driveTrainRobotDrive21.tankDrive(0,0);
       		return  true;
       	}
       	else{
               return false;
       	}

    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("autonForward", "end()");
    	speedFollowerPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("autonForward", "interrupted()");
    	end();
      }
}