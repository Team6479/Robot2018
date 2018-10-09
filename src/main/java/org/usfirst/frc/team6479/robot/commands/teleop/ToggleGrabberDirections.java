package org.usfirst.frc.team6479.robot.commands.teleop;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleGrabberDirections extends Command {

	public ToggleGrabberDirections() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.grabber);
	}

	/**
	 * The execute method is called repeatedly when this Command is
	 * scheduled to run until this Command either finishes or is canceled.
	 */
	@Override
	protected void execute() {
		boolean isSucking = Robot.grabber.isSucking();
		boolean isSpitting = Robot.grabber.isSpitting();
		boolean isDriverLeftBumperPressed = Robot.oi.getDriverLeftBumper().isPressed();
		boolean isDriverRightBumperPressed = Robot.oi.getDriverRightBumper().isPressed();

		if(isDriverLeftBumperPressed && !isDriverRightBumperPressed) {
			if(!isSucking && !isSpitting) {
				Robot.grabber.suck(Robot.grabber.GRABBER_SUCK_SPEED);
			}
		}
		else if(!isDriverLeftBumperPressed && isDriverRightBumperPressed) {
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
