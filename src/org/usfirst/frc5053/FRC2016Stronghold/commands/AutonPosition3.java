package org.usfirst.frc5053.FRC2016Stronghold.commands;

import org.usfirst.frc5053.FRC2016Stronghold.Robot;
import org.usfirst.frc5053.FRC2016Stronghold.subsystems.Navigation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonPosition3 extends CommandGroup {
    public AutonPosition3(int defense) {
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

//	addSequential(new ArmSetpoints(ArmSetpoints.NEUTRAL));
//	addSequential(new DriveForward(87.5-(2*12),6*12,14));
//	addSequential(new DriveSpin(60));
//	addSequential(new DriveForward(76,6*12,14));
//	addSequential(new ShooterSpin(1));
    	addSequential(new DriveForward(
   			 distanceToTravelIntoCourtYard, 8, 24)); //Distance, speed (ft/sec), ramp/rampdown | FROM 6 ft/sec TO  8 ft/sec
  // addSequential(new AutonArmSetpoints(AutonArmSetpoints.NEUTRAL, 0.01));// Start Arms Traveling up

    }
}
