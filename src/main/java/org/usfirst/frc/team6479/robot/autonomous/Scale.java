package org.usfirst.frc.team6479.robot.autonomous;

import org.usfirst.frc.team6479.robot.autonomous.manager.StartPosition;
import org.usfirst.frc.team6479.robot.commands.auton.drive.GyroDrive;
import org.usfirst.frc.team6479.robot.commands.auton.drive.StraightDrive;
import org.usfirst.frc.team6479.robot.commands.auton.elevator.MoveElevator;

import openrio.powerup.MatchData;

public class Scale extends BaseAutonomous {
	private boolean isLeft;

	//nothing should be done
	public Scale(StartPosition pos, boolean shouldUseSensors) {
		super(pos, shouldUseSensors);
		isLeft = super.scale == MatchData.OwnedSide.LEFT;
	}

	//what happens when robot is positioned on the center
	@Override
	protected void center() {
		System.out.println("Scale Center Autonomous");
	}

	//what happens when robot is positioned on the left
	@Override
	protected void left() {
		System.out.println("Scale Left Autonomous");

		//Drive forward 20 ft.
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 240));

		if (isLeft) {
			//Drive forward 7 ft.
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 84));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dRight));
		}
		else {
			addSequential(new GyroDrive(90, GyroDrive.Direction.dRight));
			//Go straight 23 ft.
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive,276));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
			//Drive forward 7 ft.
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 84));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
		}

		addSequential(new MoveElevator(MoveElevator.PreSetHeight.Scale));
	}

	//what happens when robot is positioned on the right
	@Override
	protected void right() {
		System.out.println("Scale Right Autonomous");
	}

	//what happens when robot is positioned in the center
	@Override
	protected void deadReckonCenter() {
		System.out.println("DEADRECKON Scale Center Autonomous");
	}

	//what happens when robot is positioned on the left
	@Override
	protected void deadReckonLeft() {
		System.out.println("DEADRECKON Scale Left Autonomous");
	}

	//what happens when robot is positioned on the right
	@Override
	protected void deadReckonRight() {
		System.out.println("DEADRECKON Scale Right Autonomous");
	}

}
