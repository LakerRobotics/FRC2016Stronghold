package org.usfirst.frc5053.FRC2016Stronghold;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;

public class PIDSourceAddRate implements PIDSource {

	PIDSource m_PidSource = null;
	PIDSourceType m_PidSourceType = null;
	
	double m_Distance_LastTime = 0;
	double m_Time_LastTime = 0;

	boolean firstTime = true;
	
	PIDSourceAddRate(PIDSource pidSource){
		m_PidSource = pidSource;
	}
	
	@Override
	public PIDSourceType getPIDSourceType() {
		return m_PidSourceType;
	}

	@Override
	public double pidGet() {
		double returnValue = 0;
		// TODO Auto-generated method stub
		if(m_PidSourceType == PIDSourceType.kDisplacement){
			returnValue =  m_PidSource.pidGet();
		}
		else{// need to return speed
			if (firstTime){
				returnValue = 0;
			}else {
				double distance_CurrentTime = m_PidSource.pidGet();
				double time_CurrentTime = Timer.getFPGATimestamp();
				double changeInDistance = distance_CurrentTime - m_Distance_LastTime;
				double changeInTime = time_CurrentTime - m_Time_LastTime;
				double speed = changeInDistance/changeInTime;
				returnValue = speed;	
			}
		}
		m_Distance_LastTime = m_PidSource.pidGet();
		m_Time_LastTime = Timer.getFPGATimestamp();
		return returnValue;
	}

	@Override
	public void setPIDSourceType(PIDSourceType arg0) {
		m_PidSourceType = arg0;
	}


}
