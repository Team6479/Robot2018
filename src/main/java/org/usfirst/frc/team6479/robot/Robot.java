package org.usfirst.frc.team6479.robot;

import edu.wpi.first.wpilibj.command.Scheduler;
import robot.base.TimedIterativeRobot;

import org.usfirst.frc.team6479.robot.config.RobotMap;
import org.usfirst.frc.team6479.robot.control.OI;
import org.usfirst.frc.team6479.robot.subsystems.Drivetrain;

public class Robot extends TimedIterativeRobot {

	public static OI oi;
	public static Drivetrain drivetrain;

	@Override
	public void robotInit() {
		
		//init the controls in oi
		oi = new OI();
		
		//init subsystems
		drivetrain = new Drivetrain();
		
	}
	@Override
	public void autonomousInit() {
		
	}
	@Override
	public void autonomousPeriodic() {
		
	}
	@Override
	public void teleopInit() {
		
	}
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		
		
		//kill switch code
		if(oi.getXbox().getStartButton()) {
			stop();
		}
	}
	@Override
	public void disabledInit() {
		stop();
	}
	public void stop() {
		//stop all subsystems
		drivetrain.stop();
	}
}
