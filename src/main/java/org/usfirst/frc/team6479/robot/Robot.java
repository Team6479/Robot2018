package org.usfirst.frc.team6479.robot;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.usfirst.frc.team6479.robot.autonomous.manager.AutonomousManager;
import org.usfirst.frc.team6479.robot.commands.auton.GrabCube;
import org.usfirst.frc.team6479.robot.control.OI;
import org.usfirst.frc.team6479.robot.logger.DataLogger;
import org.usfirst.frc.team6479.robot.logger.EventLogger;
import org.usfirst.frc.team6479.robot.logger.RobotEvent;
import org.usfirst.frc.team6479.robot.subsystems.Camera;
import org.usfirst.frc.team6479.robot.subsystems.Drivetrain;
import org.usfirst.frc.team6479.robot.subsystems.Elevator;
import org.usfirst.frc.team6479.robot.subsystems.Grabber;
import org.usfirst.frc.team6479.robot.subsystems.Pusher;
import org.usfirst.frc.team6479.robot.subsystems.SafeSubsystem;
import org.usfirst.frc.team6479.robot.subsystems.WheelyBar;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.xbox.ButtonTracker;

public class Robot extends IterativeRobot {

    public static OI oi;
	public static Drivetrain drivetrain;
	public static Elevator elevator;
	public static Grabber grabber;
	public static Pusher pusher;
	public static WheelyBar wheely;
	public static Camera camera;
	private static Map<String, SafeSubsystem> subsystemManager;
	private static AutonomousManager autoManager;
	public static Compressor compressor;
	private static int ticks;
	//private PowerDistributionPanel pdp;


	private static DataLogger driveLog;
	//booleans are parsed as t and f
	public void driveLog() {
		LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
		//drivetrain
		data.put("LeftSpeed", String.format("%+01.2f", drivetrain.getLeftSideMotors().get()));
		data.put("RightSpeed", String.format("%+01.2f", drivetrain.getRightSideMotors().get()));
		data.put("LeftDistance", String.format("%+05.2f", drivetrain.getEncoder().getLeft().getDistance()));
		data.put("RightDistance", String.format("%+05.2f", drivetrain.getEncoder().getRight().getDistance()));
		data.put("LeftVelocity", String.format("%+05.2f", drivetrain.getEncoder().getLeft().getRate()));
		data.put("RightVelocity", String.format("%+05.2f", drivetrain.getEncoder().getRight().getRate()));
		data.put("Gyro", String.format("%+04.2f", drivetrain.getGyro().getAngle()));
		data.put("LeftSonar", String.format("%+02.2f", drivetrain.getUltrasonic().getLeft()));
		data.put("RightSonar", String.format("%+02.2f", drivetrain.getUltrasonic().getRight()));

		//elevator
		data.put("Winch", String.format("%+01.2f", elevator.getWinch().get()));
		data.put("ElevatorHeight", String.format("%+05.2f", elevator.getEncoder().getDistance()));
		data.put("ElevatorVelocity", String.format("%+05.2f", elevator.getEncoder().getRate()));
		data.put("GearboxToWinch", elevator.isOnWinch() ? "T" : "F");
		data.put("WinchLocked", elevator.isLocked() ? "T" : "F");

		//grabber
		data.put("Grabbing", grabber.isGrabbing() ? "T" : "F");

		//pusher
		data.put("Pushing", pusher.isExtend() ? "T" : "F");

		//wheely bar
		data.put("WheelyBarDown", wheely.isDown() ? "T" : "F");

		//camera
		data.put("LightOn", camera.isLightOn() ? "T" : "F");
		data.put("CameraMode", camera.currentCameraMode().name());
		data.put("CameraDistance", String.format("%+02.2f", camera.getCurrentDistance()));

		//power
		/*data.put("PDPTemperature", String.format("%+02.2f", pdp.getTemperature()));
		data.put("PDPTotalCurrent", String.format("%+02.2f", pdp.getTotalCurrent()));
		data.put("PDPTotalPower", String.format("%+02.2f", pdp.getTotalPower()));
		data.put("PDPInputPower", String.format("%+02.2f", pdp.getVoltage()));*/
		data.put("BatteryVoltage", String.format("%+02.2f", RobotController.getBatteryVoltage()));
		// TODO: possibly change to an event, not data
		data.put("BrownedOut", RobotController.isBrownedOut() ? "T" : "F");
		data.put("RIOInputCurrent", String.format("%+02.2f", RobotController.getInputCurrent()));
		data.put("RIOInputPower", String.format("%+02.2f", RobotController.getInputVoltage()));

		driveLog.setLogInfo(data);
	}

	public static EventLogger eventLogger;

	@Override
	public void robotInit() {
		ticks = 0;

		eventLogger = new EventLogger();
		//log init to screen
		eventLogger.shouldConsoleLog(true);
		eventLogger.writeToLog(RobotEvent.ROBOT_START);

		//init subsystems
		drivetrain = new Drivetrain();
		eventLogger.writeToLog(RobotEvent.START_GYRO_CALIBRATE);
		drivetrain.getGyro().calibrate();
		eventLogger.writeToLog(RobotEvent.GYRO_CALIBRATED);
		elevator = new Elevator();
		grabber = new Grabber();
		pusher = new Pusher();
		wheely = new WheelyBar();
		camera = new Camera();

		setRobotDefault();

		subsystemManager = new HashMap<String, SafeSubsystem>();
		subsystemManager.put("Drivetrain", drivetrain);
		subsystemManager.put("Elevator", elevator);
		subsystemManager.put("Grabber", grabber);
		subsystemManager.put("Pusher", pusher);
		subsystemManager.put("Wheely", wheely);
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

        //pdp = new PowerDistributionPanel();

		driveLog = new DataLogger(100);

        eventLogger.writeToLog(RobotEvent.ROBOT_INIT);
        SmartDashboard.putBoolean("Log to screen", true);
	}

	public void setRobotDefault() {
		drivetrain.setLimiter(false);
		drivetrain.setHyper(false);
		elevator.switchToWinch();
		elevator.unlock();
		pusher.retract();
		grabber.release();
		wheely.up();
	}
	public void setAutonomousDefault() {
		drivetrain.setLimiter(false);
		drivetrain.setHyper(true);
		elevator.switchToWinch();
		elevator.unlock();
		pusher.extend();
		grabber.release();
		wheely.up();
	}
	public void setTeleopDefault() {
		drivetrain.setLimiter(true);
		drivetrain.setHyper(false);
		elevator.switchToWinch();
		elevator.unlock();
		pusher.retract();
		grabber.release();
		wheely.up();
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
		SmartDashboard.putNumber("Gyro", Robot.drivetrain.getGyro().getAngle());

		SmartDashboard.putBoolean("Grabber:", grabber.isGrabbing());
		SmartDashboard.putBoolean("Pusher:", pusher.isExtend());
		SmartDashboard.putBoolean("Locked:", elevator.isLocked());
		SmartDashboard.putBoolean("Winch:", elevator.isOnWinch());

		//System.out.println(Robot.camera.getCurrentDistance());

		SmartDashboard.putBoolean("Pressure:", compressor.getPressureSwitchValue());

		driveLog();
	}
	@Override
	public void autonomousInit() {
		eventLogger.shouldConsoleLog(SmartDashboard.getBoolean("Log to screen", false));
		eventLogger.writeToLog(RobotEvent.AUTO_START);
		driveLog.start();
		setAutonomousDefault();
		autoManager.startAuto();
	}
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	@Override
	public void teleopInit() {
		eventLogger.shouldConsoleLog(SmartDashboard.getBoolean("Log to screen", false));
		eventLogger.writeToLog(RobotEvent.TELE_START);
		driveLog.start();
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
		eventLogger.writeToLog(RobotEvent.ROBOT_DISABLED);
		driveLog.stop();
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
