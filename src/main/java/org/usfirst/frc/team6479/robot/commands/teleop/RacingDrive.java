package org.usfirst.frc.team6479.robot.commands.teleop;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import robot.xbox.XboxMap;

public class RacingDrive extends Command {
    
    private static final double DEADZONE = 0.1;
    
	public RacingDrive() {
		//Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
	}
	//Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		//racing drive computation
		double left = Robot.oi.getXbox().getRawAxis(XboxMap.LeftTrigger);
		double right = Robot.oi.getXbox().getRawAxis(XboxMap.RightTrigger);
		// each trigger has an axis range of 0 to 1
		// to make left trigger reverse, subtract axis value from right trigger
		double throttle = right - left;
		double turn = Robot.oi.getXbox().getRawAxis(XboxMap.LeftJoystickX);
		//if turn is in deadzone, turn equal 0
		if(Math.abs(turn) <= DEADZONE) {
		    turn = 0.0;
		}
		Robot.drivetrain.curveDrive(throttle, turn);
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
		Robot.drivetrain.curveDrive(0, 0);
	}
}
