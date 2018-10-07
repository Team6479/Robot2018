package org.usfirst.frc.team6479.robot.subsystems;

import org.usfirst.frc.team6479.robot.commands.teleop.ToggleGrabberSuck;
import org.usfirst.frc.team6479.robot.config.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Jacob Abraham
 * @author Thomas Quillan
 */
public class Grabber extends Subsystem implements SafeSubsystem {

	private SpeedController leftMotor;
	private SpeedController rightMotor;
	private SpeedController grabber;

	public Grabber() {
		leftMotor = new Spark(RobotMap.grabberLeftPort);
		rightMotor = new Spark(RobotMap.grabberRightPort);
		grabber = new SpeedControllerGroup(leftMotor, rightMotor);
	}

    @Override
    protected void initDefaultCommand() {
		// setDefaultCommand(new ToggleGrabberSuck());
	}

	public void suck(double speed) {
		if (speed > 0.85) {
			speed = 0.85;
		}
		grabber.set(-speed);
	}

	public void spit(double speed) {
		grabber.set(speed);
	}

	/**
	 * @return If the grabber is sucking
	 */
	public boolean isSucking() {
		boolean isSucking = grabber.get() > 0;
		return isSucking;
	}

	public boolean isSpitting() {
		boolean isSpitting = grabber.get() < 0;
		return isSpitting;
	}

	public SpeedController getLeftMotor() {
		return leftMotor;
	}

	public SpeedController getRightMotor() {
		return rightMotor;
	}

	public SpeedController getGrabberMotors() {
		return grabber;
	}

	@Override
	public void stop() {
		grabber.set(0);
	}

}
