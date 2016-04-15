/**
 * 
 */
package org.usfirst.frc5053.FRC2016Stronghold;

//import edu.wpi.first.wpilibj.DigitalSource;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author Richard Topolewski
 *
 */
public class LidarDistanceSensor implements PIDSource {
    I2C I2CBus;
    int m_I2CAddress = 0;
    byte[] dataBuffer = new byte[6];
    ByteBuffer dataBufferWrapped = ByteBuffer.wrap(dataBuffer);

    
	public  LidarDistanceSensor(int I2CAddress){
		m_I2CAddress = I2CAddress;

	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double pidGet() {
//Example        I2CBus = new I2C(I2C.Port.kOnboard, 0x1E);
	    byte[] dataBuffer = new byte[6];

		m_I2CAddress = 192;
        I2CBus = new I2C(I2C.Port.kOnboard, m_I2CAddress);
//        I2CBus = new I2C(I2C.Port.kOnboard, 250);
        		
//        byte[] dataBuffer = new byte[6];
        
//        ByteBuffer compBuffer = ByteBuffer.wrap(dataBuffer);
        
       	I2CBus.read(250, 6, dataBuffer);
//       	I2CBus.read(registerAddress, count, buffer) // Byte[]
//       	I2CBus.read(registerAddress, count, ByteBuffer)
      	
    	dataBufferWrapped.order(ByteOrder.BIG_ENDIAN);
    	
    	short magneticFieldStrength = dataBuffer[0];
     	short diagonosticCodes      = dataBuffer[1];
    	
    	short angleRaw1              = dataBuffer[2];
    	short angleRaw2              = dataBuffer[3];
        short angle1                 = dataBuffer[4];
        short angle2                 = dataBuffer[5];
//     	short diagonosticCodes      = dataBufferWrapped.get();
    	
//    	short angleRaw1              = dataBufferWrapped.get();
//    	short angleRaw2              = dataBufferWrapped.get();
//        short angle1                 = dataBufferWrapped.get();
//        short angle2                 = dataBufferWrapped.get();
        
        SmartDashboard.putInt("HallEffect Rotation Sensor Field Strength", magneticFieldStrength);
        SmartDashboard.putInt("HallEffect Rotation Sensor diagonosticCodes", diagonosticCodes);
        SmartDashboard.putInt("HallEffect Rotation Sensor angleRaw", angleRaw1);
        SmartDashboard.putInt("HallEffect Rotation Sensor angleRaw", angleRaw2);
        SmartDashboard.putInt("HallEffect Rotation Sensor angle", angle1);
        SmartDashboard.putInt("HallEffect Rotation Sensor angle", angle2);
        
        System.out.println("HallEffect Rotation Sensor Field Strength="+ magneticFieldStrength);
        System.out.println("HallEffect Rotation Sensor diagonosticCodes"+ diagonosticCodes);
        System.out.println("HallEffect Rotation Sensor angleRaw"+ angleRaw1);
        System.out.println("HallEffect Rotation Sensor angleRaw"+ angleRaw2);
        System.out.println("HallEffect Rotation Sensor angle"+ angle1);
        System.out.println("HallEffect Rotation Sensor angle"+ angle2);
           	
		return angle2;
	}

	@Override
	public void setPIDSourceType(PIDSourceType arg0) {
		// TODO Auto-generated method stub
		
	}

}
