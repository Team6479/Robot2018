package org.usfirst.frc.team6479.robot.control;

import org.usfirst.frc.team6479.robot.config.RobotMap;

import edu.wpi.first.wpilibj.XboxController;

public class OI {
	
	//all things controlled by this singleton class
	private XboxController xboxDriver;
	
	//initilaiztion
	public OI() {
		xboxDriver = new XboxController(RobotMap.xbox);
	}

	//get the things controlled
	public XboxController getXboxDriver() {
		return xboxDriver;
	}

}
