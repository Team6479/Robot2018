package org.usfirst.frc.team6479.robot.commands.teleop;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TogglePusher extends Command {

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
	    boolean wasJustPressedAssistant = Robot.oi.getAssistantRightBumper().wasJustPressed();
	    boolean isDriverOverride = Robot.oi.getDriverController().getYButton();
	    boolean wasJustPressedDriver = Robot.oi.getDriverRightBumper().wasJustPressed();
	    if(wasJustPressedAssistant || (wasJustPressedDriver && isDriverOverride)) {
		    if(isExtended) {
			    Robot.pusher.retract();
		    }
		    else {
			    Robot.pusher.extend();
		    }
	    }
    }

	@Override
	protected boolean isFinished() {
		return false;
	}
	//Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.pusher.retract();
	}
}
