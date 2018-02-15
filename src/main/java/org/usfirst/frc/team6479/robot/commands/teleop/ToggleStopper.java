package org.usfirst.frc.team6479.robot.commands.teleop;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ToggleStopper extends InstantCommand {

	public ToggleStopper() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevator);
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        boolean isLocked = Robot.elevator.isLocked();
        if(isLocked) {
            Robot.elevator.unlock();;
        }
        else {
            Robot.elevator.lock();
        }
    }

}
