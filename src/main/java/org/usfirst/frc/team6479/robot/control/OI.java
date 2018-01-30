package org.usfirst.frc.team6479.robot.control;

import org.usfirst.frc.team6479.robot.commands.auton.CameraDrive;
import org.usfirst.frc.team6479.robot.commands.auton.EncoderDrive;
import org.usfirst.frc.team6479.robot.commands.auton.GyroDrive;
import org.usfirst.frc.team6479.robot.commands.auton.ToggleLight;
import org.usfirst.frc.team6479.robot.commands.teleop.ToggleGrabber;
import org.usfirst.frc.team6479.robot.commands.teleop.TogglePusher;
import org.usfirst.frc.team6479.robot.config.RobotMap;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.xbox.ButtonTracker;
import robot.xbox.XboxMap;

public class OI {

	//initialization
	public OI() {
		xbox = new XboxController(RobotMap.xboxPort);
		rightBumper = new ButtonTracker(xbox, XboxMap.RightBumper);
		rightBumper.toggleWhenPressed(new ToggleGrabber());
	    leftBumper = new ButtonTracker(xbox, XboxMap.LeftBumper);
	    leftBumper.toggleWhenPressed(new TogglePusher());
	    
	    //testing code for auto commands
	    SmartDashboard.putData("Drive Forward", new EncoderDrive(10, EncoderDrive.direction.dForward));
	    SmartDashboard.putData("Turn", new GyroDrive(15, GyroDrive.direction.dRight));
	    SmartDashboard.putData("Toggle Light", new ToggleLight());
	    SmartDashboard.putData("Turn on Camera", new CameraDrive());
	}

	private XboxController xbox;
	private ButtonTracker rightBumper;
	private ButtonTracker leftBumper;

	//get the things controlled
	public XboxController getXbox() {
		return xbox;
	}

}
