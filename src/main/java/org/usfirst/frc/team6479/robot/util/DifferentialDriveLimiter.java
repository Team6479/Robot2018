package org.usfirst.frc.team6479.robot.util;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//limits accleration in certain drive functions
public class DifferentialDriveLimiter extends DifferentialDrive {

	public DifferentialDriveLimiter(SpeedController leftMotor, SpeedController rightMotor) {
		super(leftMotor, rightMotor);
	}
	public static final double RATE_LIMITER = 3/5;
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
