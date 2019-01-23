/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6479.robot.commands.auton.elevator;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * Add your docs here.
 */
public class TimedMoveElevator extends TimedCommand {

	public enum PreSetHeight {
		Switch(2),
		Scale(4);

		PreSetHeight(int value) {
			this.value = value;
		}
		int value;

	}

	private double speed;

	/**
	 * Add your docs here.
	 */
	public TimedMoveElevator(double timeout, double speed) {
		super(timeout);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.elevator);

		this.speed = speed;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.elevator.move(speed);
	}

	// Called once after timeout
	@Override
	protected void end() {
		Robot.elevator.move(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
