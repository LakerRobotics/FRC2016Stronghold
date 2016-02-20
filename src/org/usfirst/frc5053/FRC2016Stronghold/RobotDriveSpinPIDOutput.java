package org.usfirst.frc5053.FRC2016Stronghold;

import edu.wpi.first.wpilibj.PIDOutput;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotDriveSpinPIDOutput implements PIDOutput {


	public RobotDriveSpinPIDOutput() {
	    SmartDashboard.putString("RobotDriveSpinPIDOutput", "constructor called");
	}

	@Override
	public void pidWrite(double output) {
		RobotMap.driveTrainRobotDrive21.tankDrive(-output,output); 
		System.out.println("RobotDriveSpinPIDOutput Rotation Motor Output:"+output);
		SmartDashboard.putNumber("RobotDriveSpinPIDOutput Rotation Motor Output",output); 
	}

}
