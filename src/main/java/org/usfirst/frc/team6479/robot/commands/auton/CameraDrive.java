package org.usfirst.frc.team6479.robot.commands.auton;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//turns based on camera input on distance to target
//works with box CUBE mode and GOAL mode
public class CameraDrive extends Command {

    public CameraDrive() {
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

        distanceToTarget = Robot.camera.currentDistance();
        
        speed = 0.4;
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        
        distanceToTarget = Robot.camera.currentDistance();
        
        SmartDashboard.putNumber("CAMERA DISTANCE", distanceToTarget);
        
        if (distanceToTarget <= 0) {
            Robot.drivetrain.tankDrive(speed, -speed);
        }
        else if (distanceToTarget >= 0) {
            Robot.drivetrain.tankDrive(-speed, speed);
        }
    }
    
    private boolean inRange() {
        
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
    }
}
