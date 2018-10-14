package org.usfirst.frc.team6479.robot.commands.auton.elevator;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

//move elevator to a height
public class MoveElevator extends Command {

	public enum PreSetHeight {
		PrepSwitch(400),
		Switch(1255),
		Scale(2800),
		Home(0),
		Vision(10);

		PreSetHeight(int value) {
			this.value = value;
		}
		int value;

	}

	private int ticks;
    private double speed;
    private boolean needToMoveUp;
    private PreSetHeight height;
    private double goalValue;

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
		Robot.eventLogger.writeToLog("MoveElevator Starting at: " + height.name());
		// Robot.elevator.unlock();
		// Robot.elevator.switchToWinch();

		ticks = 0;

	    speed = 0.7;
	    goalValue = height.value;
	    //if the current height is higher than the setpoint, needToMoveUp is false
	    needToMoveUp = Robot.elevator.getEncoder().get() < height.value;
	}


	/**
	 * The execute method is called repeatedly when this Command is
	 * scheduled to run until this Command either finishes or is canceled.
	 */
	@Override
	protected void execute() {
		ticks++;
		if(ticks >= 20 && Robot.elevator.getEncoder().get() == 0) {
			Robot.eventLogger.writeToLog("MoveElevator Fail Safe Triggered");
			speed = 0;
		}
		else {
			speed = 0.3 + 0.15 * ((goalValue - Robot.elevator.getEncoder().get()) / goalValue);
		}

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
		if(needToMoveUp) {
			return Robot.elevator.getEncoder().get() >= height.value;
		}
		else {
			return Robot.elevator.getEncoder().get() <= height.value;
		}
	}

   /* private boolean isInRange(int d1, int d2) {
        return Math.abs(d1 - d2) <= 5;
    }*/

	/**
	 * Called once when the command ended peacefully; that is it is called once
	 * after {@link #isFinished()} returns true. This is where you may want to
	 * wrap up loose ends, like shutting off a motor that was being used in the
	 * command.
	 */
	@Override
	protected void end() {
		//Robot.elevator.lock();
		Robot.elevator.stop();
		Robot.eventLogger.writeToLog("MoveElevator Finished");
	}
}
