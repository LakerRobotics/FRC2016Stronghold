package org.usfirst.frc5053.FRC2016Stronghold;

import edu.wpi.first.wpilibj.PIDOutput;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class rotateRobotPIDOutput implements PIDOutput {


	public rotateRobotPIDOutput() {
//	    SmartDashboard.putString("rotateRobotPIDOutput", "constructor called");
	}

	@Override
	public void pidWrite(double output) {
					RobotMap.driveTrainRobotDrive21.tankDrive(output,-output); 
		 SmartDashboard.putNumber("Motor Output",output); 
	}

}
