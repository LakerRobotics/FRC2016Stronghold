package org.usfirst.frc5053.FRC2016Stronghold;

import edu.wpi.first.wpilibj.PIDOutput;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotDriveStraightPIDOutput implements PIDOutput {

	double Kp = 0.025;// was 0.05 whith a delay of 0.004

	public RobotDriveStraightPIDOutput() {
//	    SmartDashboard.putString("rotateRobotPIDOutput", "constructor called");
	}

	@Override
	public void pidWrite(double motorPower) {
//TODO	   	double angle = RobotMap.driveGyro.getAngle(); // get current heading
		//TODO	    RobotMap.driveRobotDrive.drive(motorPower, -angle*Kp*(motorPower/Math.abs(motorPower))); // drive towards heading 0
	    SmartDashboard.putNumber("Motor Output",motorPower); 
	}

}

