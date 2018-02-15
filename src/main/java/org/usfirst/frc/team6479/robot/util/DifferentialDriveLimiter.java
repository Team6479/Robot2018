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

	public DifferentialDriveLimiter(SpeedController leftMotor, SpeedController rightMotor) {
		super(leftMotor, rightMotor);
	}

	public void arcadeDrive(double throttle, double turn, boolean squaredInputs, boolean limit) {
		if (limit) {
			int tick = Robot.getCurrentTick();

			if (throttle > lastThrottle) {
				lastThrottle = Math.min(throttle, lastThrottle + (Robot.getCurrentTick() - lastTick));
			}
			else {
				lastThrottle = Math.max(throttle, lastThrottle - (Robot.getCurrentTick() - lastTick));
			}

			if (turn > lastTurn) {
				lastTurn = Math.min(turn, lastTurn + (Robot.getCurrentTick() - lastTurn));
			}
			else {
				lastTurn = Math.max(turn, lastTurn - (Robot.getCurrentTick() - lastTick));
			}

			lastTick = tick;

			super.arcadeDrive(lastThrottle, lastTurn, squaredInputs);
		}
		else {
			super.arcadeDrive(throttle, turn, squaredInputs);
		}
	}

	public void tankDrive(double leftSpeed, double rightSpeed, boolean squaredInputs, boolean limit) {
		if (limit) {
			int tick = Robot.getCurrentTick();
			//calc left speed
			if (leftSpeed > lastLeftSpeed) {
				lastLeftSpeed = Math.min(leftSpeed, lastLeftSpeed + (Robot.getCurrentTick() - lastTick) * 1);
			} else {
				lastLeftSpeed = Math.max(leftSpeed, lastLeftSpeed - (Robot.getCurrentTick() - lastTick) * 1);
			}

			//calc right speed
			if (leftSpeed > lastLeftSpeed) {
				lastRightSpeed = Math.min(rightSpeed, lastRightSpeed + (Robot.getCurrentTick() - lastTick) * 1);
			} else {
				lastRightSpeed = Math.max(rightSpeed, lastRightSpeed - (Robot.getCurrentTick() - lastTick) * 1);
			}
			lastTick = tick;

			super.tankDrive(lastLeftSpeed, lastRightSpeed, squaredInputs);
		}
		else {
			super.tankDrive(leftSpeed, rightSpeed, squaredInputs);
		}
	}
}
