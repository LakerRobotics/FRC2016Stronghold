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
import com.ni.vision.VisionException;
import com.ni.vision.NIVision.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.vision.*;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;


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
	};

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//HSV Ranges for Filtering
	//(Hue/Saturation/Value)
	//Seemed to work better than color filtering because of green appearing the same as white
	public static final int hueLowRange = 2;
	public static final int hueHighRange = 255;
	public static final int satLowRange = 138;
	public static final int satHighRange = 255;
	public static final int valLowRange = 0;
	public static final int valHighRange = 255;
	
	//Image files, manipulated by vision processing
	Image sourceFrame;
	Image morphedFrame;
	
	CameraServer camera;
	
	//Session ID for camera acquisition
	int session;
	
	//HSV Range Objects
	NIVision.Range hueFilter;
	NIVision.Range satFilter;
	NIVision.Range valFilter;
	
	//Camera View Angle for a Microsoft Lifecam
	double VIEW_ANGLE = 52;
	
	//Particle Filter Criterias, which specify what properties to filter excess particles by
	NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0,0,1,1);
	
	public VisionHandler ()
	{
		//Initialize Filters
		hueFilter = new NIVision.Range(hueLowRange, hueHighRange);
		satFilter = new NIVision.Range(satLowRange, satHighRange);
		valFilter = new NIVision.Range(valLowRange, valHighRange);
		//RGB Image taken from Camera
		sourceFrame = NIVision.imaqCreateImage(ImageType.IMAGE_HSL, 0);
		
		//Binary Image used to store the HSV filtered Image
		morphedFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		
		//Initialize Particle Filter, in this case the only filtering characteristic is the area of the Convex Hull operation performed later
		criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_CONVEX_HULL_AREA, 0, 600, 0,  0);
		
		
		
		
		
	}
	public boolean particleSort (ParticleReport i, ParticleReport j) {return (i.Area > j.Area);}
	
	//Function to get the angle of horizontal offset from the goal
	//The idea here is to run this once each time we want to aim at the goal
	//This way the image processing is kept to a minimum while giving the robot the precise info it needs to turn
	//
	//The robot calls this function once, uses the rotational PID from the Gyro until it gets reasonably close.
	//The robot continues to call this function and run a PID until the offset angle is below some minimum threshold to conclude it's on target
	
	public double getGoalOffset()
	{
		double offset = 0;
		
		//Take a picture from the camera
		updateFrame();
		//We have now stored the most recent image into sourceFrame
		NIVision.imaqWriteBMPFile(morphedFrame, "BaseImage.bmp", 8, NIVision.RGB_BLUE);
		//Apply a color threshold
		//Hint: NIVision.something(a, b, c, d, etc.)
		NIVision.imaqColorThreshold(morphedFrame, sourceFrame, 255, NIVision.ColorMode.HSV, hueFilter, satFilter, valFilter);
		NIVision.imaqWriteBMPFile(morphedFrame, "Step1.bmp", 8, NIVision.RGB_BLUE);
		
		//Apply a morphology or two
		
		//Applying imaqClose() to finish the particles
        try{
    		NIVision.imaqMorphology(morphedFrame, morphedFrame, NIVision.MorphologyMethod.CLOSE, new NIVision.StructuringElement(3,3,3));
         }
         catch(VisionException e){
      	   System.out.println("In VisionHandler tried imaqMorphology, but got this exception:"+e);
         }
		NIVision.imaqWriteBMPFile(morphedFrame, "Step3.bmp", 8, NIVision.RGB_BLUE);
		
		//Applying the Convex Hull Operation to build full shapes
		NIVision.imaqConvexHull(morphedFrame, morphedFrame, 0);
		NIVision.imaqWriteBMPFile(morphedFrame, "Step4.bmp", 8, NIVision.RGB_BLUE);
		
		//Filter the Particles using our criteria and options set earlier
		NIVision.imaqParticleFilter4(morphedFrame, morphedFrame, criteria, filterOptions, null);
		
		
		int numParticles = NIVision.imaqCountParticles(morphedFrame, 1);
		
		//Run a particle analysis report on all remaining particles and sort them
		if (numParticles > 0)
			
		{
			Vector<ParticleReport> particles = new Vector<ParticleReport>();
			
			for (int particleIndex = 0; particleIndex < numParticles; particleIndex++)
			{
				ParticleReport par = new ParticleReport();
				par.Area = (int)NIVision.imaqMeasureParticle(morphedFrame, particleIndex, 0, NIVision.MeasurementType.MT_CONVEX_HULL_AREA);
				par.CenterOfMassX = (int)NIVision.imaqMeasureParticle(morphedFrame, particleIndex, 0, NIVision.MeasurementType.MT_CENTER_OF_MASS_X);
				par.BoundingRectLeft = NIVision.imaqMeasureParticle(morphedFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
				par.BoundingRectRight = NIVision.imaqMeasureParticle(morphedFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_RIGHT);
				
				particles.add(par);
			}
			particles.sort(null);
			
			//The particle at the top of the vector is our goal
			offset = particles.elementAt(0).CenterOfMassX;
		}
		
		//Get the Center_Of_Mass_X of the mass with the highest Y
		
		//Distance Calculation!
		//objAngle = 0.5*((*particles)[number].boundingRect.width)*(CAMERA_ANGLE/(*particles)[number].imageWidth);
		//return 1/tan(objAngle);
		
		//We found it! Now return it to the robot calling this function
		//Converts pixel center X value of the particle to a normalized % of image value, with -1 being far left and 1 being far right
		//The field of view of the camera is known, (in this case the lifecam is 52 degrees, so half that is 26 degrees)
		//This offset * half_view_angle = degrees_off_from_target (At least I'm pretty sure)
		return ((offset - 160)/160) * (VIEW_ANGLE/2);
	}
	public void updateFrame()
	{
		session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		
		NIVision.IMAQdxStartAcquisition(session);
		NIVision.IMAQdxGrab(session, sourceFrame, 1);
		CameraServer.getInstance().setImage(sourceFrame);
		NIVision.IMAQdxStopAcquisition(session);
		NIVision.IMAQdxCloseCamera(session);	
		
	}
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new RobotCanSee());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    //This was taken from the FRC Vision example, the targetWidth hasn't been modified yet
    double computeDistance (Image image, ParticleReport report) {
		double normalizedWidth, targetWidth;
		NIVision.GetImageSizeResult size;

		size = NIVision.imaqGetImageSize(image);
		normalizedWidth = 2*(report.BoundingRectRight - report.BoundingRectLeft)/size.width;
		targetWidth = 7;

		return  targetWidth/(normalizedWidth*12*Math.tan(VIEW_ANGLE*Math.PI/(180*2)));
	}
}

