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

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5053.FRC2016Stronghold.Robot;
import org.usfirst.frc5053.FRC2016Stronghold.RobotMap;

/**
 *
 */
public class AllwaysTrackLocation extends Command {
	double x,y;
	   
	NetworkTable table;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public AllwaysTrackLocation() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.navigation);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//table = NetworkTable.getTable("datatable");
    	//table.putDouble("Right Encoder",RobotMap.driveTrainRightWheelEncoder.get());
    	//table.putDouble("Left Encoder",RobotMap.driveTrainLeftWheelEncoder.get());
    	//table.putDouble("SPI Gyro", RobotMap.SpiGyro.pidGet());
    	SmartDashboard.putNumber("Arm Potentiometer Reading", RobotMap.armArmStringPot.get());
    	SmartDashboard.putDouble("Right Encoder", RobotMap.driveTrainRightWheelEncoder.get());
    	SmartDashboard.putDouble("Left Encoder", RobotMap.driveTrainLeftWheelEncoder.get());
    	SmartDashboard.putDouble("SPI Gyro", RobotMap.SpiGyro.pidGet());
    	//table.putDouble("Right LIDAR", RobotMap.)
    	//table.putDouble("Left LIDAR", RobotMap.)
    	getDataFromArduino();
    	
    	RobotMap.driveTrainLeftWheelEncoder.setPIDSourceType(PIDSourceType.kRate);
    	double rightSpeed = RobotMap.driveTrainLeftWheelEncoder.get(); 
    	RobotMap.driveTrainRightWheelEncoder.setPIDSourceType(PIDSourceType.kRate);
    	double leftSpeed  = RobotMap.driveTrainLeftWheelEncoder.get();
    	        
    	double robotSpeed = (leftSpeed+rightSpeed)/2;
    	
    	double distance = robotSpeed *.002;
    	        
    	double currentAngle = RobotMap.gyroToUse.getAngle();
    	        // Assume 20 milli seconds
    	x = x + distance*Math.cos(2*Math.PI*currentAngle/360);
    	y = y + distance*Math.sin(2*Math.PI*currentAngle/360); 
    	SmartDashboard.putDouble("x", x);
    	SmartDashboard.putDouble("y", y);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private void getDataFromArduino(){
    byte    LIDARLite_ADDRESS = 0x62;         // Default I2C Address of LIDAR-Lite.
    byte   RegisterMeasure   = 0x00;          // Register to write to initiate ranging.
    byte    MeasureValue      = 0x04;          // Value to initiate ranging.
//    byte	RegisterHighLowB  = 0x8f;          // Register to get both High and Low bytes in 1 call.
    byte    I2CMultiplexer    = 0x70;          // PCA9544A Multiplexer Address
    
    	/*
#include <Wire.h>
#include <I2C.h>
#define    LIDARLite_ADDRESS     0x62          // Default I2C Address of LIDAR-Lite.
#define    RegisterMeasure       0x00          // Register to write to initiate ranging.
#define    MeasureValue          0x04          // Value to initiate ranging.
#define    RegisterHighLowB      0x8f          // Register to get both High and Low bytes in 1 call.
#define    I2CMultiplexer        0x70          // PCA9544A Multiplexer Address
*/
     I2C i2c = new I2C(I2C.Port.kOnboard, LIDARLite_ADDRESS+128);// port, address 
     
     /*Initialize the I2C connection on address 84. Because of differences between the implementation of the library for the cRIO and Arduino 
      * (the lower bit of the address selects either read or write) the cRIO uses address 168. 168 is the address 84 shifted by 1 bit (the read/write bit). 
      * from https://wpilib.screenstepslive.com/s/3120/m/7912/l/175524-sending-data-from-the-crio-to-an-arduino*/

     /*void setup() {
   Wire.begin();        // join i2c bus (address optional for master)
   Serial.begin(9600);  // start serial for output
   delay(100); // Waits to make sure everything is powered up before sending or receiving data  
   I2c.timeOut(50); // Sets a timeout to ensure no locking up of sketch if I2C communication fails
}
*/

     
/*
void loop() {
   Wire.requestFrom(8, 2);    // request 6 bytes from slave device #8
   */

     /*

   while (Wire.available()) { // slave may send less than requested
     long c = Wire.read(); // receive a byte as character
     Serial.println(c);         // print the character
     
     selectMultiplexerChannel(0); //Select Multiplexer Channel 0
     Serial.println(readDistance()); // Read Distance from Sensor at Channel 0
     //Serial.print("."); // Print "." to separate readings
     //selectMultiplexerChannel(1); //Select Multiplexer Channel 1
     //Serial.print(readDistance()); // Read Distance from Sensor at Channel 1
     //Serial.println(".");  // Print "." to separate readings
   }

   delay(10);
}
*/
   
   /*


void selectMultiplexerChannel(byte channel){
  byte controlRegister = 0x04; // The Control register of the Multiplexer
  controlRegister |= channel; // Bitwise OR controlRegister & channel
  uint8_t nackack = 100; // Setup variable to hold ACK/NACK resopnses     
  while (nackack != 0){ // While NACK keep going (i.e. continue polling until sucess message (ACK) is received )
    nackack = I2c.write((uint8_t)I2CMultiplexer, (uint8_t)controlRegister); // Write 0x04 to 0x00
    delay(1); // Wait 1 ms to prevent overpolling
  }
}
*/

/*
int readDistance(){
  uint8_t nackack = 100; // Setup variable to hold ACK/NACK resopnses     
  while (nackack != 0){ // While NACK keep going (i.e. continue polling until sucess message (ACK) is received )
    nackack = I2c.write(LIDARLite_ADDRESS,RegisterMeasure, MeasureValue); // Write 0x04 to 0x00
    delay(1); // Wait 1 ms to prevent overpolling
  }

  byte distanceArray[2]; // array to store distance bytes from read function
  
  // Read 2byte distance from register 0x8f
  nackack = 100; // Setup variable to hold ACK/NACK resopnses     
  while (nackack != 0){ // While NACK keep going (i.e. continue polling until sucess message (ACK) is received )
    nackack = I2c.read(LIDARLite_ADDRESS,RegisterHighLowB, 2, distanceArray); // Read 2 Bytes from LIDAR-Lite Address and store in array
    delay(1); // Wait 1 ms to prevent overpolling
  }
  */
 //i2c.transaction(byte[] dataToSend, int sendSize, byte[] dataReceived, int receiveSize)   
   byte[] dataToSend = new byte[1];
   dataToSend[0] = MeasureValue;
   int sendSize = 1;
   byte[] dataReceived  = new byte[2]; 
   int recieveSize = 2;
i2c.transaction(dataToSend, sendSize, dataReceived, recieveSize);

System.out.println("dataReceived from I2C"+dataReceived+" size="+recieveSize);
SmartDashboard.putString("dataReceived from I2C",dataReceived+" size="+recieveSize);
/*
  int distance = (distanceArray[0] << 8) + distanceArray[1];  // Shift high byte [0] 8 to the left and add low byte [1] to create 16-bit int
  
  return distance;   // Print Sensor Name & Distance
 */  
//}

   
// Example WPI I2C code
////////////////////////////////////////////     
/*
     package org.usfirst.frc.team228.robot;


     import java.nio.ByteBuffer;
     import java.nio.ByteOrder;

     import edu.wpi.first.wpilibj.I2C;
     import edu.wpi.first.wpilibj.SampleRobot;
     import edu.wpi.first.wpilibj.RobotDrive;
     import edu.wpi.first.wpilibj.Joystick;
     import edu.wpi.first.wpilibj.Timer;
     import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

     public class Robot extends SampleRobot {
        // RobotDrive Drive;
        // Joystick stick;
         I2C I2CBus;
         
         short cX = 0, cY = 0, cZ = 0;

         byte[] dataBuffer = new byte[6];
         ByteBuffer compBuffer = ByteBuffer.wrap(dataBuffer);
         
         
      //   FieldCentricController FCC();
         
         
         public Robot() {
         	
             I2CBus = new I2C(I2C.Port.kOnboard, 0x1E);
            // Drive = new RobotDrive(0, 1);
            // Drive.setExpiration(0.1);
            // stick = new Joystick(0);
            
         }


         //
         // Runs the motors with arcade steering.
         //
         public void operatorControl() {
           //  Drive.setSafetyEnabled(true);
         		I2CBus.write(0x02, 0x00);
         	
             while (isOperatorControl() && isEnabled()) {
             	I2CBus.read(0x03, 6, dataBuffer);
             	compBuffer.order(ByteOrder.BIG_ENDIAN);
             	cX = compBuffer.getShort();
             	cY = compBuffer.getShort();
             	cZ = compBuffer.getShort();
             	SmartDashboard.putNumber("CompX", cX);
             	SmartDashboard.putNumber("CompY", cY);
             	SmartDashboard.putNumber("CompZ", cZ);
           //    Drive.mecanumDrive_Cartesian(stick.getX(), stick.getY(), stick.getTwist(), 0.0);
                Timer.delay(0.065);		// wait for a motor update time
             }
         }

     }*/

    }
}
