package org.usfirst.frc5053.FRC2016Stronghold.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc5053.FRC2016Stronghold.Robot;

public class AlignToLowGoalAndShoot extends CommandGroup {
	
	public AlignToLowGoalAndShoot() {
    	addSequential(new VisionAlignForLowGoalShot() );
		addSequential(new AutonShooterSpinSpeedCalculation(.5));//Wait for up to 0.5 seconds for wheels to get up to speed (especially useful for debugging on Lil Geek)
		addSequential(new AutonScalerReachUp(1.0));//Shot should fire before this and then driver releases the button and we are interrupted
	}
}