package org.usfirst.frc.team6479.robot.autonomous.manager;

//position the robot starts in
public enum StartPosition {
	s_left("LEFT"),
	s_right("RIGHT"),
	s_center("CENTER");
	
	private StartPosition(String key) {
		this.key = key;
	}
	
	private String key;
	
	public String getKey() {
		return key;
	}
	
	static final String name = "Start Position";
}