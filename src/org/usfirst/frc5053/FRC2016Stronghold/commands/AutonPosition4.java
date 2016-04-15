package org.usfirst.frc5053.FRC2016Stronghold.commands;

import org.usfirst.frc5053.FRC2016Stronghold.Robot;
import org.usfirst.frc5053.FRC2016Stronghold.subsystems.Navigation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonPosition4 extends CommandGroup {
    public AutonPosition4(int defense, int goalHeight) {
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

    	if(goalHeight == AutonSelection.LOW_GOAL){
    		addSequential(new DriveSpin(90));
    		addSequential(new DriveForward(1*Navigation.fieldDefenseWidth,10,24));
    		addSequential(new DriveSpin(-90));
    		addSequential(new AutonRightGoal(goalHeight));
    	}

    }
}
