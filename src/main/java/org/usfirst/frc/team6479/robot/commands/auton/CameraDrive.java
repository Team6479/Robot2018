package org.usfirst.frc.team6479.robot.commands.auton;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//essential follows the camera
//drives towards it until it reaches a certain distance, then waits until the object moves away
//works with box CUBE mode and GOAL mode
public class CameraDrive extends Command {

    public CameraDrive() {
        //uses drivetrain
        requires(Robot.drivetrain);
        requires(Robot.camera);
    }

    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {

    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
    	
    }


    @Override
    protected boolean isFinished() {
        //if its in range its done
        return false;
    }

    @Override
    protected void end() {
        Robot.drivetrain.stop();
    }
}
