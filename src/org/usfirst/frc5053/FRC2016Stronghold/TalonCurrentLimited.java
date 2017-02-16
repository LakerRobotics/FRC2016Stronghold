package org.usfirst.frc5053.FRC2016Stronghold;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TalonCurrentLimited implements SpeedController {
	double m_currentLimitForward = 0;
	double m_currentLimitReverse = 0;
	int m_powerDistributionBordSlot = 0;
	SpeedController m_speedController = null;
	public static PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public TalonCurrentLimited(SpeedController a_speedController, int powerDistributionBordSlot,
			double currentLimitForward, double currentLimitReverse ){
		m_speedController = a_speedController;
		m_currentLimitForward = currentLimitForward;
		m_currentLimitReverse = currentLimitReverse;
		m_powerDistributionBordSlot = powerDistributionBordSlot;

	}

	@Override
	public void pidWrite(double arg0) {
		m_speedController.pidWrite(arg0);
	}

	@Override
	public void disable() {
		m_speedController.disable();
	}

	@Override
	public double get() {
		return m_speedController.get();
	}

	@Override
	public boolean getInverted() {
		return m_speedController.getInverted();
	}

	/**
	 * This is where we actually do something
	 */
	@Override
	public void set(double powerIn) {
		
		double current = pdp.getCurrent(m_powerDistributionBordSlot);
		
		SmartDashboard.putNumber("Arm Current1", current);
		System.out.println("Arm Current1" + current);
		double powerOut = powerIn;
		if (current> m_currentLimitForward){
			powerOut = 0;
			
		}
		else if (current<m_currentLimitReverse){
			powerOut = 0;
		}
		System.out.println("power In:"+powerIn+" out"+powerOut+"Arm Current1" + current);
		
		m_speedController.set(powerOut);
	}

	/**
	 * This is where we actually do something
	 */
/*	@Override
	public void set(double power, byte syncGroup) {
		double current = pdp.getCurrent(m_powerDistributionBordSlot);
		SmartDashboard.putNumber("Arm Current2", current);
		System.out.println("Arm Current2" + current);
		
		if (current> m_currentLimitForward){
			power = 0;
		}
		else if (current<m_currentLimitReverse){
			power = 0;
		}
		m_speedController.set(power, syncGroup);
	}
*/
	@Override
	public void setInverted(boolean arg0) {
		m_speedController.setInverted(arg0);
		
	}

	@Override
	public void stopMotor() {
		// TODO Auto-generated method stub
		
	}

}
