package org.usfirst.frc5053.FRC2016Stronghold.commands;

import org.usfirst.frc5053.FRC2016Stronghold.Robot;

public class AutonShooterSpinSpeedCalculation extends AutonShooterSpin {
	
	private double setpoint;
	
	public AutonShooterSpinSpeedCalculation(double timeMax) {
		super(getShooterSpeed(Robot.visionHandler.getDistance()), timeMax);		
	}
	
	public static double getShooterSpeed(double distance) {
        // If shooting under 4ft is too low then increase this number.
		// Take an estimate about how short it was in height and go to the spreadsheet and use the excel menu DATA>>What-If Analysis>>Goal seek to find the new desired speed.
		// the goal seek should seek a the new height (e.g. the original height(8.5ft) plus how low the ball was from the top of the goal).
		// Example if the shot was 1.5ft low then the goal seek should look to have cell K12 set to 10ft while changing cell F12.  Note 10ft is the 8.5 + 1.5 how much it was off by.

		double lowSpeed = 1500;  // If shooting close is to low Increase this number.  Take an estimate about how low it is and go to the spreadsheet and is excel goal seek to find the new desired speed that will get the the original heigh t(8.5ft) to the 8.5 + how much it was off by

		double highSpeed = 2500;
		
		if(distance < 4) {
            return lowSpeed;
		}
		else if(distance > 7) {
			return highSpeed;
		}
		else {
			return lowSpeed + (distance*((highSpeed-lowSpeed)/(7-4)) );// Originally 800 + (distance *566.667
		}
	}
}