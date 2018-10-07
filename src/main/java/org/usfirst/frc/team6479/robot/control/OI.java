package org.usfirst.frc.team6479.robot.control;

import org.usfirst.frc.team6479.robot.commands.Reset;
import org.usfirst.frc.team6479.robot.commands.auton.drive.StraightDrive;
import org.usfirst.frc.team6479.robot.commands.auton.elevator.MoveElevator;
import org.usfirst.frc.team6479.robot.commands.auton.intake.GrabberSpit;
import org.usfirst.frc.team6479.robot.commands.auton.intake.GrabberStop;
import org.usfirst.frc.team6479.robot.commands.auton.intake.GrabberSuck;
import org.usfirst.frc.team6479.robot.commands.teleop.ToggleGrabberSuck;
import org.usfirst.frc.team6479.robot.config.RobotMap;
import org.usfirst.frc.team6479.robot.util.XboxControllerDeadzone;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.xbox.ButtonTracker;
import robot.xbox.XboxMap;

public class OI {
	private XboxControllerDeadzone driverController;
	private ButtonTracker driverRightBumper;
	private ButtonTracker driverLeftBumper;
	private ButtonTracker driverBButton;

	//initialization
	public OI() {
		driverController = new XboxControllerDeadzone(RobotMap.driverController);

		//Driver Button Trackers
		driverRightBumper = new ButtonTracker(driverController, XboxMap.RightBumper);
	    driverLeftBumper = new ButtonTracker(driverController, XboxMap.LeftBumper);
	    driverBButton = new ButtonTracker(driverController, XboxMap.BButton);


	    //Testing Commands
        /*SmartDashboard.putData("Camera Drive", new CameraDrive());
        SmartDashboard.putData("Camera Turn", new CameraDrive());
        SmartDashboard.putData("Encoder Drive", new StraightDrive(StraightDrive.Mode.encoderDrive, 30));
        SmartDashboard.putData("Sonar Drive", new StraightDrive(StraightDrive.Mode.sonarDrive, 50));
        SmartDashboard.putData("Flush Turn", new FlushTurn());
		SmartDashboard.putData("GYRO: 90 degree", new GyroDrive(90, GyroDrive.Direction.dLeft));*/
		//SmartDashboard.putData("LIT BOI", new ToggleLight());
		// SmartDashboard.putData("3 feet", new StraightDrive(StraightDrive.Mode.encoderDrive, 36));
		// SmartDashboard.putData("20 feet", new StraightDrive(StraightDrive.Mode.encoderDrive, 240));
		//SmartDashboard.putData("3 seconds at 50 inches/sec", new VelocityDrive(3, 50));
		//SmartDashboard.putData("To 3 feet", new StraightDrive(StraightDrive.Mode.sonarDrive, 36));
		//SmartDashboard.putData("90 degrees", new GyroDrive(90, GyroDrive.Direction.dLeft));
		//SmartDashboard.putData("Flush", new FlushTurn());
		SmartDashboard.putData("RESET", new Reset());
		// SmartDashboard.putData("Restart Jetson", new RestartJetsonCode());
		// SmartDashboard.putData("Move Elevator", new MoveElevator(MoveElevator.PreSetHeight.Switch));
		// SmartDashboard.putData("Move Elev To Scale", new MoveElevator(MoveElevator.PreSetHeight.Scale));
		//SmartDashboard.putData("Grab Cube Auto", new GrabCube());

		SmartDashboard.putData("Grabber Suck", new GrabberSuck());
		SmartDashboard.putData("Grabber Spit", new GrabberSpit());
		SmartDashboard.putData("Grabber Stop", new GrabberStop());
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
	public ButtonTracker getDriverBButton() {
		return driverBButton;
	}

}
