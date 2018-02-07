package org.usfirst.frc.team6479.robot.commands.auton;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6479.robot.Robot;

public class StraightDrive extends Command {
	public enum Mode {
		encoderDrive, sonarDrive
	}
    private double speed;

	/*
	distance is in Inches
	Sonar - distanceGoal is desired distance from programming
	Encoder - Distance to travel
	 */
    private double distanceGoal;
    private double distance;
    private double totalDistance;
    private Mode mode;

    public StraightDrive(Mode mode, double distance) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
        requires(Robot.drivetrain);
        this.mode = mode;
        this.distanceGoal = distance;
	}


	/**
	 * The initialize method is called just before the first time
	 * this Command is run after being started.
	 */
	@Override
	protected void initialize() {
	    Robot.drivetrain.getGyro().reset();
	    speed = 0.2;

	    //Distance that needs to be traveled
	    totalDistance = Robot.drivetrain.getSonar().getDistance() - distanceGoal;
	}


	/**
	 * The execute method is called repeatedly when this Command is
	 * scheduled to run until this Command either finishes or is canceled.
	 */
	@Override
	protected void execute() {
        //kP = constant to prevent jerky angle correction
		double kP = 0.03;
	    double angle = Robot.drivetrain.getGyro().getAngle();

	    if (mode == Mode.sonarDrive) {
		    distance = Robot.drivetrain.getSonar().getDistance();

		    //Equation that decreases speed as the the robot approached the angle goal with precision
	        /*
	        0.2 = min speed
	        0.45 = speed. (Increase for speed increase/ decrease for speed decrease)
	        The parentheses stuff is an equation that goes from 1 to 0 as the angle approaches the goal
	        */
		    speed = 0.2 + (0.45 * ((distance - distanceGoal) / totalDistance));
	    }
	    else {
	    	distance = Robot.drivetrain.getEncoder().getDistance();

		    //Equation that decreases speed as the the robot approached the angle goal with precision
	        /*
	        0.2 = min speed
	        0.45 = speed. (Increase for speed increase/ decrease for speed decrease)
	        The parentheses stuff is an equation that goes from 1 to 0 as the angle approaches the goal
	        */
		    speed = 0.2 + (0.45 * ((distanceGoal - distance) / distanceGoal));
	    }

        Robot.drivetrain.curveDrive(speed, -angle*kP);
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
		return distance <= distanceGoal;
	}


	/**
	 * Called once when the command ended peacefully; that is it is called once
	 * after {@link #isFinished()} returns true. This is where you may want to
	 * wrap up loose ends, like shutting off a motor that was being used in the
	 * command.
	 */
	@Override
	protected void end() {

	}


	/**
	 * <p>
	 * Called when the command ends because somebody called {@link #cancel()} or
	 * another command shared the same requirements as this one, and booted it out. For example,
	 * it is called when another command which requires one or more of the same
	 * subsystems is scheduled to run.
	 * </p><p>
	 * This is where you may want to wrap up loose ends, like shutting off a motor that was being
	 * used in the command.
	 * </p><p>
	 * Generally, it is useful to simply call the {@link #end()} method within this
	 * method, as done here.
	 * </p>
	 */
	@Override
	protected void interrupted() {
		super.interrupted();
	}
}
