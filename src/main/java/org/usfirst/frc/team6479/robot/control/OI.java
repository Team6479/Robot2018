package org.usfirst.frc.team6479.robot.control;

import org.usfirst.frc.team6479.robot.config.RobotMap;

import edu.wpi.first.wpilibj.XboxController;

public class OI {
	
	//initilaiztion
	public OI() {
		xbox = new XboxController(RobotMap.xboxPort);
	}
	
	private XboxController xbox;

	//get the things controlled
	public XboxController getXbox() {
		return xbox;
	}

}
