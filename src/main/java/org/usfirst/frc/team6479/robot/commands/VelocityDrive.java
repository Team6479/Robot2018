package org.usfirst.frc.team6479.robot.commands;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class VelocityDrive extends TimedCommand {
	
    private double velocity;
    
    //timeout in seconds
    //speed is always positive
    public VelocityDrive(double timeout, double velocity) {
        super(timeout);
        this.velocity = velocity;
        
        requires(Robot.drivetrain);
    }

    @Override
    protected void execute() {
        
    	
		double kP = 0.03;
	    double angle = Robot.drivetrain.getGyro().getAngle();
	    
    		Robot.drivetrain.getDrive().arcadeDriveVelocity(velocity, -angle*kP, false);
        //Robot.drivetrain.getDrive().tankDriveVelocity(velocity, velocity, false);
    }
    
    //when timed out, set to zero
    @Override
    protected void end() {
        Robot.drivetrain.stop();
        double distance = Robot.drivetrain.getEncoder().getDistance();
        System.out.println("Distance is: " + distance);
    }
}
