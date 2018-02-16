package org.usfirst.frc.team6479.robot.commands.auton.camera;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

import communication.JetsonPacket.ModePacket.Mode;

public class ToggleCamera extends InstantCommand {

	//the mode this command will change the camera to
	private Mode changeTo;

    public ToggleCamera(Mode changeModeTo) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.camera);
        changeTo = changeModeTo;
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
    		Robot.camera.setCameraMode(changeTo);
    }
}
