package org.usfirst.frc.team6479.robot.sensors;

import edu.wpi.first.wpilibj.Encoder;

public class AdjustedEncoder extends Encoder {

	private double scalar;

	public AdjustedEncoder(int channelA, int channelB, boolean reverseDirection, EncodingType encodingType) {
		super(channelA, channelB, reverseDirection, encodingType);
		scalar = 1;
	}

	public void setScalar(double scalar) {
		this.scalar = scalar;
	}
	@Override
	public double getDistancePerPulse() {
		return super.getDistancePerPulse() * scalar;
	}
}
