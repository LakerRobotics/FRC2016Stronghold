package org.usfirst.frc5053.FRC2016Stronghold;

import edu.wpi.first.wpilibj.PIDOutput;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Rich Topolewski
 * 
 * Used to take the speed calulcated from the PID control and pass it to the drive train, 
 * and also adjust the speed going to the wheels to drive straight
 *
 */
public class RobotDriveStraightPIDOutput implements PIDOutput {

	// This is just a simpe P control, Proportional control of the line follow
	// if we assume angle is in degrees and if we were off by 20 Degress then we would want how much correction
	// for example id Kp is 0.025 at 20 degrees we would have 0.5 or half the power toward rotating the robot 
	double Kp = 0.025;// was 0.05 with a delay of 0.004
	

	public RobotDriveStraightPIDOutput() {
//	    SmartDashboard.putString("rotateRobotPIDOutput", "constructor called");
	}

	@Override
	public void pidWrite(double motorPower) {
	   	double angle = RobotMap.navigationAnalogGyro.getAngle(); // get current heading
			    RobotMap.driveTrainRobotDrive21.arcadeDrive(/*moveValue*/ motorPower, /*rotateValue*/ -angle*Kp*(motorPower/Math.abs(motorPower))); // drive towards heading 0
	    SmartDashboard.putNumber("Motor Output",motorPower); 
	}

}

