// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc5053.FRC2016Stronghold.subsystems;

import java.lang.Math;
import java.util.Comparator;
import java.util.Vector;


//import org.usfirst.frc.team5053.robot.Robot.ParticleReport;
import org.usfirst.frc5053.FRC2016Stronghold.RobotMap;
import org.usfirst.frc5053.FRC2016Stronghold.commands.*;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.*;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.vision.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class VisionHandler extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	
	//Redefinition of a ParticleReport, to include the variables we want
	//The base class doesn't include all of these
	public class ParticleReport implements Comparator<ParticleReport>, Comparable<ParticleReport>{
		double PercentAreaToImageArea;
		double Area;
		double BoundingRectLeft;
		double BoundingRectTop;
		double BoundingRectRight;
		double BoundingRectBottom;
		int CenterOfMassX;
		
		public int compareTo(ParticleReport r)
		{
			return (int)(r.Area - this.Area);
		}
		
		public int compare(ParticleReport r1, ParticleReport r2)
		{
			return (int)(r1.Area - r2.Area);
		}
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//HSV Ranges for Filtering
	//(Hue/Saturation/Value)
	//Seemed to work better than color filtering because of green appearing the same as white
	//public static final int hueLowRange = 2;
	//public static final int hueHighRange = 255;
	//public static final int satLowRange = 138;
	//public static final int satHighRange = 255;
	//public static final int valLowRange = 0;
	//public static final int valHighRange = 255;
	
	//Image files, manipulated by vision processing
    //Image sourceFrame;
	//BinaryImage morphedFrame;
	
	//CameraServer camera;
//	USBCamera robotEyes;
	
	//Session ID for camera acquisition
	int session;
	
	//HSV Range Objects
	//NIVision.Range hueFilter;
	//NIVision.Range satFilter;
	//NIVision.Range valFilter;
	
	//Camera View Angle for a Microsoft Lifecam
	double VIEW_ANGLE = 52;
	
	//Particle Filter Criterias, which specify what properties to filter excess particles by
	//NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	//NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0,0,1,1);
	
	public VisionHandler ()
	{
		//Initialize Filters
		//hueFilter = new NIVision.Range(hueLowRange, hueHighRange);
		//satFilter = new NIVision.Range(satLowRange, satHighRange);
		//valFilter = new NIVision.Range(valLowRange, valHighRange);
		//RGB Image taken from Camera
		//sourceFrame = NIVision.imaqCreateImage(ImageType.IMAGE_HSL, 0);
		
		
		//Binary Image used to store the HSV filtered Image
		//morphedFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		
		//robotEyes = new USBCamera("cam0");
		//Initialize Particle Filter, in this case the only filtering characteristic is the area of the Convex Hull operation performed later
		//criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_CONVEX_HULL_AREA, 0, 600, 0,  0);
		
		
	}
	public boolean particleSort (ParticleAnalysisReport i, ParticleAnalysisReport j) {return (i.particleArea > j.particleArea);}
	
	//Function to get the angle of horizontal offset from the goal
	//The idea here is to run this once each time we want to aim at the goal
	//This way the image processing is kept to a minimum while giving the robot the precise info it needs to turn
	//
	//The robot calls this function once, uses the rotational PID from the Gyro until it gets reasonably close.
	//The robot continues to call this function and run a PID until the offset angle is below some minimum threshold to conclude it's on target
	
	public double getGoalOffset()
	{
		double percentOfHalfTheScreen = 0;
		try{
			percentOfHalfTheScreen = SmartDashboard.getNumber("Object Center 1");
    	}
		catch (NetworkTableKeyNotDefined e){e.printStackTrace();};
		return percentOfHalfTheScreen * (VIEW_ANGLE/2);
	}
	public void updateFrame()
	{
		//RGTemp session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		//RGTemp NIVision.IMAQdxStartAcquisition(session);
		//RGTemp NIVision.IMAQdxGrab(session, sourceFrame, 1);
		//RGTemp CameraServer.getInstance().setImage(sourceFrame);
		//RGTemp NIVision.IMAQdxStopAcquisition(session);
		//RGTemp NIVision.IMAQdxCloseCamera(session);
		try{
//		robotEyes.openCamera();
//		robotEyes.startCapture();
//		robotEyes.getImage(sourceFrame.image);
//		robotEyes.stopCapture();
//		robotEyes.closeCamera();
		}
		catch(VisionException e){
			System.out.println("CAMERA MISSING, PLUG IT IN !!!!!!!!!!!!!!!!!!!!!");
			e.printStackTrace();
		}
	}
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new RobotCanSee());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    	

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    /*	try{
    	    sourceFrame = new RGBImage();
    	}
    	catch(NIVisionException e){
    		e.printStackTrace();
    	}*/

    }
    
    public double getDistance () {
    	double distance = 8;
    	try {
    		distance = SmartDashboard.getNumber("Object Distance");
    	}
    	catch (NetworkTableKeyNotDefined e){e.printStackTrace();};
    	return distance;
	}
}
