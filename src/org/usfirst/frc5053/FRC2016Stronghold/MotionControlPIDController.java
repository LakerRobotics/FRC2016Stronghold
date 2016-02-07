package org.usfirst.frc5053.FRC2016Stronghold;

import java.util.TimerTask;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.BoundaryException;

public class MotionControlPIDController extends PIDController {
	MotionControlHelper m_motionControlHelper; 
	
	public double getRate() throws Exception{
		//create a new object that hold a method and see if the mm_source happens to have a method call getRate
		// Gyro and Encoder will have a method called getRate (otherwise we can just throw an exception because 
		// the developer didn't provide the right kind of mm_source (mainly a Gyro or Encodeer) for the 
		// motionControlPIDController to work, so we should blow up 
		
		m_motionControlHelper.getM_source().setPIDSourceType(PIDSourceType.kRate);
		return m_motionControlHelper.getM_source().pidGet();
	}


	public MotionControlPIDController(double Kp, double Ki, double Kd, 
			MotionControlHelper motionControl) {
		super(Kp, Ki, Kd, motionControl.getM_source(), motionControl.getM_output());
		m_motionControlHelper = motionControl;
		motionControl.setRegularPIDControl(this);// to let the motionControl adjust the Rate, ie do the motion control
	}
	
	public MotionControlPIDController(double Kp, double Ki, double Kd, 
			MotionControlHelper motionControl,
			double period) {
		super(Kp, Ki, Kd, motionControl.getM_source(), motionControl.getM_output(), period);
		m_motionControlHelper = motionControl;
		motionControl.setRegularPIDControl(this);// to let the motionControl adjust the Rate, ie do the motion control
	}

	public MotionControlPIDController(double Kp, double Ki, double Kd, double Kf, 
			MotionControlHelper motionControl) {
		super(Kp, Ki, Kd, Kf, motionControl.getM_source(), motionControl.getM_output());
		m_motionControlHelper = motionControl;
		motionControl.setRegularPIDControl(this);// to let the motionControl adjust the Rate, ie do the motion control
	}

	public MotionControlPIDController(double Kp, double Ki, double Kd, double Kf, 
			double period, MotionControlHelper motionControl) {
		super(Kp, Ki, Kd, Kf, motionControl.getM_source(), motionControl.getM_output(), period);
		m_motionControlHelper = motionControl; 
		motionControl.setRegularPIDControl(this);// to let the motionControl adjust the Rate, ie do the motion control
	}

	




}
