
package org.usfirst.frc5053.FRC2016Stronghold.commands;

import org.usfirst.frc5053.FRC2016Stronghold.Robot;
import org.usfirst.frc5053.FRC2016Stronghold.subsystems.Navigation;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonPosition1 extends CommandGroup {
    public AutonPosition1() {
    	//Setup
    	double driveSpeed = 4; 
    	double distanceToDefense =   Navigation.distanceAutoLineToEdgeofDefense
    			                   -(Navigation.robotLength+Navigation.robotBumperThickness);

    	double distanceToTravelAcrossLowBar =   Navigation.defenseApron
                                              + Navigation.defenseSwapSection
                                              + Navigation.defenseApron
                                              + Navigation.robotLength;
    	double distanceToTravelIntoCourtYard=12.5*12;
    	double extraDistanceIntoNeutralZoneOnReturn = 10;

    	// Bring Arm down and drive forward

    	addSequential(new AutonArmSetpoints(AutonArmSetpoints.FULL_DOWN, 2));// Full down and wait 3 seconds for it to happen
    	addSequential(new DriveForward(
    			+ distanceToDefense
    			+ distanceToTravelAcrossLowBar, 4, 12)); //Distance, speed (ft/sec), ramp/rampdown | FROM 3 ft/sec TO 4 ft/sec
    	addSequential(new AutonArmSetpoints(AutonArmSetpoints.NEUTRAL, 0.01));// Start Arms Traveling up | MOVED from before turn TO here
    	addSequential(new DriveForward(
    			 distanceToTravelIntoCourtYard, 8, 24)); //Distance, speed (ft/sec), ramp/rampdown | FROM 6 ft/sec TO  8 ft/sec
   // addSequential(new AutonArmSetpoints(AutonArmSetpoints.NEUTRAL, 0.01));// Start Arms Traveling up
    addSequential(new DriveSpin(38));// Should be facing the low goal | original 10 then 15 now 20 RPM FASTER
//    addSequential(new Wait(0.6));
//    addSequential(new DriveSpin(Robot.visionHandler.getGoalOffset()));
    
    // Drive at Low goal and shoot
	addSequential(new DriveForward(76+6,driveSpeed,12));
	addSequential(new AutonShooterSpin(-1200,0.5));// RPM, Max time in seconds for it to come up to speed
	addSequential(new AutonScalerReachUp(0.2));//Time to push the kicker 
	addSequential(new DriveForward(6, 3, 1)); //Drive up to the tower to push failed shots in | ADDED
	addSequential(new AutonScalerReachUp(1.0));//Time to push the kicker 
	addSequential(new AutonShooterSpin(0,0.01));// Turn Off the spinners
//    addSequential(new AutonArmSetpoints(AutonArmSetpoints.FULL_DOWN, 0.01));// Start Arms Traveling Down
    
    // Head home
//	addSequential(new DriveForward(-76,driveSpeed,12));
//  	addSequential(new DriveSpin(-38));
// 	addSequential(new DriveForward(-distanceToTravelIntoCourtYard
// 			                       -distanceToTravelAcrossLowBar
// 			                       -extraDistanceIntoNeutralZoneOnReturn,6,12));
//    addSequential(new CrossLowBar(CrossLowBar.BACKWARD));	
    //all the rest of these rampupdown were 14
    }
}
