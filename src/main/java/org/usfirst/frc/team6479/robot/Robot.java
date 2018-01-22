package org.usfirst.frc.team6479.robot;

import edu.wpi.first.wpilibj.command.Scheduler;
import org.usfirst.frc.team6479.robot.control.OI;
import org.usfirst.frc.team6479.robot.subsystems.Drivetrain;
import org.usfirst.frc.team6479.robot.subsystems.Elevator;
import org.usfirst.frc.team6479.robot.subsystems.Grabber;
import robot.base.TimedIterativeRobot;

public class Robot extends TimedIterativeRobot {

    public static OI oi;
	public static Drivetrain drivetrain;
	public static Elevator elevator;
	public static Grabber grabber;

	@Override
	public void robotInit() {

		//init the controls in oi
		oi = new OI();

		//init subsystems
		drivetrain = new Drivetrain();
		elevator = new Elevator();
		grabber = new Grabber();

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
