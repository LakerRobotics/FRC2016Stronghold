package org.usfirst.frc5053.FRC2016Stronghold.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonPosition1 extends CommandGroup {
    public AutonPosition1() {
        addSequential(new AutonArmSetpoints(AutonArmSetpoints.FULL_DOWN, 3));// Full down and wait 3 seconds for it to happen
         addSequential(new CrossLowBar(CrossLowBar.FORWARD));	// at this point the back of our bumper should be just at the end of the defense apron
	//addSequential(new ArmSetpoints)
//usingLowBarAuton    addSequential(new ArmSetpoints(ArmSetpoints.FULL_DOWN/*0.349*/));
  //usingLowBarAuton	addSequential(new Wait(4));//seconds to wait
  //usingLowBarAuton	addSequential(new DriveForward(144.5, 6, 28)); //Distance, speed (ft/sec), ramp/rampdown
//	addSequential(new ArmSetpoints(ArmSetpoints.NEUTRAL));
//	addSequential(new DriveForward(87.5-(2*12),6*12,14));
    addSequential(new DriveForward(24,6,14));
    addSequential(new DriveSpin(60));// Should be facing the low goal
	addSequential(new DriveForward(76,6,14));
//	addSequential(new ShooterSpin(1));
	addSequential(new DriveForward(-76,6,14));
  	addSequential(new DriveSpin(-60));
 	addSequential(new DriveForward(-24,6,14));
    addSequential(new CrossLowBar(CrossLowBar.BACKWARD));	
    }
}
