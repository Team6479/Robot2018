package org.usfirst.frc.team6479.robot.autonomous;

import org.usfirst.frc.team6479.robot.Robot;
import org.usfirst.frc.team6479.robot.autonomous.manager.StartPosition;
import org.usfirst.frc.team6479.robot.commands.auton.drive.GyroDrive;
import org.usfirst.frc.team6479.robot.commands.auton.drive.StraightDrive;

import edu.wpi.first.wpilibj.command.WaitCommand;

import openrio.powerup.MatchData;

public class Baseline extends BaseAutonomous {

	//nothing should be done
	public Baseline(StartPosition pos, boolean shouldUseSensors) {
		super(pos, shouldUseSensors);
	}

	//what happens when robot is positioned on the center
	@Override
	protected void center() {
		Robot.eventLogger.writeToLog("Baseline Center Autonomous");
		boolean isLeft = super.scale == MatchData.OwnedSide.LEFT;
		//Go straight 6 in short of the Power Cube Zone
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 48));
		//Turn 90 degrees toward alliances scale
		if (isLeft) {
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
		}
		else {
			addSequential(new GyroDrive(90, GyroDrive.Direction.dRight));
		}
		//Straighten out to drive forward toward switch
		if (isLeft) {
			//Go straight 36 in past the switch border
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 127.75));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dRight));
		}
		else {
			//Go straight 36 in past the switch border
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 96.75));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
		}
		//Cross baseline and approach scale while staying out of null territory
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 192));
	}

	//what happens when robot is positioned on the left
	@Override
	protected void left() {
		Robot.eventLogger.writeToLog("Baseline Left Autonomous");
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 190));
		addSequential(new WaitCommand(0.25));
	}

	//what happens when robot is positioned on the right
	@Override
	protected void right() {
		Robot.eventLogger.writeToLog("Baseline Right Autonomous");
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 190));
		addSequential(new WaitCommand(0.25));
	}

	//what happens when robot is positioned in the center
	@Override
	protected void deadReckonCenter() {
		Robot.eventLogger.writeToLog("DEADRECKON Baseline Center Autonomous");
	}

	//what happens when robot is positioned on the left
	@Override
	protected void deadReckonLeft() {
		Robot.eventLogger.writeToLog("DEADRECKON Baseline Left Autonomous");
	}

	//what happens when robot is positioned on the right
	@Override
	protected void deadReckonRight() {
		Robot.eventLogger.writeToLog("DEADRECKON Baseline Right Autonomous");
	}

}
