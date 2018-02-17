package org.usfirst.frc.team6479.robot.commands.auton.elevator;

import org.usfirst.frc.team6479.robot.Robot;
import org.usfirst.frc.team6479.robot.commands.auton.drive.StraightDrive.Mode;

import edu.wpi.first.wpilibj.command.Command;

//move elevator to a height
public class MoveElevator extends Command {

	public enum PreSetHeight {
		Switch(20),
		Scale(40),
		Home(0),
		Vision(10);
		
		PreSetHeight(int value) {
			this.value = value;
		}
		int value;
		
	}	
	
    private double speed;
    private boolean needToMoveUp;
    private PreSetHeight height;

    public MoveElevator(PreSetHeight height) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
        requires(Robot.elevator);
        this.height = height;
	}


	/**
	 * The initialize method is called just before the first time
	 * this Command is run after being started.
	 */
	@Override
	protected void initialize() {
		Robot.elevator.unlock();
		Robot.elevator.switchToWinch();
		
	    speed = 0.4;
	    
	    //if the current height is higher than the setpoint, needToMoveUp is false
	    needToMoveUp = Robot.elevator.getEncoder().get() < height.value;
	}


	/**
	 * The execute method is called repeatedly when this Command is
	 * scheduled to run until this Command either finishes or is canceled.
	 */
	@Override
	protected void execute() {
		if(needToMoveUp) {
			Robot.elevator.move(speed);
		}
		else {
			Robot.elevator.move(-speed);
		}
	}


	/**
	 * <p>
	 * Returns whether this command is finished. If it is, then the command will be removed and
	 * {@link #end()} will be called.
	 * </p><p>
	 * It may be useful for a team to reference the {@link #isTimedOut()}
	 * method for time-sensitive commands.
	 * </p><p>
	 * Returning false will result in the command never ending automatically. It may still be
	 * cancelled manually or interrupted by another command. Returning true will result in the
	 * command executing once and finishing immediately. It is recommended to use
	 * {@link edu.wpi.first.wpilibj.command.InstantCommand} (added in 2017) for this.
	 * </p>
	 *
	 * @return whether this command is finished.
	 * @see Command#isTimedOut() isTimedOut()
	 */
	@Override
	protected boolean isFinished() {
		return isInRange(Robot.elevator.getEncoder().get(), height.value);
	}
	
    private boolean isInRange(int d1, int d2) {
        return Math.abs(d1 - d2) <= 5;
    }

	/**
	 * Called once when the command ended peacefully; that is it is called once
	 * after {@link #isFinished()} returns true. This is where you may want to
	 * wrap up loose ends, like shutting off a motor that was being used in the
	 * command.
	 */
	@Override
	protected void end() {
		Robot.elevator.lock();
		Robot.elevator.stop();
	}
}
