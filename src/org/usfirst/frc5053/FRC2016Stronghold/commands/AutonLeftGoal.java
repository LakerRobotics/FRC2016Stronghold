
package org.usfirst.frc5053.FRC2016Stronghold.commands;

import org.usfirst.frc5053.FRC2016Stronghold.Robot;
import org.usfirst.frc5053.FRC2016Stronghold.subsystems.Navigation;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonLeftGoal extends CommandGroup {
    public AutonLeftGoal(int goalHeight) {
    	//Setup
    	double driveSpeed = 4; 
    	double distanceToDefense =   Navigation.distanceAutoLineToEdgeofDefense
    			                   -(Navigation.robotLength+Navigation.robotBumperThickness);

    	double distanceToTravelAcrossLowBar =   Navigation.defenseApron
                                              + Navigation.defenseSwapSection
                                              + Navigation.defenseApron
                                              + Navigation.robotLength;
    	double distanceToTravelIntoCourtYard=12.5*12 -3*12; // anothe foot on after first match on Saturday
    	double extraDistanceIntoNeutralZoneOnReturn = 10;

    	// Bring Arm down and drive forward

    //	addSequential(new AutonArmSetpoints(AutonArmSetpoints.FULL_DOWN, 2));// Full down and wait 3 seconds for it to happen
    	//addSequential(new DriveForward(
    		//	+ distanceToDefense
    			//+ distanceToTravelAcrossLowBar, 4, 12)); //Distance, speed (ft/sec), ramp/rampdown | FROM 3 ft/sec TO 4 ft/sec
    	addSequential(new AutonArmSetpoints(AutonArmSetpoints.NEUTRAL, 0.01));// Start Arms Traveling up | MOVED from before turn TO here
    	addSequential(new DriveForward(
    			 distanceToTravelIntoCourtYard, 8, 24)); //Distance, speed (ft/sec), ramp/rampdown | FROM 6 ft/sec TO  8 ft/sec
   // addSequential(new AutonArmSetpoints(AutonArmSetpoints.NEUTRAL, 0.01));// Start Arms Traveling up
    addSequential(new DriveSpin(38));// Should be facing the low goal | original 10 then 15 now 20 RPM FASTER
    
    // Drive at Low goal and shoot
    if (goalHeight == AutonSelection.LOW_GOAL){
    	addSequential(new AutonLowGoal());
    	}
    else if (goalHeight == AutonSelection.HIGH_GOAL){
    	addSequential(new AutonHighGoal());
    	}
    }
}
