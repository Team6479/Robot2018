package org.usfirst.frc.team6479.robot.commands.auton;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class DeadReckonDrive extends TimedCommand {

    public enum Direction {
        dLeft, dRight, dForward, dBackward
    }
    
    private double speed;
    private Direction direction;
    //timeout in seconds
    //speed is always positive
    public DeadReckonDrive(double timeout, double speed, Direction direction) {
        super(timeout);
        this.speed = Math.abs(speed);
        this.direction = direction;
        
        requires(Robot.drivetrain);
    }

    @Override
    protected void execute() {
        
        
        //run the drive train at the set speed in the proper direction
        switch(direction) {
            case dLeft:
                Robot.drivetrain.tankDrive(-speed, speed);
                break;
            case dRight:
                Robot.drivetrain.tankDrive(speed, -speed);
                break;
            case dForward:
                Robot.drivetrain.tankDrive(speed, speed);
                break;
            case dBackward:
                Robot.drivetrain.tankDrive(-speed, -speed);
                break;
            default:
                Robot.drivetrain.stop();
                break;
        }
    }
    
    //when timed out, set to zero
    @Override
    protected void end() {
        Robot.drivetrain.stop();
    }
}
