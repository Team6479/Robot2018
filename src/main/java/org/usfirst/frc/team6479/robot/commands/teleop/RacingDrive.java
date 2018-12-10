package org.usfirst.frc.team6479.robot.commands.teleop;

import org.usfirst.frc.team6479.robot.JoystickMap;
import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import robot.xbox.ButtonTracker;
import robot.xbox.XboxMap;

public class RacingDrive extends Command {
	private double scale;
    public static boolean tiltMode = false;
    private boolean beenPressed = false;
    public ButtonTracker button11 = new ButtonTracker(Robot.oi.stick, JoystickMap.joystickButton11);
	public RacingDrive() {
		//Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
	}
	// Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        //Execute arcadeDrive with the x axis and y axis
		scale = (-Robot.oi.stick.getThrottle() + 1) / 2;
		if (scale > 0.6) {
			scale = 0.6;
		}
        // Robot.drivetrain.arcadeDrive(Robot.oi.controller.getX(Hand.kLeft), Robot.oi.controller.getY(Hand.kLeft));
		Robot.drivetrain.racingDrive(-Robot.oi.stick.getRawAxis(JoystickMap.xAxis) * scale, Robot.oi.stick.getRawAxis(JoystickMap.zAxis) * scale);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
