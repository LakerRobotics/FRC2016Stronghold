package org.usfirst.frc5053.FRC2016Stronghold;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Rich Topolewski
 * 
 * Used to take the speed calculated from the PID control and pass it to the drive train, 
 * and also adjust the speed going to the wheels to drive straight
 *
 */
public class RobotDriveStraightSpeedPIDOutput implements PIDOutput {

	// This is just a simple P control, Proportional control of the line follow
	// if we assume angle is in degrees and if we were off by 20 Degrees then we would want how much correction
	// for example id Kp is 0.025 at 20 degrees we would have 0.5 or half the power toward rotating the robot 
	double Kp = 0d/20d; //0.025;// 
	Gyro m_gyro; 
	double m_targetAngle = 0.0d;
	

	public RobotDriveStraightSpeedPIDOutput(Gyro theGyro, double targetAngle) {
//	    SmartDashboard.putString("RobotDriveSpinPIDOutput", "constructor called");
		m_gyro = theGyro;
		m_targetAngle = targetAngle;
	}

	@Override
	public void pidWrite(double motorPower) {
		
    	double leftPower; 
    	double rightPower;
		
	   	double angle = m_gyro.getAngle(); // get current heading
	   	double angleOff = angle - m_targetAngle; 
	    double rotationPower = -angleOff*Kp*(motorPower/Math.abs(motorPower));  // the last term in the () is to get the sign fixed when going backwards)
	   	//double rotationPower = 0;
	    
	    // CHANGES MARCH 31st | Changed the arcadeDrive for the PID to tank in a similar fashion to the RobotDriveArcadeStraightPIDOutput
	    
	    leftPower = motorPower - rotationPower;
	    rightPower = motorPower + rotationPower;
	    
	   // RobotMap.driveTrainRobotDrive21.arcadeDrive(/*moveValue*/ motorPower, /*rotateValue*/ rotationPower); COMMENTED OUT March 31st
	   	RobotMap.driveTrainRobotDrive21.tankDrive(leftPower, rightPower);
	   	
	   	//CHANGES END 
	   	
	    SmartDashboard.putNumber("RobotDriveStraightPIDOoutput Motor Output",motorPower);
	    SmartDashboard.putNumber("RobotDriveStraightPIDOoutput RotationPower", rotationPower);
	}

}

