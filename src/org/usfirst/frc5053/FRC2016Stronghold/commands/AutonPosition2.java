package org.usfirst.frc5053.FRC2016Stronghold.commands;

import org.usfirst.frc5053.FRC2016Stronghold.subsystems.Navigation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonPosition2 extends CommandGroup {
    public AutonPosition2() {
    	addSequential(new DriveForward(
    			Navigation.distanceAutoLineToEdgeofDefense
    			-(Navigation.robotLength+Navigation.robotBumperThickness), 6, 28)); //Distance, speed (ft/sec), ramp/rampdown
    	//So at this point our bumper is at the edge of the defense 
    	String DefenseType = SmartDashboard.getString("Defense 2");
        if(DefenseType.equals("Rock Wall")) {addSequential(new CrossRockWall());}
        if(DefenseType.equals("Rough Terrain")) {addSequential(new CrossRoughTerrain());}
        if(DefenseType.equals("Chilli Fries")) {addSequential(new CrossChevalDeFrise());}
        if(DefenseType.equals("Porticullis")) {addSequential(new CrossPorticullis());}
        if(DefenseType.equals("Moat")) {addSequential(new CrossMoat());}
        if(DefenseType.equals("Ramparts")) {addSequential(new CrossRamparts());}
        if(DefenseType.equals("Sally Port")) {addSequential(new CrossSallyPort());}
        if(DefenseType.equals("Drawbridge")) {addSequential(new CrossDrawbridge());}
        
        }

//	addSequential(new ArmSetpoints(ArmSetpoints.NEUTRAL));
//	addSequential(new DriveForward(87.5-(2*12),6*12,14));
//	addSequential(new DriveSpin(60));
//	addSequential(new DriveForward(76,6*12,14));
//	addSequential(new ShooterSpin(1));
    }

