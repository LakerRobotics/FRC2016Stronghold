package org.usfirst.frc5053.FRC2016Stronghold;

import edu.wpi.first.wpilibj.PIDOutput;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class rotateRobotPIDOutput implements PIDOutput {


	public rotateRobotPIDOutput() {
	    SmartDashboard.putString("rotateRobotPIDOutput", "constructor called");
	}

	@Override
	public void pidWrite(double output) {
		RobotMap.driveTrainRobotDrive21.tankDrive(-output,output); 
		System.out.println("rotateRobotPIDOutput Rotation Motor Output:"+output);
		SmartDashboard.putNumber("rotateRobotPIDOutput Rotation Motor Output",output); 
	}

}
