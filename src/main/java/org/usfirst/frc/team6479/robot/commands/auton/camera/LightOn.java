package org.usfirst.frc.team6479.robot.commands.auton.camera;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;


public class LightOn extends InstantCommand {

    public LightOn() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.camera);
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
           Robot.camera.lightOn();
    }
}
