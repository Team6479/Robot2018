package org.usfirst.frc.team6479.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.IterativeRobotBase;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6479.robot.control.OI;
import org.usfirst.frc.team6479.robot.subsystems.Camera;
import org.usfirst.frc.team6479.robot.subsystems.Drivetrain;
import org.usfirst.frc.team6479.robot.subsystems.Elevator;
import org.usfirst.frc.team6479.robot.subsystems.Grabber;
import org.usfirst.frc.team6479.robot.subsystems.Pusher;

import communication.JetsonPacket.ModePacket.Mode;
import robot.base.TimedIterativeRobot;
import robot.xbox.ButtonTracker;

public class Robot extends IterativeRobot {

    public static OI oi;
	public static Drivetrain drivetrain;
	public static Elevator elevator;
	public static Grabber grabber;
	public static Pusher pusher;
	public static Camera camera;

	@Override
	public void robotInit() {

		//init subsystems
		drivetrain = new Drivetrain();
		elevator = new Elevator();
		grabber = new Grabber();
		pusher = new Pusher();
		
		camera = new Camera();
		camera.getJetson().setMode(Mode.CUBE);
		
	      //init the controls in oi
        oi = new OI();
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

        
        SmartDashboard.putNumber("Range", drivetrain.getSonar().getDistance());

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
	    //TODO: need to find a better way of doing this, very very error prone
		drivetrain.stop();
		elevator.stop();
        grabber.stop();
        pusher.stop();
        camera.stop();
	}
}
