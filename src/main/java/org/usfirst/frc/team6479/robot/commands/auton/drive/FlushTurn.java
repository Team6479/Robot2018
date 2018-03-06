package org.usfirst.frc.team6479.robot.commands.auton.drive;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//adjust the robot so that it is perpedicular to the surface it is facing
public class FlushTurn extends Command {

    public FlushTurn() {
        //uses drivetrain
        requires(Robot.drivetrain);
    }

    //distance to reduce
    private double distance;
    //speed of the motors
    private double speed;
    //tolerance in inches of how flush it should get
    private static final double TOLERANCE = 2;


    private double distance() {
		double left = Robot.drivetrain.getUltrasonic().getLeft();
		double right = Robot.drivetrain.getUltrasonic().getRight();
		return left - right;
    }

    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        distance = distance();

        speed = 0.25;
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {

        distance = distance();

        SmartDashboard.putNumber("CHANGE IN DISTANCE", distance);

	    //Equation that decreases speed as the the robot approached the angle goal with precision
	    /*
	    0.2 = min speed
	    0.45 = speed. (Increase for speed increase/ decrease for speed decrease)
	    The parentheses stuff is an equation that goes from 1 to 0 as the angle approaches the goal
	    */
	    //speed = 0.2 + (0.45 * (distance / initDistance));


        if (distance <= 0) {
            Robot.drivetrain.tankDrive(-speed, speed);
        }
        else if (distance >= 0) {
            Robot.drivetrain.tankDrive(speed, -speed);
        }
    }

    private boolean inRange() {

        return (Math.abs(distance) <= TOLERANCE);
    }


    @Override
    protected boolean isFinished() {
        //if its in range its done
        return inRange();
    }

    @Override
    protected void end() {
        Robot.drivetrain.stop();
    }

}
