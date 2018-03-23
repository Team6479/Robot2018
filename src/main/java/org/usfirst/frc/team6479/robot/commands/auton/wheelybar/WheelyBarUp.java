package org.usfirst.frc.team6479.robot.commands.auton.wheelybar;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class WheelyBarUp extends InstantCommand {

    public WheelyBarUp() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.wheely);
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
    		Robot.wheely.up();
    }
}
