package org.usfirst.frc.team6479.robot.commands.auton.elevator;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class Push extends InstantCommand {

    public Push() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.pusher);
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
    		Robot.pusher.extend();
    }
}
