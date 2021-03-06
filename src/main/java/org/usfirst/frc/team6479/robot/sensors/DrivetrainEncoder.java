package org.usfirst.frc.team6479.robot.sensors;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import org.usfirst.frc.team6479.robot.util.Units;

//class to control two encoders so that they can be averaged nicely
public class DrivetrainEncoder extends SendableBase {
	private AdjustedEncoder left;
	private AdjustedEncoder right;
	private Timer timer;
	private double fallbackDistance;
	private double prevRate;
	private double prevTime;

	public DrivetrainEncoder(AdjustedEncoder left, AdjustedEncoder right) {
		this.left = left;
		this.right = right;
		right.setSamplesToAverage(20);
		left.setSamplesToAverage(25);

		timer = new Timer();
		timer.start();

		prevRate = 0;
		prevTime = 0;
	}
	public DrivetrainEncoder(int leftA, int leftB, boolean reverseLeft, int rightA, int rightB, boolean reverseRight, Encoder.EncodingType encoding) {
		this(new AdjustedEncoder(leftA, leftB, reverseLeft, encoding), new AdjustedEncoder(rightA, rightB, reverseRight, encoding));
	}

	public Encoder getLeft() {
		return left;
	}
	public Encoder getRight() {
		return right;
	}
	public Timer getTimer() {
		return timer;
	}


	//accesor methods take avergaes
	public double getDistance() {
		return (left.getDistance() + right.getDistance()) / 2;
	}
	public double getDistancePerPulse() {
		return (left.getDistancePerPulse() + right.getDistancePerPulse()) / 2;
	}
	public double getFallbackDistance() {
		return fallbackDistance;
	}
	public void calcFallbackDistance() {
			//fallbackDistance = getRate() * timer.get();
		double currentTime = timer.get();
		double currentRate = getRate();
		fallbackDistance += (Math.abs(currentRate - prevRate) * 10) * Math.abs(currentTime - prevTime);
		prevRate = currentRate;
		prevTime = currentTime;
	}
	public double getRate() {
		return (left.getRate() + right.getRate()) / 2;
	}
	public int getRaw() {
		return (left.getRaw() + right.getRaw()) / 2;
	}
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


	public void reset() {
		left.reset();
		right.reset();
		timer.reset();
		prevRate= 0;
		prevTime = 0;
		fallbackDistance = 0;
	}


	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("Drivetrain Encoder");

		builder.addDoubleProperty("Speed", this::getRate, null);
		builder.addDoubleProperty("Distance", this::getDistance, null);
		builder.addDoubleProperty("Distance per Tick", this::getDistancePerPulse, null);
	}
}
