package org.usfirst.frc5053.FRC2016Stronghold.commands;

import org.usfirst.frc5053.FRC2016Stronghold.subsystems.Navigation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonPosition2 extends CommandGroup {
    public AutonPosition2(int defense) {
    	addSequential(new DriveForward(
    			Navigation.distanceAutoLineToEdgeofDefense
    			-(Navigation.robotLength+Navigation.robotBumperThickness), 6, 28)); //Distance, speed (ft/sec), ramp/rampdown
    	//So at this point our bumper is at the edge of the defense
    	switch(defense) {
    	case 1/*AutonSelection.ROCK_WALL*/      : addSequential(new CrossRockWall())     ;break;
    	case 2/*AutonSelection.ROUGH_TERRAIN  */: addSequential(new CrossRoughTerrain()) ;break;
		case 3/*AutonSelection.CHEVAL_DE_FRISE*/: addSequential(new CrossChevalDeFrise());break;
		case 4/*AutonSelection.PORTICULLIS    */: addSequential(new CrossPorticullis())  ;break;
		case 5/*AutonSelection.MOAT           */: addSequential(new CrossMoat()       )  ;break;
		case 6/*AutonSelection.RAMPARTS       */: addSequential(new CrossRamparts()   )  ;break;
		case 7/*AutonSelection.SALLY_PORT     */: addSequential(new CrossSallyPort()  )  ;break;
		case 8/*AutonSelection.DRAWBRIDGE     */: addSequential(new CrossDrawbridge() )  ;break;
        }

//	addSequential(new ArmSetpoints(ArmSetpoints.NEUTRAL));
//	addSequential(new DriveForward(87.5-(2*12),6*12,14));
//	addSequential(new DriveSpin(60));
//	addSequential(new DriveForward(76,6*12,14));
//	addSequential(new ShooterSpin(1));
    }
}
