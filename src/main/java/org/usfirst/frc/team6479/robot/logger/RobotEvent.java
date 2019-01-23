package org.usfirst.frc.team6479.robot.logger;

public class RobotEvent {
	protected RobotEvent(String message) {
		this.message = message;
	}
	private String message;
	public String getMessage() {
		return message;
	}
	
	//robot mode changed
	public static final RobotEvent ROBOT_START = new RobotEvent("Robot code starting");
	public static final RobotEvent ROBOT_INIT = new RobotEvent("Robot code is initialized"); 
	public static final RobotEvent AUTO_START = new RobotEvent("Autonomous started");
	public static final RobotEvent TELE_START = new RobotEvent("Teleop started");
	public static final RobotEvent ROBOT_DISABLED = new RobotEvent("Robot disabled");
	
	//sensor calibration
	public static final RobotEvent START_GYRO_CALIBRATE = new RobotEvent("Gyro calibrating");
	public static final RobotEvent GYRO_CALIBRATED = new RobotEvent("Gyro calibrated");
	
	//jetson
	public static final RobotEvent START_JETSON_CONNECTION = new RobotEvent("Camera server is waiting for a new client");
	public static final RobotEvent JETSON_CONNECTED = new RobotEvent("Camera server is connected to client");
	public static final RobotEvent JETSON_DISCONNECTED = new RobotEvent("Camera server has disconnected from client");
	public static final RobotEvent JETSON_FAILED = new RobotEvent("Camera server failed to connect");
	
}