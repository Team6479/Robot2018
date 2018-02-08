package org.usfirst.frc.team6479.robot.sensors;

import edu.wpi.first.wpilibj.Ultrasonic;

//mananges two ultrasonic sensors
public class DoubleUltrasonic {

	private Ultrasonic left;
	private Ultrasonic right;
	
	//ping is input
	//echo is output
	public DoubleUltrasonic(int pingLeft, int echoLeft, int pingRight, int echoRight) {
		left = new Ultrasonic(pingLeft, echoLeft);
		right = new Ultrasonic(pingRight, echoRight);
		//sets right to auto as well
		left.setAutomaticMode(true);
	}
	public double get() {
		return (getLeft() + getRight()) / 2;
	}
	public double getLeft() {
		return left.getRangeInches();
	}
	public double getRight() {
		return right.getRangeInches();
	}

}
