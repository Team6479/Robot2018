package org.usfirst.frc.team6479.robot;

import edu.wpi.first.wpilibj.command.Scheduler;

import org.usfirst.frc.team6479.robot.connection.JetsonServer;
import org.usfirst.frc.team6479.robot.control.OI;
import org.usfirst.frc.team6479.robot.subsystems.Drivetrain;
import org.usfirst.frc.team6479.robot.subsystems.Elevator;
import org.usfirst.frc.team6479.robot.subsystems.Grabber;
import org.usfirst.frc.team6479.robot.subsystems.Pusher;

import communication.JetsonPacket.ModePacket;
import robot.base.TimedIterativeRobot;
import robot.xbox.ButtonTracker;

public class Robot extends TimedIterativeRobot {

    public static OI oi;
	public static Drivetrain drivetrain;
	public static Elevator elevator;
	public static Grabber grabber;
	public static Pusher pusher;
	public static JetsonServer server;

	@Override
	public void robotInit() {

		//init the controls in oi
		oi = new OI();

		//init subsystems
		drivetrain = new Drivetrain();
		elevator = new Elevator();
		grabber = new Grabber();
		pusher = new Pusher();
		
		server = new JetsonServer(1182);
	    server.setMode(ModePacket.Mode.CUBE);
	}
	@Override
	public void robotPeriodic() {
	    ButtonTracker.updateAll();
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
