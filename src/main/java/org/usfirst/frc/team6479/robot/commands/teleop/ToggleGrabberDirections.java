package org.usfirst.frc.team6479.robot.commands.teleop;

import org.usfirst.frc.team6479.robot.JoystickMap;
import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import robot.xbox.ButtonTracker;

public class ToggleGrabberDirections extends Command {

	private boolean driverLeftBumperToggle;
	public ButtonTracker trigger = new ButtonTracker(Robot.oi.stick, JoystickMap.joystickButton1);
	public ButtonTracker sideButton = new ButtonTracker(Robot.oi.stick, JoystickMap.joystickButton2);

	public ToggleGrabberDirections() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.grabber);
		driverLeftBumperToggle = false;
	}

	/**
	 * The execute method is called repeatedly when this Command is
	 * scheduled to run until this Command either finishes or is canceled.
	 */
	@Override
	protected void execute() {
		boolean isSucking = Robot.grabber.isSucking();
		boolean isSpitting = Robot.grabber.isSpitting();
		boolean isDriverLeftBumperPressed = sideButton.isPressed();
		boolean isDriverRightBumperPressed = trigger.isPressed();

		if(sideButton.wasJustPressed()) {
			driverLeftBumperToggle = !driverLeftBumperToggle;
		}

		if(driverLeftBumperToggle && !isDriverRightBumperPressed) {
			if(!isSucking && !isSpitting) {
				Robot.grabber.suck(Robot.grabber.GRABBER_SUCK_SPEED);
			}
		}
		else if(!driverLeftBumperToggle && isDriverRightBumperPressed) {
			if(!isSucking && !isSpitting) {
				Robot.grabber.spit(Robot.grabber.GRABBER_SPIT_SPEED);
			}
		}
		else {
			if(isSucking) {
				Robot.grabber.suck(0);
			}
			if(isSpitting) {
				Robot.grabber.spit(0);
			}
		}
    }

	@Override
	protected boolean isFinished() {
		return false;
	}
	//Called once after isFinished returns true
	@Override
	protected void end() {
        Robot.grabber.suck(0);
		Robot.grabber.spit(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.grabber.suck(0);
		Robot.grabber.spit(0);
	}
}
