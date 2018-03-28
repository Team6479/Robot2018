package org.usfirst.frc.team6479.robot.commands.teleop;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleGrabber extends Command {

	public ToggleGrabber() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.grabber);
	}

	/**
	 * The execute method is called repeatedly when this Command is
	 * scheduled to run until this Command either finishes or is canceled.
	 */
	@Override
	protected void execute() {
		boolean isGrabbing = Robot.grabber.isGrabbing();
		boolean pressedAssistant = Robot.oi.getAssistantLeftBumper().isPressed();
		boolean isDriverOverride = Robot.oi.getDriverController().getYButton();
		boolean pressedDriver = Robot.oi.getDriverLeftBumper().isPressed();
		if (pressedAssistant || (pressedDriver && isDriverOverride)) {
			if(!isGrabbing) {
				Robot.grabber.grab();
			}
		}
		else {
			if(isGrabbing) {
				Robot.grabber.release();
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
		Robot.grabber.release();
	}
}
