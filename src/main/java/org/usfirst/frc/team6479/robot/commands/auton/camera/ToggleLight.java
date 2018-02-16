package org.usfirst.frc.team6479.robot.commands.auton.camera;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;


public class ToggleLight extends InstantCommand {

    public ToggleLight() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.camera);
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        boolean isOn = Robot.camera.isLightOn();

        if(isOn) {
            Robot.camera.lightOff();
        }
        else {
            Robot.camera.lightOn();
        }
    }
}
