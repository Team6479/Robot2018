package org.usfirst.frc.team6479.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team6479.robot.Robot;
import org.usfirst.frc.team6479.robot.autonomous.manager.StartPosition;
import org.usfirst.frc.team6479.robot.commands.auton.drive.DeadReckonDrive;
import org.usfirst.frc.team6479.robot.commands.auton.drive.FlushTurn;
import org.usfirst.frc.team6479.robot.commands.auton.drive.GyroDrive;
import org.usfirst.frc.team6479.robot.commands.auton.drive.StraightDrive;
import org.usfirst.frc.team6479.robot.commands.auton.elevator.GrabberRelease;
import org.usfirst.frc.team6479.robot.commands.auton.elevator.MoveElevator;

import openrio.powerup.MatchData;
import org.usfirst.frc.team6479.robot.commands.auton.wheelybar.WheelyBarDown;

public class Switch extends BaseAutonomous {

	private boolean isLeft;

	//nothing should be done
	public Switch(StartPosition pos, boolean shouldUseSensors) {
		super(pos, shouldUseSensors);
	}

	//what happens when robot is positioned on the center
	@Override
	protected void center() {
		Robot.eventLogger.writeToLog("Switch Center Autonomous");

		isLeft = super.nearSwitch == MatchData.OwnedSide.LEFT;

		//Go forward 3 ft.
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 36));

		/*
		- Turn toward owned switch
		- Drive 4.5 ft. forward
		- Turn to face switch
		 */
		if (isLeft) {
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 69));
			addParallel(new MoveElevator(MoveElevator.PreSetHeight.Switch));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dRight));
		}
		else {
			addSequential(new GyroDrive(90, GyroDrive.Direction.dRight));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 36));
			addParallel(new MoveElevator(MoveElevator.PreSetHeight.Switch));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
		}

		//Drive forward 3 ft.
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 36));

		deliverCube();

		addSequential(new MoveElevator(MoveElevator.PreSetHeight.Home));
		reverse();
	}

	//what happens when robot is positioned on the left
	@Override
	protected void left() {
		Robot.eventLogger.writeToLog("Switch Left Autonomous");

		isLeft = super.nearSwitch == MatchData.OwnedSide.LEFT;

		if(isLeft) {
			//Go forward 3 ft.
			addParallel(new MoveElevator(MoveElevator.PreSetHeight.Switch));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 153));

			addSequential(new GyroDrive(90, GyroDrive.Direction.dRight));

			deliverCube();
		}
		else {
			//go around back
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 216));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dRight));
			CommandGroup raise = new CommandGroup();
			raise.addParallel(new MoveElevator(MoveElevator.PreSetHeight.PrepSwitch));
			raise.addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 220));
			addSequential(raise);
			addParallel(new MoveElevator(MoveElevator.PreSetHeight.Switch));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dRight));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 72));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dRight));
			addSequential(new FlushTurn());

			deliverCube();
		}
	}

	//what happens when robot is positioned on the right
	@Override
	protected void right() {
		Robot.eventLogger.writeToLog("Switch Right Autonomous");

		isLeft = super.nearSwitch == MatchData.OwnedSide.LEFT;

		if(isLeft) {
			//go around back
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 216));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
			CommandGroup raise = new CommandGroup();
			raise.addParallel(new MoveElevator(MoveElevator.PreSetHeight.PrepSwitch));
			raise.addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 228));
			addSequential(raise);
			addParallel(new MoveElevator(MoveElevator.PreSetHeight.Switch));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 68));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
			addSequential(new FlushTurn());

			deliverCube();
		}
		else {
			//Go forward 3 ft.
			addParallel(new MoveElevator(MoveElevator.PreSetHeight.Switch));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 147));

			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));

			deliverCube();
		}
	}

	//what happens when robot is positioned in the center
	@Override
	protected void deadReckonCenter() {
		Robot.eventLogger.writeToLog("DEADRECKON Switch Center Autonomous");
	}

	//what happens when robot is positioned on the left
	@Override
	protected void deadReckonLeft() {
		Robot.eventLogger.writeToLog("DEADRECKON Switch Left Autonomous");
	}

	//what happens when robot is positioned on the right
	@Override
	protected void deadReckonRight() {
		Robot.eventLogger.writeToLog("DEADRECKON Switch Right Autonomous");
	}

	private void deliverCube() {
		addParallel(new WheelyBarDown());
		addSequential(new StraightDrive(StraightDrive.Mode.sonarDrive, 3));
		addParallel(new GrabberRelease());
		addSequential(new DeadReckonDrive(0.6, 0.65, DeadReckonDrive.Direction.dForward));
	}

	private void reverse() {
		//Back up 3ft.
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, StraightDrive.Direction.backward, 36));

		if (isLeft) {
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
		}
		else {
			addSequential(new GyroDrive(90 , GyroDrive.Direction.dRight));
		}

		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 55));

		if (isLeft) {
			addSequential(new GyroDrive(90, GyroDrive.Direction.dRight));
		}
		else {
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
		}

		//Drive 3 ft across baseline
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 45));
	}
}
