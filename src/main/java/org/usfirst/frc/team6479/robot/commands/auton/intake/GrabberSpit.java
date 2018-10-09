package org.usfirst.frc.team6479.robot.commands.auton.intake;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class GrabberSpit extends InstantCommand {

    public GrabberSpit() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.grabber);
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
    		Robot.grabber.spit(1);;
    		//System.out.println("RELEASE: " + Robot.grabber.isSpitting());
	}
}
