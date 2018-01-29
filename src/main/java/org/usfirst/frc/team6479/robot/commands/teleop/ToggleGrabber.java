package org.usfirst.frc.team6479.robot.commands.teleop;

import edu.wpi.first.wpilibj.command.InstantCommand;

import org.usfirst.frc.team6479.robot.Robot;


public class ToggleGrabber extends InstantCommand {

    public ToggleGrabber() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.grabber);
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        boolean isGrabbing = Robot.grabber.isGrabbing();

        if(isGrabbing) {
            Robot.grabber.release();
        }
        else {
            Robot.grabber.grab();
        }
    }
}
