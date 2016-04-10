package org.usfirst.frc5053.FRC2016Stronghold.commands;

import org.usfirst.frc5053.FRC2016Stronghold.Robot;

public class ShooterAimSetpointsAngleCalculations extends ShooterAimSetpoints {

	public ShooterAimSetpointsAngleCalculations(double setpoint) {
		super(getShooterAngle(Robot.visionHandler.getDistance()));
		// TODO Auto-generated constructor stub
	}
	
	public static double getShooterAngle(double distance) {
		if(distance < 4) {
            return -1.3677 * (distance * distance) + (0.6361 * distance) + 86.144;
		}
		else if(distance > 7) {
			return 0.0829 * (distance * distance) - (3.7229 * distance) + 65.48;
		}
		else {
			return 1.3106 * (distance * distance) - (21.546 * distance) + 131.45;
		}
	}
}