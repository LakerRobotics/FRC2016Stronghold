package org.usfirst.frc5053.FRC2016Stronghold;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.interfaces.Gyro;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Rich Topolewski
 * 
 * Used to take the speed calculated from the PID control and pass it to the drive train, 
 * and also adjust the speed going to the wheels to drive straight
 *
 */
public class RobotDriveStraightSpeedPIDStraightPIDOutput implements PIDOutput {

	// This is just a simple P control, Proportional control of the line follow
	// if we assume angle is in degrees and if we were off by 20 Degrees then we would want how much correction
	// for example id Kp is 0.025 at 20 degrees we would have 0.5 or half the power toward rotating the robot 
	double Kp = 0d/20d; //0.025;// 
	Gyro m_gyro; 
	double m_targetAngle = 0.0d;
	double rotationPower = 0.0d;

	public RobotDriveStraightSpeedPIDStraightPIDOutput(Gyro theGyro, double targetAngle) {
//	    SmartDashboard.putString("RobotDriveSpinPIDOutput", "constructor called");
		m_gyro = theGyro;
		m_targetAngle = targetAngle;
		double slowRotation = m_targetAngle + 90;
		WrapRotationPIDOutput wrappedRotationPIDOutput =  new WrapRotationPIDOutput(this);
		
//		MotionControlPIDController rotationPID = Robot.navigation.createRotationPIDController(m_targetAngle, slowRotation, wrappedRotationPIDOutput);
		
		//WrapRotationPIDInput  wrapRotationPIDInput = new WrapRotationPIDOutput(rotationPID, (PIDSource) m_gyro);
	}

	protected synchronized double getRotationPower() {
		return rotationPower;
	}


	protected synchronized void setRotationPower(double rotationPower) {
		this.rotationPower = rotationPower;
	}


	@Override
	public synchronized void pidWrite(double motorPower) {
	    //rotationPower
	   	//double rotationPower = 0;
	   	//RobotMap.driveTrainRobotDrive21.arcadeDrive(/*moveValue*/ motorPower, /*rotateValue*/ rotationPower); 
	    SmartDashboard.putNumber("RobotDriveStraightPIDOoutput Motor Output",motorPower);
	    SmartDashboard.putNumber("RobotDriveStraightPIDOoutput RotationPower", rotationPower);
    	double leftPower; 
    	double rightPower;
    	
    	// Reduce joystick power so can get full turning effect, and hopefully avoid a jerk in the rotation
    	// if quickly taken off full throttle.
    	if (motorPower + rotationPower > 1.0){
    		motorPower = 1 - rotationPower;
    	}
    	
    	leftPower = motorPower-rotationPower;
    	rightPower = motorPower+rotationPower;
    	RobotMap.driveTrainRobotDrive21.tankDrive(leftPower, rightPower);

	}
	
    private class WrapRotationPIDOutput implements PIDOutput {

        private RobotDriveStraightSpeedPIDStraightPIDOutput m_rotationPowerDestination;

        public WrapRotationPIDOutput(RobotDriveStraightSpeedPIDStraightPIDOutput rotationPowerDesintation) {
            if (rotationPowerDesintation == null) {
                throw new NullPointerException("Given rotationPowerDestination was null");
            }
            else{
                m_rotationPowerDestination = rotationPowerDesintation;            	
            }
        }

		@Override
		public void pidWrite(double rotationPower) {
			this.m_rotationPowerDestination.setRotationPower(rotationPower);
		}

    }



}

