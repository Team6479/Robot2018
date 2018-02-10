package org.usfirst.frc.team6479.robot.control;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6479.robot.commands.auton.CameraDrive;
import org.usfirst.frc.team6479.robot.commands.auton.FlushDrive;
import org.usfirst.frc.team6479.robot.commands.auton.StraightDrive;
import org.usfirst.frc.team6479.robot.commands.auton.ToggleLight;
import org.usfirst.frc.team6479.robot.commands.teleop.ToggleGrabber;
import org.usfirst.frc.team6479.robot.commands.teleop.TogglePusher;
import org.usfirst.frc.team6479.robot.config.RobotMap;
import org.usfirst.frc.team6479.robot.util.XboxControllerDeadzone;
import robot.xbox.ButtonTracker;
import robot.xbox.XboxMap;

public class OI {

	//initialization
	public OI() {
		xbox = new XboxControllerDeadzone(RobotMap.xboxPort);
		rightBumper = new ButtonTracker(xbox, XboxMap.RightBumper);
		rightBumper.toggleWhenPressed(new ToggleGrabber());
	    leftBumper = new ButtonTracker(xbox, XboxMap.LeftBumper);
	    leftBumper.toggleWhenPressed(new TogglePusher());

	    //Testing Commands
        SmartDashboard.putData("Camera Drive", new CameraDrive());
        SmartDashboard.putData("Camera Turn", new CameraDrive());
        SmartDashboard.putData("Encoder Drive", new StraightDrive(StraightDrive.Mode.encoderDrive, 30));
        SmartDashboard.putData("Sonar Drive", new StraightDrive(StraightDrive.Mode.encoderDrive, 30));
        SmartDashboard.putData("Toggle Light", new ToggleLight());
        SmartDashboard.putData("Flush Drive", new FlushDrive());
	}

	private XboxController xbox;
	private ButtonTracker rightBumper;
	private ButtonTracker leftBumper;

	//get the things controlled
	public XboxController getXbox() {
		return xbox;
	}
	public ButtonTracker getRightBumper() {
	    return rightBumper;
	}
	public ButtonTracker getLeftBumper() {
	    return leftBumper;
	}

}
