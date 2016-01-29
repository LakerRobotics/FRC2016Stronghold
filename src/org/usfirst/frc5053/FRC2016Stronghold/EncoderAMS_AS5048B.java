/**
 * 
 */
package org.usfirst.frc5053.FRC2016Stronghold;

import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Encoder;

/**
 * @author Richard Topolewski
 *
 */
public class EncoderAMS_AS5048B extends Encoder {

	/**
	 * @param aChannel
	 * @param bChannel
	 */
	private EncoderAMS_AS5048B(int aChannel, int bChannel) throws Exception {
		super(aChannel, bChannel);
		throw new Exception("tha AS5048B sensor does not have an A B Channel, it is a hall effect sensor");
		
	}

	/**
	 * @param aSource
	 * @param bSource
	 */
	public EncoderAMS_AS5048B(DigitalSource aSource, DigitalSource bSource) {
		super(aSource, bSource);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param aChannel
	 * @param bChannel
	 * @param reverseDirection
	 */
	public EncoderAMS_AS5048B(int aChannel, int bChannel,
			boolean reverseDirection) {
		super(aChannel, bChannel, reverseDirection);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param aChannel
	 * @param bChannel
	 * @param indexChannel
	 */
	public EncoderAMS_AS5048B(int aChannel, int bChannel, int indexChannel) {
		super(aChannel, bChannel, indexChannel);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param aSource
	 * @param bSource
	 * @param reverseDirection
	 */
	public EncoderAMS_AS5048B(DigitalSource aSource, DigitalSource bSource,
			boolean reverseDirection) {
		super(aSource, bSource, reverseDirection);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param aSource
	 * @param bSource
	 * @param indexSource
	 */
	public EncoderAMS_AS5048B(DigitalSource aSource, DigitalSource bSource,
			DigitalSource indexSource) {
		super(aSource, bSource, indexSource);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param aChannel
	 * @param bChannel
	 * @param reverseDirection
	 * @param encodingType
	 */
	public EncoderAMS_AS5048B(int aChannel, int bChannel,
			boolean reverseDirection, EncodingType encodingType) {
		super(aChannel, bChannel, reverseDirection, encodingType);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param aChannel
	 * @param bChannel
	 * @param indexChannel
	 * @param reverseDirection
	 */
	public EncoderAMS_AS5048B(int aChannel, int bChannel, int indexChannel,
			boolean reverseDirection) {
		super(aChannel, bChannel, indexChannel, reverseDirection);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param aSource
	 * @param bSource
	 * @param reverseDirection
	 * @param encodingType
	 */
	public EncoderAMS_AS5048B(DigitalSource aSource, DigitalSource bSource,
			boolean reverseDirection, EncodingType encodingType) {
		super(aSource, bSource, reverseDirection, encodingType);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param aSource
	 * @param bSource
	 * @param indexSource
	 * @param reverseDirection
	 */
	public EncoderAMS_AS5048B(DigitalSource aSource, DigitalSource bSource,
			DigitalSource indexSource, boolean reverseDirection) {
		super(aSource, bSource, indexSource, reverseDirection);
		// TODO Auto-generated constructor stub
	}

}
