package org.usfirst.frc.team6479.robot.commands.teleop;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ToggleWheely extends InstantCommand {

	public ToggleWheely() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.wheely);
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        boolean isDown = Robot.wheely.isDown();
        if(isDown) {
            Robot.wheely.up();
        }
        else {
            Robot.wheely.down();
        }
    }

}