package org.usfirst.frc5053.FRC2016Stronghold.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc5053.FRC2016Stronghold.Robot;

public class AlignToLowGoalAndShoot extends CommandGroup {
	
	public AlignToLowGoalAndShoot() {
		addSequential(new DriveSpin(Robot.visionHandler.getGoalOffset()));
		addSequential(new AutonShooterSpinSpeedCalculation(.5));
		addSequential(new AutonScalerReachUp(1.0));
	}
}