package org.usfirst.frc.team6479.robot.commands;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class Reset extends InstantCommand {

	public Reset() {
		requires(Robot.drivetrain);
		requires(Robot.elevator);
	}

    protected void execute() {
        Robot.drivetrain.getEncoder().reset();
        Robot.elevator.getEncoder().reset();
        Robot.drivetrain.getGyro().reset();
        Robot.drivetrain.getGyro().calibrate();
        Robot.drivetrain.getGyro().reset();
 }
}
