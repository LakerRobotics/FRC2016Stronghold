
package org.usfirst.frc5053.FRC2016Stronghold.commands;

import org.usfirst.frc5053.FRC2016Stronghold.Robot;
import org.usfirst.frc5053.FRC2016Stronghold.subsystems.Navigation;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonLowGoal extends CommandGroup {
    public AutonLowGoal() {
    	//Setup
    	double driveSpeed = 4; 
   
//    	addSequential(new Wait(0.5));
//      addSequential(new DriveSpin(Robot.visionHandler.getGoalOffset()));
    	addSequential(new DriveForward(76+6+6,driveSpeed,12));
    	addSequential(new AutonShooterSpin(-2500,0.5));// RPM, Max time in seconds for it to come up to speed
    	addSequential(new AutonScalerReachUp(0.5));//Time to push the kicker 
    	addSequential(new DriveForward(18+6, 6, 1)); //Drive up to the tower to push failed shots in | ADDED
    	addSequential(new AutonScalerReachUp(1.0));//Time to push the kicker 
    	addSequential(new AutonShooterSpin(0,0.01));// Turn Off the spinners

    }
}
