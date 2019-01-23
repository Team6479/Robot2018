package org.usfirst.frc.team6479.robot.commands.auton.intake;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class GrabberSuck extends InstantCommand {

    public GrabberSuck() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.grabber);
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
    		Robot.grabber.suck(0.5);
    }
}
