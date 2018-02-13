package org.usfirst.frc.team6479.robot.commands.auton;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

//essential follows the camera
//drives towards it until it reaches a certain distance, then waits until the object moves away
//works with box CUBE mode and GOAL mode
public class CameraDrive extends Command {

	private double speed;
	private double pixelDistance;
    //pixel tolerance
    private static final double PIXEL_TOLERANCE = 2;
    //how close to the object this code should get, will get within 20 inches
    private static final double STOP_RANGE = 20;

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
    		pixelDistance = Robot.camera.getCurrentDistance();
    		speed = 0.4;
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
    		//kP = constant to prevent jerky angle correction
    		double kP = 0.03;
    		//how much to adjust the robot in turning
    		double angle;
    		pixelDistance = Robot.camera.getCurrentDistance();
    		//if pixel distance is in tolerance, set to zero
    		if(inTolerance()) {
    			angle = 0;
    		}
    		else {
    			angle = pixelDistance;
    		}

	        //Equation that decreases speed as the the robot approached the angle goal with precision
	        /*
	        0.2 = min speed
	        0.45 = speed. (Increase for speed increase/ decrease for speed decrease)
	        The parentheses stuff is an equation that goes from 1 to 0 as the angle approaches the goal
	        */
	        //speed = 0.2 + (0.45 * ((Robot.drivetrain.getSonar().getDistance() - STOP_RANGE) / totalDistance));

    	    Robot.drivetrain.racingDrive(speed, -angle*kP);
    }

    private boolean inTolerance() {

        return (Math.abs(pixelDistance) <= PIXEL_TOLERANCE);
    }
    private boolean inRange() {

        return (Robot.drivetrain.getSonar().getDistance() <= STOP_RANGE);
    }



    @Override
    protected boolean isFinished() {
        //if its in range its done
        return inTolerance() && inRange();
    }

    @Override
    protected void end() {
        Robot.drivetrain.stop();
    }
}
