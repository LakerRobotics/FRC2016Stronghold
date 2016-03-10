package org.usfirst.frc5053.FRC2016Stronghold.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonPosition1 extends CommandGroup {
    public AutonPosition1() {
	//addSequential(new ArmSetpoints)
    addSequential(new ArmSetpoints(ArmSetpoints.FULL_DOWN/*0.349*/));
	addSequential(new Wait(4));//seconds to wait
	addSequential(new DriveForward(144.5, 6, 28)); //Distance, speed (ft/sec), ramp/rampdown
//	addSequential(new ArmSetpoints(ArmSetpoints.NEUTRAL));
//	addSequential(new DriveForward(87.5-(2*12),6*12,14));
//	addSequential(new DriveSpin(60));
//	addSequential(new DriveForward(76,6*12,14));
//	addSequential(new ShooterSpin(1));
   
    }
}
