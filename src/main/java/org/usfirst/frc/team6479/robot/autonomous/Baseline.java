package org.usfirst.frc.team6479.robot.autonomous;

import edu.wpi.first.wpilibj.command.WaitCommand;
import openrio.powerup.MatchData;
import org.usfirst.frc.team6479.robot.autonomous.manager.StartPosition;
import org.usfirst.frc.team6479.robot.commands.auton.GyroDrive;
import org.usfirst.frc.team6479.robot.commands.auton.StraightDrive;

public class Baseline extends BaseAutonomous {

	//nothing should be done
	public Baseline(StartPosition pos) {
		super(pos);
	}

	//what happens when robot is positioned on the center
	@Override
	protected void center() {
		boolean isLeft = super.scale == MatchData.OwnedSide.LEFT;
		System.out.println("Baseline Center Autonomous");
		//Go straight 6 in short of the Power Cube Zone
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 72));
		//Turn 90 degrees toward alliances scale
		if (isLeft) {
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
		}
		else {
			addSequential(new GyroDrive(90, GyroDrive.Direction.dRight));
		}
		//Go straight 36 in past the switch border
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 112.75));
		//Straighten out to drive forward toward switch
		if (isLeft) {
			addSequential(new GyroDrive(90, GyroDrive.Direction.dRight));
		}
		else {
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
		}
		//Cross baseline and approach scale while staying out of null territory
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 192));
	}

	//what happens when robot is positioned on the left
	@Override
	protected void left() {
		System.out.println("Baseline Left Autonomous");
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 240));
		addSequential(new WaitCommand(0.25));
	}

	//what happens when robot is positioned on the right
	@Override
	protected void right() {
		System.out.println("Baseline Right Autonomous");
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 240));
		addSequential(new WaitCommand(0.25));
	}

}
