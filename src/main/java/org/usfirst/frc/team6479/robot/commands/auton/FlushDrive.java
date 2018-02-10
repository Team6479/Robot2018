package org.usfirst.frc.team6479.robot.commands.auton;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//adjust the robot so that it is perpedicular to the surface it is facing
public class FlushDrive extends Command {

    public FlushDrive() {
        //uses drivetrain
        requires(Robot.drivetrain);
    }

    //distance to reduce
    private double distance;
    //speed of the motors
    private double speed;
    //tolerance in inches of how flush it should get
    private static final double TOLERANCE = 1;


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

        speed = 0.4;
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {

        distance = distance();

        SmartDashboard.putNumber("CHANGE IN DISTANCE", distance);

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
