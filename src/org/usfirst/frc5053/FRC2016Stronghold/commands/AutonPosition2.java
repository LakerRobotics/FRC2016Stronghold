package org.usfirst.frc5053.FRC2016Stronghold.commands;

import org.usfirst.frc5053.FRC2016Stronghold.Robot;
import org.usfirst.frc5053.FRC2016Stronghold.subsystems.Navigation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonPosition2 extends CommandGroup {
    public AutonPosition2(int defense, int goalHeight, int goalSide) {
       	double driveSpeed = 4; 
    	double distanceToDefense =   Navigation.distanceAutoLineToEdgeofDefense
    			                   -(Navigation.robotLength+Navigation.robotBumperThickness);

    	double distanceToTravelAcrossLowBar =   Navigation.defenseApron
                                              + Navigation.defenseSwapSection
                                              + Navigation.defenseApron
                                              + Navigation.robotLength;
    	double distanceToTravelIntoCourtYard=12.5*12 -3*12+ 2*12-3*12; //  the 2 feet is to go further into the Court Yard because we are 4 ft closer to the tower 
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
//Temp    	addSequential(new DriveForward(
    	//Temp    	   			 distanceToTravelIntoCourtYard, 8, 24)); //Distance, speed (ft/sec), ramp/rampdown | FROM 6 ft/sec TO  8 ft/sec
  // addSequential(new AutonArmSetpoints(AutonArmSetpoints.NEUTRAL, 0.01));// Start Arms Traveling up
    	//Temp   addSequential(new DriveSpin(38));// Should be facing the low goal | original 10 then 15 now 20 RPM FASTER
    	//Temp   addSequential(new Wait(0.5));
    	//Temp   addSequential(new DriveSpin(Robot.visionHandler.getGoalOffset()));
   
   // Drive at Low goal and shoot
    	//Temp	addSequential(new DriveForward(76+6-36,driveSpeed,12)); // Took 36in off of the distance because we are in 2nd position.
    	//Temp	addSequential(new AutonShooterSpin(-1200,0.5));// RPM, Max time in seconds for it to come up to speed
    	//Temp	addSequential(new AutonScalerReachUp(0.5));//Time to push the kicker 
    	//Temp	addSequential(new DriveForward(18, 3, 1)); //Drive up to the tower to push failed shots in | ADDED
    	//Temp	addSequential(new AutonScalerReachUp(1.0));//Time to push the kicker 
    	//Temp	addSequential(new AutonShooterSpin(0,0.01));// Turn Off the spinners
    	if(goalSide == AutonSelection.LEFT_GOAL) {
    		addSequential(new AutonLeftGoal(goalHeight));
    	}
    	else if(goalSide == AutonSelection.RIGHT_GOAL){
    		addSequential(new DriveSpin(90));
    		addSequential(new DriveForward(3*Navigation.fieldDefenseWidth,10,24));
    		addSequential(new DriveSpin(-90));
    		addSequential(new AutonRightGoal(goalHeight));
    	}
    	else if(goalSide == AutonSelection.CENTER_GOAL){
    		addSequential(new DriveSpin(90));
    		addSequential(new DriveForward(1.5*Navigation.fieldDefenseWidth,10,24));
    		addSequential(new DriveSpin(-90));
    		addSequential(new AutonCenterGoal(goalHeight));
    	}
    }
}
