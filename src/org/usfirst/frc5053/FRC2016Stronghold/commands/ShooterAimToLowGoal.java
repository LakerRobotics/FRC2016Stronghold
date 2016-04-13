package org.usfirst.frc5053.FRC2016Stronghold.commands;

import org.usfirst.frc5053.FRC2016Stronghold.Robot;
import java.math.*;

public class ShooterAimToLowGoal extends ShooterAimSetpoints {

	public ShooterAimToLowGoal() {
		super(getShooterAngle(Robot.visionHandler.getDistance())-38/12);//subtract off the distance from the camera to the front of the robot because equations are based on the front of the robot
	}
	
	//@overide
    protected void initialize() {
		this.setSetpoint(getShooterAngle(Robot.visionHandler.getDistance())-38/12);//subtract off the distance from the camera to the front of the robot because equations are based on the front of the robot
		super.initialize();
	}	 
	
	
	public static double getShooterAngle(double distance) {
		
		if(distance < 4) {
			return 9;
		}
		
		else if(distance > 7) {
			return (-0.0004 * Math.pow(distance, 3)) + (0.0235 * Math.pow(distance, 2)) + (0.2713 * distance) + 4.7726;
		}
		
		else {
			return (-0.1502 * Math.pow(distance, 3)) + (2.9814 * Math.pow(distance, 2)) + (20.525 * distance) + 53.024;
		}
	}
}