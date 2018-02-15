package org.usfirst.frc.team6479.robot.commands.teleop;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ToggleShifter extends InstantCommand {

	public ToggleShifter() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevator);
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        boolean isOnClimber = Robot.elevator.isOnClimber();
        if(isOnClimber) {
            Robot.elevator.switchToWinch();
        }
        else {
            Robot.elevator.switchToClimber();
        }
    }

}
