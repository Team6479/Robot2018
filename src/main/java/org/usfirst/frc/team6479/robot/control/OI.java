package org.usfirst.frc.team6479.robot.control;

import org.usfirst.frc.team6479.robot.commands.Reset;
import org.usfirst.frc.team6479.robot.commands.VelocityDrive;
import org.usfirst.frc.team6479.robot.commands.auton.camera.ToggleLight;
import org.usfirst.frc.team6479.robot.commands.auton.drive.FlushTurn;
import org.usfirst.frc.team6479.robot.commands.auton.drive.GyroDrive;
import org.usfirst.frc.team6479.robot.commands.auton.drive.StraightDrive;
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
	private ButtonTracker assistantRightBumper;
	private ButtonTracker assistantLeftBumper;
	private ButtonTracker assistantXButton;
	private ButtonTracker assistantBButton;

	//initialization
	public OI() {
		driverController = new XboxControllerDeadzone(RobotMap.driverController);
		assistantController = new XboxControllerDeadzone(RobotMap.assistantController);

		//Driver Button Trackers
		driverRightBumper = new ButtonTracker(driverController, XboxMap.RightBumper);
	    driverLeftBumper = new ButtonTracker(driverController, XboxMap.LeftBumper);


	    //Assistant Button Trackers
	    assistantLeftBumper = new ButtonTracker(assistantController, XboxMap.LeftBumper);
	    assistantRightBumper = new ButtonTracker(assistantController, XboxMap.RightBumper);
	    assistantXButton = new ButtonTracker(assistantController, XboxMap.XButton);
	    assistantXButton.toggleWhenPressed(new ToggleStopper());
	    assistantBButton = new ButtonTracker(assistantController, XboxMap.BButton);
	    assistantBButton.toggleWhenPressed(new ToggleShifter());

	    //Testing Commands
        /*SmartDashboard.putData("Camera Drive", new CameraDrive());
        SmartDashboard.putData("Camera Turn", new CameraDrive());
        SmartDashboard.putData("Encoder Drive", new StraightDrive(StraightDrive.Mode.encoderDrive, 30));
        SmartDashboard.putData("Sonar Drive", new StraightDrive(StraightDrive.Mode.sonarDrive, 50));
        SmartDashboard.putData("Flush Turn", new FlushTurn());
		SmartDashboard.putData("GYRO: 90 degree", new GyroDrive(90, GyroDrive.Direction.dLeft));*/
		SmartDashboard.putData("LIT BOI", new ToggleLight());
		SmartDashboard.putData("3 feet", new StraightDrive(StraightDrive.Mode.encoderDrive, 36));
		SmartDashboard.putData("3 seconds at 50 inches/sec", new VelocityDrive(3, 50));
		SmartDashboard.putData("To 3 feet", new StraightDrive(StraightDrive.Mode.sonarDrive, 36));
		SmartDashboard.putData("90 degrees", new GyroDrive(90, GyroDrive.Direction.dLeft));
		SmartDashboard.putData("Flush", new FlushTurn());
		SmartDashboard.putData("RESET", new Reset());
	}

	//get the things controlled
	public XboxControllerDeadzone getDriverController() {
		return driverController;
	}
	public ButtonTracker getDriverLeftBumper() {
		return driverLeftBumper;
	}
	public ButtonTracker getDriverRightBumper() {
		return driverRightBumper;
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
