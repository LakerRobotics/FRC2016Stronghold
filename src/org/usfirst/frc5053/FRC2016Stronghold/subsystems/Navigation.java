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

import org.usfirst.frc5053.FRC2016Stronghold.MotionControlHelper;
import org.usfirst.frc5053.FRC2016Stronghold.MotionControlPIDController;
import org.usfirst.frc5053.FRC2016Stronghold.RobotDriveSpinPIDOutput;
import org.usfirst.frc5053.FRC2016Stronghold.RobotMap;
import org.usfirst.frc5053.FRC2016Stronghold.commands.*;
import java.util.Date;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
//import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**0
 *
 */
public class Navigation extends Subsystem {

	private double x, y, angle;
	private double xSpeed, ySpeed, angleSpeed;
	private double angleGyroToField = 90;
	private Gyro m_gyroToField = null;
	public static double robotBumperThickness = 2.5+0.75;
	public static double robotLength = 32 + 2*robotBumperThickness;
	public static double robotWidth  = 28 + 2*robotBumperThickness;
	public static double fieldDefenseWidth = 48.25;
	public static double fieldLength = 54*12+1;
	public static double fieldWidth = 26*12+7;
	public static double autonLine = 12+1+fieldLength/2;
	public static double sensorSpacingLeftSide = robotLength -2*robotBumperThickness -2 -2;
	public static double sensorSpacingRightSide = sensorSpacingLeftSide;
	public static double sensorSpacingRear = robotWidth -2*robotBumperThickness -1 -1;
	public static double sensorSpacingFront = robotWidth -2*robotBumperThickness -1 -1;
	
	

	/**
	 * 
	 */
	public Trajectory getTrajectory(){
		return new Trajectory(x, y, angle, xSpeed, ySpeed, angleSpeed);
		
	};
	
	
	public void autonomousInit(){
		
		
		m_gyroToField = new GyroFieldOriented(RobotMap.gyroToUse,angleGyroToField);
		m_gyroToField.reset(); // should provide that the robot is facing downfield and gyro returns 90
		
		int startPosition = SmartDashboard.getInt("Start Position");
		switch (startPosition){
			case 1: 
				x = 0.5*fieldDefenseWidth; y= autonLine + robotLength/2 -robotBumperThickness; 
				break;
			case 2: 
				x = 1.5*fieldDefenseWidth; y= autonLine + robotLength/2 -robotBumperThickness; 
				break;
			case 3: 
				x = 2.5*fieldDefenseWidth; y= autonLine + robotLength/2 -robotBumperThickness; 
				break;
			case 4: 
				x = 3.5*fieldDefenseWidth; y= autonLine + robotLength/2 -robotBumperThickness; 
				break;
			case 5: 
				x = 4.5*fieldDefenseWidth; y= autonLine + robotLength/2 -robotBumperThickness; 
				break;
		}
		
	};
	
	public final static String partOfField(double x, double y) {
		//Their Courtyard
		if (y>458.5){// 650 is the end of the field, but if past that we just extend the field to infinity to avoid having an undefined condition (i.e. an error)
			if (x<=265.5){
				return "Their Courtyard";
				}
			else if (x<=319){
				return "Their Secret Passage";
			}
		}
		if ( y > 410.5 ){ // and y<=287.5 because otherwise it would not have gotten here
			if (x<=53.1) {
				return "Their D1";
			}
				else if (x<=106.2){
					return "Their D2";
			}
				else if (x<=159.3){
					return "Their D3";
			}
				else if (x<=212.4){
					return "Their D4";
			}
				else if (x<=265.5){
					return "Their D5";
			}
				else if (x<=319){
					return "Their Secret Passage";
			}
	}
			//Their Neutral Zone
		if (y>362.5){
			if (x<=265.5){
				return "Their Neutral";
			}
			else if (x<=319){
				return "Their Secret Passage";
			}
			//Neutral
	if (y>287.5){ // and y<=287.5 because otherwise it would not have gotten here
		return "Neutral";
	}
		};
	//Our Neutral
	if (y>239.5){ // and y<=287.5 because otherwise it would not have gotten here
		if (x<=53.1){
			return "Our Secret Passage";
		}
		else if (x<=319){
			return "Our Neutral";
		}
		//Our Defenses
	}
		if (y>191.5){ // and y<=239.5 because otherwise it would not have gotten here
			if (x<=53.1) {
				return "Our D1";
			}
				else if (x<=106.2){
					return "Our D2";
			}
				else if (x<=159.3){
					return "Our D3";
			}
				else if (x<=212.4){
					return "Our D4";
			}
				else if (x<=265.5){
					return "Our D5";
				}
				else if (x<=319){
					return "Our Secret Passage";
				}
		}
		if (y>0){ // and y<=191.5 because otherwise it would not have gotten here
			if (x<=53.1){
				return "Our Secret Passage";
			}
			else if (x<=310){
				return "Our Courtyard";
			}
		}
		return "Error";
	}

	public MotionControlPIDController createRotationPIDController(double targetAngle, double start, PIDOutput pidOutput) {
		
	    double     ramp =  30; //degrees
	    double maxspeed = 10.0*(360/60) ; //60/360 converts the first numbers which is in RPM to degrees/second
		
		final double Kp = 1d/200; // so at denominator off in the spin-Rate the power will reach the max
	    final double Ki = 0.0000;
	    final double Kd = 0.0;
	 
	    MotionControlPIDController localRotationSpeedPID;

	    MotionControlHelper rotationSpeedProfile; 
        rotationSpeedProfile = new MotionControlHelper(targetAngle, ramp, maxspeed, start, (PIDSource) RobotMap.gyroToUse, pidOutput);
        localRotationSpeedPID = new MotionControlPIDController(Kp,Ki,Kd, rotationSpeedProfile );
        localRotationSpeedPID.setOutputRange(-1.0, 1.0);
        localRotationSpeedPID.enable();
	    return localRotationSpeedPID;
	}	
	
	public Gyro getGyro(){
		return m_gyroToField;
	}
	
	public double fieldAngleToGyroAngle(double fieldAngle){
		double realGyroAngle = m_gyroToField.getAngle();
		int rotations = (int)(realGyroAngle/360);
		return rotations*360+fieldAngle;
	};

	/**
	 * This returns the angle with:
	 *   0 being across the field to the right
	 *   90 pointing downfield
	 *   180 pointing to the left
	 *   270 pointing toward our driver station
	 *   
	 *   It is expected this Gyro object will be enhanced to provide angle based on two distance sensors
	 *   
	 * @author richard.topolewski
	 *
	 */
	private class GyroFieldOriented implements Gyro{
		double m_angleGyroToField = 90;
		Gyro m_gyroReal;
		

		public GyroFieldOriented(Gyro gyroReal, double angleGyroToField){
			m_gyroReal = gyroReal;
			m_angleGyroToField = angleGyroToField;
		}
		
		
		@Override
		public void calibrate() {
			m_gyroReal.calibrate();
			return;
		}

		@Override
		public void free() {
			m_gyroReal.free();
			return;
		}

		@Override
		public double getAngle() {
			double realGyroAngle = m_gyroReal.getAngle();
			// adjust if it has spun 360 degrees
			
			return realGyroAngle+m_angleGyroToField;
			
		}

		@Override
		public double getRate() {
			return m_gyroReal.getRate();
		}

		/**
		 * THis should be called when you know you are pointing down field (i.e. 90 Degrees)
		 */
		@Override
		public void reset() {
			m_angleGyroToField = 90 - m_gyroReal.getAngle();
			System.out.println("gyroFieldOrienation.reset CALLED m_angleGyroToField set to "+m_angleGyroToField );
		}
		
	}
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final AnalogGyro oldAnalogGyro = RobotMap.navigationoldAnalogGyro;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new AllwaysTrackLocation());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }



public static void main(String args[]) {
}
}
