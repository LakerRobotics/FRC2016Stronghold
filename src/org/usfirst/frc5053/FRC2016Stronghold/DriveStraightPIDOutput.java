package org.usfirst.frc5053.FRC2016Stronghold;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraightPIDOutput extends rotateRobotPIDOutput {

	public DriveStraightPIDOutput() {
	    SmartDashboard.putString("DriveStraightPIDOutput", "constructor called");
	}
	
	@Override
	public void pidWrite(double rotationPower) {
//		RobotMap.driveTrainRobotDrive21.tankDrive(-output,output); 
		System.out.println("DriveStraightPIDOutput Rotation Motor Output:"+rotationPower);
		SmartDashboard.putNumber("DriveStraightPIDOutput Rotation Motor Output",rotationPower); 
		
    	double leftPower; 
    	double rightPower;
    	double joystickPower = Robot.oi.getDriver().getY();
    	//int reverseAngleIfGoingBackwards = (int) (joystickPower/Math.abs(joystickPower));
    	//RobotMap.driveTrainRobotDrive21.arcadeDrive(/*moveValue*/ joystickPower, /*rotateValue*/ rotationPower); // drive towards heading 0
    	
    	leftPower = joystickPower-rotationPower;
    	rightPower = joystickPower+rotationPower;
    	RobotMap.driveTrainRobotDrive21.tankDrive(leftPower, rightPower);

	}


}
