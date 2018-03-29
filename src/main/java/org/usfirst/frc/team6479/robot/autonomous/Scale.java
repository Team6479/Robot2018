package org.usfirst.frc.team6479.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team6479.robot.Robot;
import org.usfirst.frc.team6479.robot.autonomous.manager.StartPosition;
import org.usfirst.frc.team6479.robot.commands.auton.drive.GyroDrive;
import org.usfirst.frc.team6479.robot.commands.auton.drive.StraightDrive;
import org.usfirst.frc.team6479.robot.commands.auton.elevator.GrabberGrab;
import org.usfirst.frc.team6479.robot.commands.auton.elevator.GrabberRelease;
import org.usfirst.frc.team6479.robot.commands.auton.elevator.MoveElevator;
import org.usfirst.frc.team6479.robot.commands.auton.wheelybar.WheelyBarDown;

import openrio.powerup.MatchData;

public class Scale extends BaseAutonomous {
	private boolean isLeft;

	//nothing should be done
	public Scale(StartPosition pos, boolean shouldUseSensors) {
		super(pos, shouldUseSensors);
	}

	//what happens when robot is positioned on the center
	@Override
	protected void center() {
		isLeft = super.scale == MatchData.OwnedSide.LEFT;

		Robot.eventLogger.writeToLog("Scale Center Autonomous");
	}

	//what happens when robot is positioned on the left
	@Override
	protected void left() {
		isLeft = super.scale == MatchData.OwnedSide.LEFT;

		Robot.eventLogger.writeToLog("Scale Left Autonomous");

		//Drive forward 20 ft.
		//addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 240));

		if (isLeft) {
			//Drive forward 25 ft and 2in.
			addParallel(new MoveElevator(MoveElevator.PreSetHeight.Switch));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 240));
			CommandGroup raiseTurn = new CommandGroup();
			raiseTurn.addParallel(new MoveElevator(MoveElevator.PreSetHeight.Scale));
			raiseTurn.addParallel(new WheelyBarDown());
			raiseTurn.addSequential(new GyroDrive(34, GyroDrive.Direction.dRight));
			addSequential(raiseTurn);

			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 34, true));
			addSequential(new GrabberRelease());
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, StraightDrive.Direction.backward, 34, true));
			addSequential(new GrabberGrab());
			addSequential(new MoveElevator(MoveElevator.PreSetHeight.Home));
		}
		else {
			addParallel(new MoveElevator(MoveElevator.PreSetHeight.Switch));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 216));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dRight));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 220));
			CommandGroup raiseTurn = new CommandGroup();
			raiseTurn.addParallel(new MoveElevator(MoveElevator.PreSetHeight.Scale));
			raiseTurn.addParallel(new WheelyBarDown());
			raiseTurn.addSequential(new GyroDrive(115, GyroDrive.Direction.dLeft));
			addSequential(raiseTurn);
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 62, true));
			addSequential(new GrabberRelease());
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, StraightDrive.Direction.backward, 34, true));
			addSequential(new GrabberGrab());
			addSequential(new MoveElevator(MoveElevator.PreSetHeight.Home));
		}
	}

	//what happens when robot is positioned on the right
	@Override
	protected void right() {
		isLeft = super.scale == MatchData.OwnedSide.LEFT;

		Robot.eventLogger.writeToLog("Scale Right Autonomous");

		if (isLeft) {
			addParallel(new MoveElevator(MoveElevator.PreSetHeight.Switch));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 216));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 220));
			CommandGroup raiseTurn = new CommandGroup();
			raiseTurn.addParallel(new MoveElevator(MoveElevator.PreSetHeight.Scale));
			raiseTurn.addParallel(new WheelyBarDown());
			raiseTurn.addSequential(new GyroDrive(115, GyroDrive.Direction.dRight));
			addSequential(raiseTurn);
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 62, true));
			addSequential(new GrabberRelease());
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, StraightDrive.Direction.backward, 34, true));
			addSequential(new GrabberGrab());
			addSequential(new MoveElevator(MoveElevator.PreSetHeight.Home));
		}
		else {
			//Drive forward 25 ft and 2in.
			addParallel(new MoveElevator(MoveElevator.PreSetHeight.Switch));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 240));
			CommandGroup raiseTurn = new CommandGroup();
			raiseTurn.addParallel(new MoveElevator(MoveElevator.PreSetHeight.Scale));
			raiseTurn.addParallel(new WheelyBarDown());
			raiseTurn.addSequential(new GyroDrive(34, GyroDrive.Direction.dLeft));
			addSequential(raiseTurn);

			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 34, true));
			addSequential(new GrabberRelease());
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, StraightDrive.Direction.backward, 34, true));
			addSequential(new GrabberGrab());
			addSequential(new MoveElevator(MoveElevator.PreSetHeight.Home));
		}
	}

	//what happens when robot is positioned in the center
	@Override
	protected void deadReckonCenter() {
		Robot.eventLogger.writeToLog("DEADRECKON Scale Center Autonomous");
	}

	//what happens when robot is positioned on the left
	@Override
	protected void deadReckonLeft() {
		Robot.eventLogger.writeToLog("DEADRECKON Scale Left Autonomous");
	}

	//what happens when robot is positioned on the right
	@Override
	protected void deadReckonRight() {
		Robot.eventLogger.writeToLog("DEADRECKON Scale Right Autonomous");
	}

}
