package org.usfirst.frc5053.FRC2016Stronghold;

import java.lang.reflect.Method;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author richard.topolewski
 *
 */
public class MotionControlHelper {
	double m_targetDistance         = 0.0d; // in distance units for example inches or Degrees of rotation
	double m_rampUpRampDownDistance = 0.0d; // in distance units for example inches or Degrees of roation
    double m_runningSpeed           = 0.0d; // distance (e.g. inchs or Degrees of rotation) over seconds	
	
    double m_currentMeasuredDistance  = 0.0d;
    double m_initialMeasuredDistance  = 0.0d;
 
   	public double percentDeadZoneOverride = 0.50;//enter portion of 1 (e.g. .1 for 10%)
   
	PIDOutput m_output;
	PIDSource m_source;
	
	PIDController regularPIDControl;
	   	
	/**
     * This helper class just takes a target distance and will provide a motion control speed to get to that target
     * @param aTargetDistance   Where we are trying to get to
     * @param aRampUpRampDownDistance  Allows control of how fast we accelorate and decelorate
     * @param aRunningSpeed   the speed we want to travel most of the time, except for ramp up and ramp down
     * @param aInitialMeasuredDistance  so we know where we started from for the ramp up
     */
    public MotionControlHelper(double targetDistance, double rampUpRampDownDistance, double runningSpeed, 
    		            double initialMeasuredDistance,
    		            PIDSource source, PIDOutput output){
    	m_targetDistance          = targetDistance;
    	m_rampUpRampDownDistance  = Math.abs(rampUpRampDownDistance);
    	m_runningSpeed            = runningSpeed;
    	m_initialMeasuredDistance = Math.abs(initialMeasuredDistance);
    	m_output = output;
    	m_source = source;

    	}

    protected PIDController getRegularPIDControl() {
		return regularPIDControl;
	}

	protected void setRegularPIDControl(PIDController regularPIDControl) {
		this.regularPIDControl = regularPIDControl;
	}


    public PIDOutput getM_output() {
		return m_output;
	}

    /**
     * This returns the PIDSource wrapped so when called by the PIDController the motionControlHelper can
     * adjust the target rate that the PIDController is trying ot achieve
     * @return
     */
	public PIDSource getM_source() {
		return new wrapPIDInput(this, m_source);
	}

	/**
     * Given the currentPosition (i.e. distance) this will return the current target speed 
     * @param currentMeasuredDistance // the current position, in distance units for example inches or Degrees-of-rotation
     * @return
     */
    public double getTargetSpeed(double currentMeasuredDistance){
       double targetSpeed = 0.0d;       
       
       // get the motors going in the right direction
       double gapEnd = m_targetDistance-currentMeasuredDistance;
       targetSpeed = (gapEnd/Math.abs(gapEnd)) * m_runningSpeed; // This just applied +1 or -1 to get the sign right
       
       // Calculate the reduction to the speed if at the start
       double percentRampUp;
       double gapStart = currentMeasuredDistance-m_initialMeasuredDistance;
       if( Math.abs(gapStart) > m_rampUpRampDownDistance){
    	   // We are outside of the rampUp zone 
    	   percentRampUp = 1; //100%
       }
       else{
    	   // Are we right on top of the start point, if so, don't set motor to zero but some minimum number to get things to move
           if( Math.abs(gapStart) < m_rampUpRampDownDistance*(percentDeadZoneOverride)){
    	      percentRampUp = percentDeadZoneOverride ; //just to make sure it does not stay stuck at the start 
           }
           else{
               percentRampUp = Math.abs(gapStart)/m_rampUpRampDownDistance;
           }
       }
       
       // Calculate reduction to the speed if we are at the end
       double percentRampDown = Math.abs(gapEnd)/m_rampUpRampDownDistance;
       if (Math.abs(percentRampDown)>1)  percentRampDown = 1; // limit percent to 100%

      //Apply any speed reductions based on rampUp or rampDown.
       System.out.println("fromStart="+gapStart+"("+percentRampUp+")   fromEnd="+gapEnd+"("+percentRampDown+")");
       if(Math.abs(gapStart)<Math.abs(gapEnd)){
    	   targetSpeed = percentRampUp * targetSpeed;
       }
       else{
    	   targetSpeed = percentRampDown * targetSpeed;
       }
       SmartDashboard.putNumber("targetSpeed",targetSpeed);
       
       return targetSpeed;
    }
    
	/**
	 * The PIDSource is expected to have a setPIDSourceParameter() method on it, otherwise we can not ensure it is
	 * returning rate to the PIDController
	 * @throws Exception
	 */
	public void ensureSourceProvidesRate() throws Exception{
		
		java.lang.reflect.Method methodSetSourceParm;
		try {
//			System.out.println ("Source object class="+m_source.getClass().getName());
//			Method[] methods = m_source.getClass().getDeclaredMethods();
			
//			for(int i=0; i<methods.length; i++) {
//				System.out.println ("Method="+methods[i].getName());
//			}                                                    
			//TODO need to parameterTypes to the getMethod Call;
			methodSetSourceParm = m_source.getClass().getMethod("setPIDSourceType");
			//                                        new Gyro().setPIDSourceParameter(pidSource);
		}
		catch (SecurityException e) {
	      throw new Exception("failure in trying got find setsetPIDSourceType() method on the PIDSource Object provided, maybe if exist is is private, but needs to be publice for MotionCotrollerPIDController requires", e);
		} 
		catch (NoSuchMethodException e) {
			throw new Exception("failure in trying got find setPIDSourceType() method on the PIDSource Object provided, apparently one does not exist, as MotionCotrollerPIDController requires", e);
		}
		
		// do the real work, set the PIDSource to return rate
		try {
			methodSetSourceParm.invoke(m_source, PIDSourceType.kRate);
			return;
		} 
		catch (IllegalArgumentException e) {
			throw new Exception("failure in trying got run setPIDSourceType() method on the PIDSource Object provided", e);
		} 
		catch (IllegalAccessException e) {
			throw new Exception("failure in trying got run setPIDSourceParameter() method on the PIDSource Object provided", e);
		}
	}
    
    /**
     * Read the input(i.e. position) and calculate the speed for this position and put that in as the setPoint
     */
    protected void adjustTargetSpeed() throws Exception {
    	//using the current measurement get the desired rate (i.e. speed)
    	double targetSpeed = getTargetSpeed(this.getMeasurment());
    	this.getRegularPIDControl().setSetpoint(targetSpeed);
    	SmartDashboard.putDouble("Gyro Target Rate", targetSpeed);
    	
//    	ensureSourceProvidesRate();
    	SmartDashboard.putDouble("Gyro Rate", m_source.pidGet());
    	// now that we have the speed set properly lets call the PID control and have it adjust the PIDInput (e.g. the motor power) to get closer to the desired speed.
    	//TODO need to access the inner class PIDTask and override to call calculatesSetup then then calculate()
       	//super.calculate();
    }

	public double getMeasurment() throws Exception{
		if (m_source instanceof GyroBase){
			GyroBase gyro = (GyroBase) m_source;
			return gyro.getAngle();
		}
		else if (m_source instanceof Encoder){
			Encoder encoder = (Encoder) m_source;
			return encoder.getDistance();
		}
		else{
			//create a new object that hold a method and see if the mm_source happens to have a method call getRate
			// Gyro and Encoder will have a method called getRate (otherwise we can just throw an exception because 
			// the developer didn't provide the right kind of mm_source (mainly a Gyro or Encoder) for the 
			// motionControlPIDController to work, so we should blow up 
			try{
				java.lang.reflect.Method methodGetMeas;
				try {
					methodGetMeas = m_source.getClass().getMethod("getMeasurement");
				}
				catch (SecurityException e) {
			      throw new Exception("failure in trying got find getMeasurement() method on the PIDSource Object provided, maybe if exist is is private, but needs to be publice for MotionCotrollerPIDController requires", e);
				} 
				catch (NoSuchMethodException e) {
					throw new Exception("failure in trying got find getMeasurement() method on the PIDSource Object provided, apparently one does not exist, as MotionCotrollerPIDController requires", e);
				}
				
				try {
					return (double) methodGetMeas.invoke(m_source);
				} 
				catch (IllegalArgumentException e) {
					throw new Exception("failure in trying got run getMeasurement() method on the PIDSource Object provided", e);
				} 
				catch (IllegalAccessException e) {
					throw new Exception("failure in trying got run getMeasurment() method on the PIDSource Object provided", e);
				}
			}
			catch(Exception e){
				throw new Exception("Source was not Encoder or Gyro or a PIDSource with a method getMeasurement()", e);
			}
		}
	}	

    
    private class wrapPIDInput implements PIDSource {

        private MotionControlHelper m_MCHelper;
        private PIDSource m_source; 

        public wrapPIDInput(MotionControlHelper motionControlHelper, PIDSource source) {
            if (motionControlHelper == null) {
                throw new NullPointerException("Given MotionControlPIDController was null");
            }
            m_MCHelper = motionControlHelper;
            if (source == null){
                throw new NullPointerException("Given PIDSource was null");
            }
            m_source = source;
        }
        
		@Override
        public double pidGet(){
        	// have the controller set the target speed,
        	//TODO redo the PIDController so the calculate() method is protected so we wouldn't have to do this hack 
			//  if it were protected then we could override calculate() method and allow the target speed to be set ahead of calculation the new PID output
			try{
				m_MCHelper.adjustTargetSpeed();
			}
			catch (Exception e){
				System.out.println("MotionControl PIDSource BAD, likley not Gyro or Encoder or missing getMeasurement()");
				System.out.println(e);
			}
			// call the real PIDSource
        	return m_source.pidGet();
        }

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return null;
		}

    }
    
    
    //Time
}
