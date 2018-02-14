package org.usfirst.frc.team6479.robot.util;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//limits accleration in certain drive functions
public class DifferentialDriveLimiter extends DifferentialDrive {
	public static final double RATE_LIMITER = 3/5;

	private int lastTick;

	public DifferentialDriveLimiter(SpeedController leftMotor, SpeedController rightMotor) {
		super(leftMotor, rightMotor);
	}

	@Override
	public void arcadeDrive(double throttle, double turn, boolean limit) {
		//int tick = Robot.getCurrentTick();
		if (limit) {

		}
		else {
			super.arcadeDrive(throttle, turn);
		}
	}

	@Override
	public void tankDrive(double leftSpeed, double rightSpeed, boolean limit) {
		if (limit) {

		}
		else {

		}
	}

	public void arcadeDriveLimiter(double throttle, double turn) {
		throttle = Math.copySign(Math.pow(throttle, RATE_LIMITER), throttle);
		turn = Math.copySign(Math.pow(turn, RATE_LIMITER), turn);
		super.arcadeDrive(throttle, turn);
	}

	public void tankDriveLimiter(double leftSpeed, double rightSpeed) {
		leftSpeed = Math.copySign(Math.pow(leftSpeed, RATE_LIMITER), leftSpeed);
		rightSpeed = Math.copySign(Math.pow(rightSpeed, RATE_LIMITER), rightSpeed);
		super.tankDrive(leftSpeed, rightSpeed);
	}
}
