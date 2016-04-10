package org.usfirst.frc5053.FRC2016Stronghold.commands;

import org.usfirst.frc5053.FRC2016Stronghold.Robot;
import org.usfirst.frc5053.FRC2016Stronghold.subsystems.Navigation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonPosition5 extends CommandGroup {
    public AutonPosition5(int defense) {
       	double driveSpeed = 4; 
    	double distanceToDefense =   Navigation.distanceAutoLineToEdgeofDefense
    			                   -(Navigation.robotLength+Navigation.robotBumperThickness);

    	double distanceToTravelAcrossLowBar =   Navigation.defenseApron
                                              + Navigation.defenseSwapSection
                                              + Navigation.defenseApron
                                              + Navigation.robotLength;
    	double distanceToTravelIntoCourtYard=12.5*12 -3*12- 2*12; // anothe foot on after first match on Saturday
    	double extraDistanceIntoNeutralZoneOnReturn = 10;

    	addSequential(new DriveForward(
    			Navigation.distanceAutoLineToEdgeofDefense
    			-(Navigation.robotLength+Navigation.robotBumperThickness), 6, 28)); //Distance, speed (ft/sec), ramp/rampdown

    	
    	//So at this point our bumper is at the edge of the defense
    	switch(defense) {
    	case AutonSelection.ROCK_WALL      : addSequential(new CrossRockWall(CrossLowBar.FORWARD)     );break;
    	case AutonSelection.ROUGH_TERRAIN  : addSequential(new CrossRoughTerrain(CrossLowBar.FORWARD) );break;
		case AutonSelection.CHEVAL_DE_FRISE: addSequential(new CrossChevalDeFrise(CrossLowBar.FORWARD));break;
		case AutonSelection.PORTICULLIS    : addSequential(new CrossPorticullis(CrossLowBar.FORWARD)  );break;
		case AutonSelection.MOAT           : addSequential(new CrossMoat(CrossLowBar.FORWARD)         );break;
		case AutonSelection.RAMPARTS       : addSequential(new CrossRamparts(CrossLowBar.FORWARD)     );break;
		case AutonSelection.SALLY_PORT     : addSequential(new CrossSallyPort(CrossLowBar.FORWARD)    );break;
		case AutonSelection.DRAWBRIDGE     : addSequential(new CrossDrawbridge(CrossLowBar.FORWARD)   );break;
        }

    	addSequential(new DriveForward(
      			 distanceToTravelIntoCourtYard, 8, 24)); //Distance, speed (ft/sec), ramp/rampdown | FROM 6 ft/sec TO  8 ft/sec
     // addSequential(new AutonArmSetpoints(AutonArmSetpoints.NEUTRAL, 0.01));// Start Arms Traveling up
      addSequential(new DriveSpin(-38));// Should be facing the low goal | original 10 then 15 now 20 RPM FASTER
      addSequential(new Wait(0.5));
      addSequential(new DriveSpin(Robot.visionHandler.getGoalOffset()));
      
      // Drive at Low goal and shoot
   	addSequential(new DriveForward(76+6-36,driveSpeed,12)); // Took 36in off of the distance because we are in 2nd position.
   	addSequential(new AutonShooterSpin(-1200,0.5));// RPM, Max time in seconds for it to come up to speed
   	addSequential(new AutonScalerReachUp(0.5));//Time to push the kicker 
   	addSequential(new DriveForward(18, 3, 1)); //Drive up to the tower to push failed shots in | ADDED
   	addSequential(new AutonScalerReachUp(1.0));//Time to push the kicker 
   	addSequential(new AutonShooterSpin(0,0.01));// Turn Off the spinners


    }
}
