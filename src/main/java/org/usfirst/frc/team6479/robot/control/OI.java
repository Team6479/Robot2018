package org.usfirst.frc.team6479.robot.control;

import org.usfirst.frc.team6479.robot.commands.ToggleGrabber;
import org.usfirst.frc.team6479.robot.commands.TogglePusher;
import org.usfirst.frc.team6479.robot.config.RobotMap;

import edu.wpi.first.wpilibj.XboxController;
import robot.xbox.ButtonTracker;
import robot.xbox.XboxMap;

public class OI {
	
	//initialization
	public OI() {
		xbox = new XboxController(RobotMap.xboxPort);
		rightBumper = new ButtonTracker(xbox, XboxMap.RightBumper);
		rightBumper.whenPressed(new ToggleGrabber());
	    leftBumper = new ButtonTracker(xbox, XboxMap.LeftBumper);
	    leftBumper.whenPressed(new TogglePusher());
	}
	
	private XboxController xbox;
	private ButtonTracker rightBumper;
	private ButtonTracker leftBumper;

	//get the things controlled
	public XboxController getXbox() {
		return xbox;
	}

}
