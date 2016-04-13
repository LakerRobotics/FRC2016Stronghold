package org.usfirst.frc5053.FRC2016Stronghold;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class ImuTilt implements PIDSource {

	PIDSourceType m_pidSourceType = PIDSourceType.kDisplacement;
	@Override
	public PIDSourceType getPIDSourceType() {
		return m_pidSourceType;
	}

	@Override
	public double pidGet() {
		if(m_pidSourceType == PIDSourceType.kRate){
			return RobotMap.IMU.getRateX();
			//TODO make sure this returns angular Rate (not rate of acceleration in the X direction
		}else{
			return RobotMap.IMU.getAngleX();	
//			return RobotMap.IMU.getPitch();
		}
	}

	@Override
	public void setPIDSourceType(PIDSourceType displacementOrRate) {
		if(displacementOrRate == PIDSourceType.kRate) System.out.println("ImuTilt not sure if can not do Rate, please check");
		 m_pidSourceType = displacementOrRate ;
	}

}
