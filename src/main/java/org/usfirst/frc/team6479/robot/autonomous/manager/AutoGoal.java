package org.usfirst.frc.team6479.robot.autonomous.manager;

//the goal of the robot
public enum AutoGoal {
	a_baseline("BASELINE"), 
	a_switch("SWITCH"),
	a_d_switch("DOUBLE_SWITCH"),
	a_scale("SCALE");

	private AutoGoal(String key) {
		this.key = key;
	}

	private String key;
	
	public String getKey() {
		return key;
	}

	static final String name = "Auto Goal";
}