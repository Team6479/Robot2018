package org.usfirst.frc.team6479.robot;

import edu.wpi.first.wpilibj.command.Scheduler;
import robot.base.TimedIterativeRobot;

import org.usfirst.frc.team6479.robot.control.OI;
import org.usfirst.frc.team6479.robot.subsystems.Drivetrain;

public class Robot extends TimedIterativeRobot {

	public static Drivetrain drivetrain;
	public static OI oi;

	@Override
	public void robotInit() {
		drivetrain = new Drivetrain();
		oi = new OI();
		
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
		if(oi.getXboxDriver().getStartButton()) {
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
