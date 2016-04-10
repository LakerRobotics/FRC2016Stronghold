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

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5053.FRC2016Stronghold.MotionControlHelper;
import org.usfirst.frc5053.FRC2016Stronghold.MotionControlPIDController;
import org.usfirst.frc5053.FRC2016Stronghold.Robot;
import org.usfirst.frc5053.FRC2016Stronghold.RobotMap;

/**
 *
 */
public class ShooterAimSetpoints extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	   // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	double m_targetTiltAngle = 10;//default in degrees
	double targetTolerance = 1 ; //degrees

    double     ramp =  10; //degrees ramps up from 50% of the top speed to the maxSpeed, and from maxspeed to zero
    double maxspeed = 60.0*(360/60) ; //360/60 converts the first numbers which is in RPM to degrees/second 4/1/2016 RGT increase from 15 to 20
    double    start =   10; //degrees
    MotionControlHelper rotationSpeedProfile; 
    
    final double Kp = 1d/(60*(360/6)); // so at denominator off in the spin-Rate the power will reach the max
    final double Ki = 0.0005;
    final double Kd = 0.0;
    MotionControlPIDController tiltSpeedPID;
 

    	double minAngle = 10;
    	double maxAngle = 45;
    
    public ShooterAimSetpoints(double targetTiltAngle) {
        SmartDashboard.putString("ShooterAimSetPointsCodeLocation","entered ShooterAimSetPoints("+targetTiltAngle+")");
	       // Use requires() here to declare subsystem dependencies
	       // eg. requires(chassis);
        m_targetTiltAngle = targetTiltAngle;
	    	System.out.println("ShooterAimSetPoints targetTiltAngle "+m_targetTiltAngle+".");
	    	if(m_targetTiltAngle > maxAngle) targetTiltAngle = maxAngle;
	    	if(m_targetTiltAngle < minAngle) targetTiltAngle = minAngle;
	    	System.out.println("ShooterAimSetPoints targetTiltAngle after min max limit is "+m_targetTiltAngle+".");

	       // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shooterAim);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
         
	 }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR

    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putString("ShooterAimSetPointsCodeLocation","entered initialize()");
//    	   	RobotMap.gyroToUse.reset();
    	   	
 //   	    rotationSpeedPID.setAbsoluteTolerance(targetTolerance);
//    	    mcPID.free();
        //set so the imuTilt will return the current tilt angle
        RobotMap.imuTilt.setPIDSourceType(PIDSourceType.kDisplacement);
        start = RobotMap.imuTilt.pidGet();
        rotationSpeedProfile = new MotionControlHelper(m_targetTiltAngle, ramp, maxspeed, start, 
        		(PIDSource) RobotMap.imuTilt,
        		RobotMap.shooterAimMotor);
        tiltSpeedPID = new MotionControlPIDController(Kp,Ki,Kd, rotationSpeedProfile );
    	tiltSpeedPID.setOutputRange(-0.10, 0.10);
    	tiltSpeedPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putString("ShooterAimSetPointsCodeLocation","entered execute()");
        System.out.println("ShooterAimSetPointsCodeLocation "+"entered execute()");
    	// get current Angle to report on
//    	double currentAngle = RobotMap.IMU.getAngleX(); // RGT 20160408 this is from startup angle or could be reset
//        double currentAngle = RobotMap.IMU.getRoll(); // RGT 20160408 Think this should be absolute relative to the floor
        //Ensure the imuTilt will return the current tilt angle
        RobotMap.imuTilt.setPIDSourceType(PIDSourceType.kDisplacement);
        double currentAngle = RobotMap.imuTilt.pidGet(); // RGT 20160408 Think this should be absolute relative to the floor
        SmartDashboard.putNumber("ShooterAimSetPoints Current angle",currentAngle);
        SmartDashboard.putNumber("ShooterAimSetPoints TargetTiltAngle",m_targetTiltAngle);
        
        //set so the imuTilt will return the current tilt angle rate
        RobotMap.imuTilt.setPIDSourceType(PIDSourceType.kRate);
        double currentRate = RobotMap.imuTilt.pidGet(); // RGT 20160408 Think this should be absolute relative to the floor
        //set so the imuTilt will return the current tilt angle
        RobotMap.imuTilt.setPIDSourceType(PIDSourceType.kDisplacement);

        SmartDashboard.putNumber("ShooterAimSetPoints TiltRotation Actual Rate",currentRate);
        SmartDashboard.putNumber("ShooterAimSetPoints TiltRotation Target Rate",rotationSpeedProfile.getTargetSpeed(currentAngle));
        SmartDashboard.putNumber("ShooterAimSetPoints Time",this.timeSinceInitialized());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        SmartDashboard.putString("ShooterAimSetPointsCodeLocation","entered isFinished()");
        
    	if(Math.abs(RobotMap.imuTilt.pidGet()-m_targetTiltAngle)<targetTolerance) { 
//       		rotationSpeedPID.disable();
       		System.out.println("ShooterAimSetpoints Finished true");
// lets let it continually update the angle, trying to get better       		
//       	  	RobotMap.shooterAimMotor.set(0);; 
       		return  true;
       	}
       	else{
               return false;
       	}

    }

    // Called once after isFinished returns true
    protected void end() {
        SmartDashboard.putString("ShooterAimSetPointsCodeLocation","entered end()");
        System.out.println("ShooterAimSetPointsCodeLocation "+"entered end()");
//   	rotationSpeedPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        SmartDashboard.putString("ShooterAimSetPointsCodeLocation","entered interrupted()");
        System.out.println("ShooterAimSetPointsCodeLocation "+"entered end()");
        RobotMap.shooterAimMotor.set(0);//
       	tiltSpeedPID.disable();
       	end();
    }
 
}
																