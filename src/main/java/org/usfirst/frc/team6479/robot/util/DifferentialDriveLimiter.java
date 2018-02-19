package org.usfirst.frc.team6479.robot.util;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//limits accleration in certain drive functions
public class DifferentialDriveLimiter extends DifferentialDrive {
	public static final double RATE_LIMITER = 3/5;

	private int lastTick;

	//Arcade
	private double lastThrottle;
	private double lastTurn;

	//Tank
	private double lastLeftSpeed;
	private double lastRightSpeed;

	private double maxSpeed;

	public DifferentialDriveLimiter(SpeedController leftMotor, SpeedController rightMotor) {
		super(leftMotor, rightMotor);
		maxSpeed = 1;
	}

	public void setMaxSpeed(double speed) {
		maxSpeed = speed;
		System.out.println("SPEEDY BOI: " + maxSpeed);
	}

	public void arcadeDrive(double throttle, double turn, boolean squaredInputs, boolean limit) {
		if (limit) {
			int tick = Robot.getCurrentTick();

			if (throttle > lastThrottle) {
				lastThrottle = Math.min(throttle, lastThrottle + (Robot.getCurrentTick() - lastTick)) * maxSpeed;
			}
			else {
				lastThrottle = Math.max(throttle, lastThrottle - (Robot.getCurrentTick() - lastTick)) * maxSpeed;
			}

			if (turn > lastTurn) {
				lastTurn = Math.min(turn, lastTurn + (Robot.getCurrentTick() - lastTurn)) * maxSpeed;
			}
			else {
				lastTurn = Math.max(turn, lastTurn - (Robot.getCurrentTick() - lastTick))* maxSpeed;
			}

			lastTick = tick;
			//System.out.println(lastTurn);
			super.arcadeDrive(lastThrottle, lastTurn, squaredInputs);
		}
		else {
			super.arcadeDrive(throttle * maxSpeed, turn * maxSpeed, squaredInputs);
		}
	}

	public void tankDrive(double leftSpeed, double rightSpeed, boolean squaredInputs, boolean limit) {
		if (limit) {
			int tick = Robot.getCurrentTick();
			//calc left speed
			if (leftSpeed > lastLeftSpeed) {
				lastLeftSpeed = Math.min(leftSpeed, lastLeftSpeed + (Robot.getCurrentTick() - lastTick)) * maxSpeed;
			} else {
				lastLeftSpeed = Math.max(leftSpeed, lastLeftSpeed - (Robot.getCurrentTick() - lastTick)) * maxSpeed;
			}

			//calc right speed
			if (leftSpeed > lastLeftSpeed) {
				lastRightSpeed = Math.min(rightSpeed, lastRightSpeed + (Robot.getCurrentTick() - lastTick)) * maxSpeed;
			} else {
				lastRightSpeed = Math.max(rightSpeed, lastRightSpeed - (Robot.getCurrentTick() - lastTick)) * maxSpeed;
			}
			lastTick = tick;

			super.tankDrive(lastLeftSpeed, lastRightSpeed, squaredInputs);
		}
		else {
			super.tankDrive(leftSpeed * maxSpeed, rightSpeed * maxSpeed, squaredInputs);
		}
	}
}
