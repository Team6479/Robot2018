package org.usfirst.frc.team6479.robot.commands.auton.drive;

import org.usfirst.frc.team6479.robot.Robot;
import org.usfirst.frc.team6479.robot.connection.JetsonServer;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//turns based on camera input on distance to target
//works with box CUBE mode and GOAL mode
public class CameraTurn extends Command {
	private double initialDistance;

    public CameraTurn() {
        //uses drivetrain
        requires(Robot.drivetrain);
        requires(Robot.camera);
    }

    //distance in pixels, aquired from camera
    private double distanceToTarget;
    //speed of the motors
    private double speed;
    //pixel tolerance
    private static final double PIXEL_TOLERANCE = 2;

    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        distanceToTarget = Robot.camera.getCurrentDistance();

        initialDistance = distanceToTarget;

        speed = 0.4;
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {

        distanceToTarget = Robot.camera.getCurrentDistance();


        if (Math.abs(Math.abs(distanceToTarget) - JetsonServer.CUBE_NOT_FOUND) <= 2) {
        		distanceToTarget = 0;
        		return;
        }

        SmartDashboard.putNumber("CAMERA DISTANCE", distanceToTarget);

	    //Equation that decreases speed as the the robot approached the angle goal with precision
	    /*
	    0.4 = min speed
	    0.25 = speed. (Increase for speed increase/ decrease for speed decrease)
	    The parentheses stuff is an equation that goes from 1 to 0 as the angle approaches the goal
	     */
        speed = 0.4 + (0.25 * (Math.abs(distanceToTarget / initialDistance)));

        System.out.println(speed);
        if (distanceToTarget <= 0) {
            Robot.drivetrain.tankDrive(speed, -speed);
        }
        else if (distanceToTarget >= 0) {
            Robot.drivetrain.tankDrive(-speed, speed);
        }
        else {
        	Robot.drivetrain.tankDrive(0, 0);
		}
    }

    private boolean inRange() {
    	System.out.println(distanceToTarget);
		System.out.println((Math.abs(distanceToTarget) <= PIXEL_TOLERANCE));
        return (Math.abs(distanceToTarget) <= PIXEL_TOLERANCE);
    }


    @Override
    protected boolean isFinished() {
        //if its in range its done
        return inRange();
    }

    @Override
    protected void end() {
        Robot.drivetrain.stop();
        System.out.println("Camera Turn Finished");
    }
}
