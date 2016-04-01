// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc5053.FRC2016Stronghold.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5053.FRC2016Stronghold.Robot;
import org.usfirst.frc5053.FRC2016Stronghold.RobotMap;

/**
 *
 */
public class AutonArmSetpoints extends Command {
	public static final double SCALING        = 0.209;
	public static final double NEUTRAL        = 0.209;
	public static final double AUTON_POSITION = 0.300;
	public static final double GET_BALL       = 0.327;
	public static final double FULL_DOWN      = 0.340;
	
	

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private double m_setpoint;
    private double m_timeDelay;
    private double m_P_temp;
    private double m_I_temp;
    private double m_D_temp;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public AutonArmSetpoints(double setpoint,double timeDelay) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    	m_timeDelay = timeDelay;
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        m_setpoint = setpoint;

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.arm);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=INITIALIZE
        Robot.arm.enable();
        Robot.arm.setSetpoint(m_setpoint);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=INITIALIZE
        
        m_P_temp = Robot.arm.getPIDController().getP();
        m_I_temp = Robot.arm.getPIDController().getI();
        m_D_temp = Robot.arm.getPIDController().getD();
        
        Robot.arm.getPIDController().setPID(30.0, 0.3, 0);
        

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
        SmartDashboard.putNumber("Target Setpoint", m_setpoint);
        SmartDashboard.putNumber("Arm_String_Pot_Reading", RobotMap.armArmStringPot.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(this.timeSinceInitialized() > m_timeDelay) {
            return true;
          }
          else {
         	 return false;
          }
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=ISFINISHED
    }

    // Called once after isFinished returns true
    protected void end() {
       	//Robot.arm.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//Restore old values
        Robot.arm.getPIDController().setPID(m_P_temp, m_I_temp, m_D_temp);

        
    	// Turn off PID control Don't touch motor, so it will hold it the last position
    	//Robot.arm.disable();
    }
}
