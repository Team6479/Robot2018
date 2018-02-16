package org.usfirst.frc.team6479.robot.control;

import org.usfirst.frc.team6479.robot.commands.auton.camera.ToggleLight;
import org.usfirst.frc.team6479.robot.commands.auton.drive.CameraDrive;
import org.usfirst.frc.team6479.robot.commands.auton.drive.FlushTurn;
import org.usfirst.frc.team6479.robot.commands.auton.drive.StraightDrive;
import org.usfirst.frc.team6479.robot.commands.teleop.ToggleGrabber;
import org.usfirst.frc.team6479.robot.commands.teleop.TogglePusher;
import org.usfirst.frc.team6479.robot.commands.teleop.ToggleShifter;
import org.usfirst.frc.team6479.robot.commands.teleop.ToggleStopper;
import org.usfirst.frc.team6479.robot.config.RobotMap;
import org.usfirst.frc.team6479.robot.util.XboxControllerDeadzone;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.xbox.ButtonTracker;
import robot.xbox.XboxMap;

public class OI {
	private XboxControllerDeadzone driverController;
	private XboxControllerDeadzone assistantController;
	private ButtonTracker driverRightBumper;
	private ButtonTracker driverLeftBumper;
	private ButtonTracker driverXButton;
	private ButtonTracker driverBButton;
	private ButtonTracker assistantRightBumper;
	private ButtonTracker assistantLeftBumper;
	private ButtonTracker assistantXButton;
	private ButtonTracker assistantBButton;

	//initialization
	public OI() {
		driverController = new XboxControllerDeadzone(RobotMap.driverController);
		assistantController = new XboxControllerDeadzone(RobotMap.assistantController);

		//Driver Button Trackers
		/*
		driverRightBumper = new ButtonTracker(driverController, XboxMap.RightBumper);
		driverRightBumper.toggleWhenPressed(new ToggleGrabber());
	    driverLeftBumper = new ButtonTracker(driverController, XboxMap.LeftBumper);
	    driverLeftBumper.toggleWhenPressed(new TogglePusher());
	    driverXButton = new ButtonTracker(driverController, XboxMap.XButton);
	    driverXButton.toggleWhenPressed(new ToggleStopper());
	    driverBButton = new ButtonTracker(driverController, XboxMap.BButton);
	    driverBButton.toggleWhenPressed(new ToggleShifter());
	    */

	    //Assistant Button Trackers
	    assistantLeftBumper = new ButtonTracker(assistantController, XboxMap.LeftBumper);
	    assistantLeftBumper.toggleWhenPressed(new ToggleGrabber());
	    assistantRightBumper = new ButtonTracker(assistantController, XboxMap.RightBumper);
	    assistantRightBumper.toggleWhenPressed(new TogglePusher());
	    assistantXButton = new ButtonTracker(assistantController, XboxMap.XButton);
	    assistantXButton.toggleWhenPressed(new ToggleStopper());
	    assistantBButton = new ButtonTracker(assistantController, XboxMap.BButton);
	    assistantBButton.toggleWhenPressed(new ToggleShifter());

	    //Testing Commands
        SmartDashboard.putData("Camera Drive", new CameraDrive());
        SmartDashboard.putData("Camera Turn", new CameraDrive());
        SmartDashboard.putData("Encoder Drive", new StraightDrive(StraightDrive.Mode.encoderDrive, 30));
        SmartDashboard.putData("Sonar Drive", new StraightDrive(StraightDrive.Mode.encoderDrive, 30));
        SmartDashboard.putData("Toggle Light", new ToggleLight());
        SmartDashboard.putData("Flush Drive", new FlushTurn());
	}

	//get the things controlled
	public XboxControllerDeadzone getDriverController() {
		return driverController;
	}
	public XboxControllerDeadzone getAssistantController() {
		return assistantController;
	}
	public ButtonTracker getAssistantRightBumper() {
	    return assistantRightBumper;
	}
	public ButtonTracker getAssistantLeftBumper() {
	    return assistantLeftBumper;
	}
	public ButtonTracker getAssistantXButton() {
	    return assistantXButton;
	}
	public ButtonTracker getAssistantBButton() {
	    return assistantBButton;
	}

}
