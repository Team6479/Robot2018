package org.usfirst.frc.team6479.robot.control;

import org.usfirst.frc.team6479.robot.commandgroups.CameraTurnGetCube;
import org.usfirst.frc.team6479.robot.commands.auton.CameraDrive;
import org.usfirst.frc.team6479.robot.commands.auton.GyroDrive;
import org.usfirst.frc.team6479.robot.commands.auton.SonarDrive;
import org.usfirst.frc.team6479.robot.commands.auton.ToggleLight;
import org.usfirst.frc.team6479.robot.commands.deadreckoning.DeadReckonDrive;
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
	    SmartDashboard.putData("Drive Forward", new SonarDrive(10, SonarDrive.Direction.dForward));
	    SmartDashboard.putData("Turn", new GyroDrive(90, GyroDrive.Direction.dRight));
	    SmartDashboard.putData("Toggle Light", new ToggleLight());
	    SmartDashboard.putData("Turn on Camera", new CameraDrive());
	    SmartDashboard.putData("GET DA CUBE", new CameraTurnGetCube());
	    
	    SmartDashboard.putData("Dead Reckon Forward", new DeadReckonDrive(2, 0.5, DeadReckonDrive.Direction.dForward));
	    SmartDashboard.putData("Dead Reckon Backward", new DeadReckonDrive(2, 0.5, DeadReckonDrive.Direction.dBackward));
	    SmartDashboard.putData("Dead Reckon Right", new DeadReckonDrive(2, 0.5, DeadReckonDrive.Direction.dRight));
	    SmartDashboard.putData("Dead Reckon Left", new DeadReckonDrive(2, 0.5, DeadReckonDrive.Direction.dLeft));
	    
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
