package org.usfirst.frc.team6479.robot.commands.teleop;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleWheely extends Command {

	public ToggleWheely() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.wheely);
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
	@Override
    protected void execute() {
        boolean isUp = Robot.wheely.isUp();
	    boolean wasJustPressedDriver = Robot.oi.getDriverBButton().wasJustPressed();
	    if(isUp && wasJustPressedDriver) {
	    		Robot.wheely.down();
	    	}
	    else {
	    		Robot.wheely.up();
	    }
    }

	@Override
	protected boolean isFinished() {
		return false;
	}
	//Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.wheely.up();
	}

}