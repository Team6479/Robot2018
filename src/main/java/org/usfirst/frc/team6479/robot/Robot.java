package org.usfirst.frc.team6479.robot;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.usfirst.frc.team6479.robot.autonomous.manager.AutonomousManager;
import org.usfirst.frc.team6479.robot.config.RobotMap;
import org.usfirst.frc.team6479.robot.control.OI;
import org.usfirst.frc.team6479.robot.logger.RobotLogger;
import org.usfirst.frc.team6479.robot.subsystems.Camera;
import org.usfirst.frc.team6479.robot.subsystems.Drivetrain;
import org.usfirst.frc.team6479.robot.subsystems.Elevator;
import org.usfirst.frc.team6479.robot.subsystems.Grabber;
import org.usfirst.frc.team6479.robot.subsystems.Pusher;
import org.usfirst.frc.team6479.robot.subsystems.SafeSubsystem;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import robot.xbox.ButtonTracker;

public class Robot extends IterativeRobot {

    public static OI oi;
	public static Drivetrain drivetrain;
	public static Elevator elevator;
	public static Grabber grabber;
	public static Pusher pusher;
	public static Camera camera;
	private static Map<String, SafeSubsystem> subsystemManager;
	private static AutonomousManager autoManager;
	public static Compressor compressor;
	private static int ticks;

	@Override
	public void robotInit() {
		ticks = 0;

		//init subsystems
		drivetrain = new Drivetrain();
		elevator = new Elevator();
		grabber = new Grabber();
		pusher = new Pusher();
		camera = new Camera();

		setRobotDefault();

		subsystemManager = new HashMap<String, SafeSubsystem>();
		subsystemManager.put("Drivetrain", drivetrain);
		subsystemManager.put("Elevator", elevator);
		subsystemManager.put("Grabber", grabber);
		subsystemManager.put("Pusher", pusher);
		subsystemManager.put("Camera", camera);


		//init the controls in oi
        oi = new OI();

        autoManager = new AutonomousManager();

        compressor = new Compressor();
        compressor.setClosedLoopControl(true);


        //Initialize SmartDashboard tracking
        //IMPORTANT: THIS NEEDS TO BE LAST!
        //Drivetrain
        SmartDashboard.putData("Drivetrain", Robot.drivetrain.getDrive());
	}

	public void setRobotDefault() {
		drivetrain.setLimiter(false);
		drivetrain.setHyper(false);
		elevator.switchToWinch();
		elevator.unlock();
		pusher.retract();
		grabber.release();
	}
	public void setAutonomousDefault() {
		drivetrain.setLimiter(false);
		drivetrain.setHyper(true);
		elevator.switchToWinch();
		elevator.unlock();
		pusher.retract();
		grabber.release();
	}
	public void setTeleopDefault() {
		drivetrain.setLimiter(true);
		drivetrain.setHyper(false);
		elevator.switchToWinch();
		elevator.unlock();
		pusher.retract();
		grabber.release();
	}

	@Override
	public void robotPeriodic() {
		ticks++;

	    ButtonTracker.updateAll();

	    SmartDashboard.putData("encoder left", Robot.drivetrain.getEncoder().getLeft());
	    SmartDashboard.putData("encoder right", Robot.drivetrain.getEncoder().getRight());
	    SmartDashboard.putData("elevator encoder", Robot.elevator.getEncoder());
	    SmartDashboard.putNumber("Sonar left", Robot.drivetrain.getUltrasonic().getLeft());
		SmartDashboard.putNumber("Sonar Right", Robot.drivetrain.getUltrasonic().getRight());

		RobotLogger.logger.log("Velocity: Left: " + drivetrain.getEncoder().getLeft().getRate()
			+ " Velocity: Right: " + drivetrain.getEncoder().getRight().getRate()
			+ " Distance: Left: " + drivetrain.getEncoder().getLeft().getDistancePerPulse()
			+ " Distance: Right: " + drivetrain.getEncoder().getLeft().getDistancePerPulse());
	}
	@Override
	public void autonomousInit() {
		setAutonomousDefault();
		autoManager.startAuto();
	}
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	@Override
	public void teleopInit() {
		//deque all commands
		Scheduler.getInstance().removeAll();

		setTeleopDefault();
	}
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		if(oi.getDriverController().getAButton()) {
			drivetrain.setHyper(true);
		}
		else {
			drivetrain.setHyper(false);
		}

		//kill switch code
		if(oi.getDriverController().getStartButton()) {
			stop();
		}
	}
	@Override
	public void disabledInit() {
		stop();
	}
	public void stop() {
		//stop all subsystems
        for(Entry<String, SafeSubsystem> entry: subsystemManager.entrySet()) {
        		entry.getValue().stop();
        }
	}

	public static int getCurrentTick() {
		return ticks;
	}
}
