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
	
	//velocity
	private static final double DELTA_V = 0.5;
	private static final double CLOSE_V = 5;
	//arcade
	private double setThrottleVelocity;
	private double leftSpeedV;
	private double rightSpeedV;
	//tank
	private double setLeftVelocity;
	private double setRightVelocity;
	private double throttleV;

	public DifferentialDriveLimiter(SpeedController leftMotor, SpeedController rightMotor) {
		super(leftMotor, rightMotor);
		maxSpeed = 1;
	}

	public void setMaxSpeed(double speed) {
		maxSpeed = speed;
	}
	
	//moves robot at set velocity
	//throttle is velocity
	//turn is normal -1 to 1
	public void arcadeDriveVelocity(double throttleVelocity, double turn, boolean squaredInputs) {
		
		//get the current velcoity
		setThrottleVelocity = Robot.drivetrain.getEncoder().getRate();
		
		//set velocity is close to current velocity
		if(Math.abs(throttleVelocity - setThrottleVelocity) <= CLOSE_V) {
			//do nothing, dont change the throttle
		}
		//the robot should go faster
		else if(throttleVelocity > setThrottleVelocity) {
			throttleV += DELTA_V;
		}
		//the robot should go slower
		else {
			throttleV += -DELTA_V;
		}
		
		super.arcadeDrive(throttleV, turn, squaredInputs);
	}
	//moves robot at set velocity
	public void tankDrive(double leftVelocity, double rightVelocity, boolean squaredInputs) {
		//get the current velcoity
		setLeftVelocity = Robot.drivetrain.getEncoder().getLeft().getRate();
		setRightVelocity = Robot.drivetrain.getEncoder().getRight().getRate();
		
		//set velocity is close to current velocity
		if(Math.abs(leftVelocity - setLeftVelocity) <= CLOSE_V) {
			//do nothing, dont change the throttle
		}
		//the left side of the robot should go faster
		else if(leftVelocity > setLeftVelocity) {
			leftSpeedV += DELTA_V;
		}
		//the left side of the robot should go slower
		else {
			leftSpeedV += -DELTA_V;
		}
		
		//set velocity is close to current velocity
		if(Math.abs(rightVelocity - setRightVelocity) <= CLOSE_V) {
			//do nothing, dont change the throttle
		}
		//the left side of the robot should go faster
		else if(rightVelocity > setRightVelocity) {
			rightSpeedV += DELTA_V;
		}
		//the left side of the robot should go slower
		else {
			rightSpeedV += -DELTA_V;
		}
		
		super.tankDrive(leftSpeedV, rightSpeedV, squaredInputs);
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
