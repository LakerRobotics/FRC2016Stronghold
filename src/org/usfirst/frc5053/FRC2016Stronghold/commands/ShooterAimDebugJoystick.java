package org.usfirst.frc5053.FRC2016Stronghold.commands;

import org.usfirst.frc5053.FRC2016Stronghold.Robot;
import java.math.*;

public class ShooterAimDebugJoystick extends ShooterAimSetpoints {

	public ShooterAimDebugJoystick() {
		super(getShooterAngle(Robot.visionHandler.getDistance()));
	}
	
	//@overide
    protected void initialize() {
		this.setSetpoint(90*(1-(Robot.oi.debugJoystick.getZ()+1)/2));
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