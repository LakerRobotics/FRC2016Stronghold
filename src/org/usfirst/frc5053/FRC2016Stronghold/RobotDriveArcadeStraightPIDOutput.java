package org.usfirst.frc5053.FRC2016Stronghold;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotDriveArcadeStraightPIDOutput extends RobotDriveSpinPIDOutput {

	public RobotDriveArcadeStraightPIDOutput() {
	    SmartDashboard.putString("RobotDriveArcadeStraightPIDOutput", "constructor called");
	}
	
	@Override
	public void pidWrite(double rotationPower) {
//		RobotMap.driveTrainRobotDrive21.tankDrive(-output,output); 
		System.out.println("RobotDriveArcadeStraightPIDOutput Rotation Motor Output:"+rotationPower);
		SmartDashboard.putNumber("RobotDriveArcadeStraightPIDOutput Rotation Motor Output",rotationPower); 
		
    	double leftPower; 
    	double rightPower;
    	double joystickPower = Robot.oi.getDriver().getY();
    	
    	// We are having problems when reversing direction, from going forward to reverse
    	// I think it is caused by the PID applying power to rotate, when we are no longer traveling
    	// have any Joystick power so in transition the rotational power is the only power applied
    	// and that power ends up turning the robot a lot as the robot transitions from forward to backward, 
    	// actually it might be that the gyro is off to the side so as the robot slows down the gyro twists 
    	// say it spins counter clockwise , then it owuld try to compensata
    	// Think need to collect data, need to look at:
    	// 1) the gyro angle during transition, to see if the readings are shifting 
    	//    while transition from forward to revesre,
    	// 2) is it when the power from the joystick is zero
    	// 3) is it when the power on the joystick is reverse that of the direction of the wheels.
    	//
    	// O would place my bet on #1, and the solution may be to move the gyro to be in the middle of the robot, 
    	// and/or more ridgedly attach the Gyro to the robot.
    	
    	
    	//RobotMap.driveTrainRightWheelEncoder.setPIDSourceType(PIDSourceType.kRate);
    	//double wheelSpeed = RobotMap.driveTrainRightWheelEncoder.getRate();
    	//int reverseAngleIfGoingBackwards = (int) (wheelSpeed/Math.abs(WheelSpeed));
    	//RobotMap.driveTrainRobotDrive21.arcadeDrive(/*moveValue*/ joystickPower, /*rotateValue*/ rotationPower); // drive towards heading 0
    	
    	//maybe the thing to do is make the amount of rotational power be proportional to the speed of the robot
    	// dont think it matters the direction or the transition but if getting to zero then should be less power applied, but not zero 
    	// again it is only during the transition so what would explain that?
    	// if power is needed to be turning the robot to be on the target angle is say 20% and are going full force forward,
    	// then if are maxed out on the one motor but the other is reduced think need to down the power so that the full differentail dosn't change depending on running into the full
    	
    	// Reduce joystick power so can get full turning effect, and hopefully avoid a jerk in the rotation
    	// if quickly taken off full throttle.
    	if (joystickPower + rotationPower > 1.0){
    		joystickPower = 1 - rotationPower;
    	}
    	
    	leftPower = joystickPower-rotationPower;
    	rightPower = joystickPower+rotationPower;
    	RobotMap.driveTrainRobotDrive21.tankDrive(leftPower, rightPower);

	}


}
