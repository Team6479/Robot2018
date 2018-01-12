package org.usfirst.frc.team6479.robot.commands;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import robot.xbox.XboxMap;

public class RacingDrive extends Command {
	public RacingDrive() {
		//Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
	}
	//Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}
	//Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		//racing drive computation
		double left = Robot.oi.getXboxDriver().getRawAxis(XboxMap.LeftTrigger);
		double right = Robot.oi.getXboxDriver().getRawAxis(XboxMap.RightTrigger);
		// each trigger has an axis range of 0 to 1
		// to make left trigger reverse, subtract axis value from right trigger
		double throttle = right - left;
		double turn = Robot.oi.getXboxDriver().getRawAxis(XboxMap.LeftJoystickX) * -1;
		Robot.drivetrain.drive(throttle, turn);
	}
	//Make this return true when this Command no longer needs to run execute()
	//must always run to allow for operator control
	@Override
	protected boolean isFinished() {
		return false;
	}
	//Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drivetrain.drive(0, 0);
	}
	//Called when another command which requires one or more of the same subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
