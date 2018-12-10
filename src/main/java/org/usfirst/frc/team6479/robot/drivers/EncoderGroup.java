package org.usfirst.frc.team6479.robot.drivers;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

//class to control two encoders so that they can be averaged nicely
public class EncoderGroup extends SendableBase {
	private Encoder left;
	private Encoder right;
	private int encodingScale;

	public EncoderGroup(Encoder left, Encoder right) {
		this.left = left;
		this.right = right;
		if (left.getEncodingScale() == right.getEncodingScale()) {
			encodingScale = left.getEncodingScale();
		}
		else {
			DriverStation.reportError("Encoders passed to EncoderGroup must have the same encoding scale", true);
		}
	}

	public Encoder getLeft() {
		return left;
	}

	public Encoder getRight() {
		return right;
	}

	/**
	 * Gets the average current count. Returns the average current count on the Encoders. This method compensates for
	 * the decoding type.
	 *
	 * @return Current average count from the Encoders adjusted for the 1x, 2x, or 4x scale factor.
	 */
	public int get() {
		return (left.get() + right.get()) / 2;
	}

	/**
	 * Gets the average raw value from the encoders. The raw value is the actual count unscaled by the 1x, 2x,
	 * or 4x scale factor.
	 *
	 * @return Current average raw count from the encoders
	 */
	public int getRaw() {
		return (left.getRaw() + right.getRaw()) / 2;
	}

	/**
	 * Get the average distance the robot has driven since the last reset as scaled by the value from {@link
	 * #setDistancePerPulse(double)}.
	 *
	 * @return The distance driven since the last reset
	 */
	public double getDistance() {
		return (left.getDistance() + right.getDistance()) / 2;
	}

	/**
	 * Get the average distance per pulse for this encoder.
	 *
	 * @return The scale factor that will be used to convert pulses to useful units.
	 */
	public double getDistancePerPulse() {
		return (left.getDistancePerPulse() + right.getDistancePerPulse()) / 2;
	}

	/**
	 * Get the current average rate of the encoder. Units are distance per second as scaled by the value from
	 * {@link#setDistancePerPulse(double)}.
	 *
	 * @return The current rate of the encoder.
	 */
	public double getRate() {
		return (left.getRate() + right.getRate()) / 2;
	}

	/**
	 * Get the average Samples to Average which specifies the number of samples of the timer to average when
	 * calculating the period. Perform averaging to account for mechanical imperfections or as
	 * oversampling to increase resolution.
	 *
	 * @return SamplesToAverage The number of samples being averaged (from 1 to 127)
	 */
	public int getSamplesToAverage() {
		return (left.getSamplesToAverage() + right.getSamplesToAverage()) / 2;
	}

	public boolean getStopped() {
		return left.getStopped() && right.getStopped();
	}


	//setter methods set to both
	public void setDistancePerPulse(double distance) {
		left.setDistancePerPulse(distance);
		right.setDistancePerPulse(distance);
	}

	public void setSamplesToAverage(int samplesToAverage) {
		left.setSamplesToAverage(samplesToAverage);
		right.setSamplesToAverage(samplesToAverage);
	}

	public void setMinRate(double minRate) {
		left.setMinRate(minRate);
		right.setMinRate(minRate);
	}

	public void setMaxPeriod(double maxPeriod) {
		left.setMaxPeriod(maxPeriod);
		right.setMaxPeriod(maxPeriod);
	}

	/**
	* Reset the Encoders distances to zero. Resets the current counts to zero on the encoders.
	*/
	public void reset() {
		left.reset();
		right.reset();
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		if(encodingScale == EncodingType.k4X.value) {
			builder.setSmartDashboardType("Quadrature Encoder");
		}
		else {
			builder.setSmartDashboardType("Encoder");
		}

		builder.addDoubleProperty("Speed", this::getRate, null);
		builder.addDoubleProperty("Distance", this::getDistance, null);
    	builder.addDoubleProperty("Distance per Tick", this::getDistancePerPulse, null);
	}
}
