package org.usfirst.frc.team6479.robot.commands.auton.drive;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    private double prevDistanceAverage;
    private double prevDistanceNum;
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
		//System.out.println("Befpore");
	    Robot.drivetrain.getGyro().reset();
	    Robot.drivetrain.getEncoder().reset();
	    speed = 0.2;
	    //System.out.println("After");

	    //Distance that needs to be traveled
	    totalDistance = Robot.drivetrain.getUltrasonic().get() - distanceGoal;
	}


	/**
	 * The execute method is called repeatedly when this Command is
	 * scheduled to run until this Command either finishes or is canceled.
	 */
	@Override
	protected void execute() {
		//System.out.println("SPICY");
        //kP = constant to prevent jerky angle correction
		double kP = 0.03;
	    double angle = Robot.drivetrain.getGyro().getAngle();

	    if (mode == Mode.sonarDrive) {
		    distance = Robot.drivetrain.getUltrasonic().get() - 10;

		    //Equation that decreases speed as the the robot approached the angle goal with precision
	        /*
	        0.2 = min speed
	        0.45 = speed. (Increase for speed increase/ decrease for speed decrease)
	        The parentheses stuff is an equation that goes from 1 to 0 as the angle approaches the goal
	        */
		    speed = 0.35 + (0.4 * ((distance - distanceGoal) / totalDistance));
	    }
	    else {
	    		//Collision detection: Checks if an object is 30 in. in front of it
	    		/*if (Robot.drivetrain.getUltrasonic().get() <= 30) {
	    			speed = 0;
		        }*/
		    //else
		    	{
			    distance = Robot.drivetrain.getEncoder().getDistance();

			    //Safety for if encoders do not return a value
			    prevDistanceNum++;
			    prevDistanceAverage = (prevDistanceAverage + distance) /  prevDistanceNum;

			    //System.out.println(distance);
			    //System.out.println(distanceGoal);

			    //Equation that decreases speed as the the robot approached the angle goal with precision
	            /*
	            0.2 = min speed
	            0.45 = speed. (Increase for speed increase/ decrease for speed decrease)
	            The parentheses stuff is an equation that goes from 1 to 0 as the angle approaches the goal
	            */
	            if((distanceGoal - distance) < 40) {
		            speed = 0.2 + (0.45 * ((distanceGoal - distance) / distanceGoal));
	            }
	            else {
	            	speed = 0.65;
	            }
			    //System.out.println(speed);
		    }
	    }

        Robot.drivetrain.racingDrive(speed, -angle*kP);
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

		if (mode == Mode.sonarDrive) {
			return distance <= distanceGoal;
		}
		else {
			if (prevDistanceNum >= 5) {
				if (prevDistanceAverage <= 0) {
					return true;
				}
			}
			return distance >= distanceGoal;
		}
	}


	/**
	 * Called once when the command ended peacefully; that is it is called once
	 * after {@link #isFinished()} returns true. This is where you may want to
	 * wrap up loose ends, like shutting off a motor that was being used in the
	 * command.
	 */
	@Override
	protected void end() {
		Robot.drivetrain.stop();
	}
}
