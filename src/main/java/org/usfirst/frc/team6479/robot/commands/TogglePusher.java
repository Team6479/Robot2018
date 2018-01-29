package org.usfirst.frc.team6479.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

import org.usfirst.frc.team6479.robot.Robot;

public class TogglePusher extends InstantCommand {

    public TogglePusher() {
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
        boolean isExtended = Robot.pusher.isExtend();
        if(isExtended) {
            Robot.pusher.retract();
        }
        else {
            Robot.pusher.extend();
        }
    }
}
