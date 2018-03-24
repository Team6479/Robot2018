package org.usfirst.frc.team6479.robot.autonomous;

import org.usfirst.frc.team6479.robot.Robot;
import org.usfirst.frc.team6479.robot.autonomous.manager.StartPosition;
import org.usfirst.frc.team6479.robot.commands.auton.camera.LightOff;
import org.usfirst.frc.team6479.robot.commands.auton.camera.LightOn;
import org.usfirst.frc.team6479.robot.commands.auton.camera.ToggleCamera;
import org.usfirst.frc.team6479.robot.commands.auton.drive.CameraTurn;
import org.usfirst.frc.team6479.robot.commands.auton.drive.DeadReckonDrive;
import org.usfirst.frc.team6479.robot.commands.auton.drive.FlushTurn;
import org.usfirst.frc.team6479.robot.commands.auton.drive.GyroDrive;
import org.usfirst.frc.team6479.robot.commands.auton.drive.StraightDrive;
import org.usfirst.frc.team6479.robot.commands.auton.elevator.GrabberRelease;
import org.usfirst.frc.team6479.robot.commands.auton.elevator.MoveElevator;

import communication.JetsonPacket;
import openrio.powerup.MatchData;

public class DoubleSwitch extends BaseAutonomous {

	private boolean isLeft;

	//nothing should be done
	public DoubleSwitch(StartPosition pos, boolean shouldUseSensors) {
		super(pos, shouldUseSensors);
	}

	//what happens when robot is positioned on the center
	@Override
	protected void center() {
		Robot.eventLogger.writeToLog("Double Switch Center Autonomous");

		isLeft = super.nearSwitch == MatchData.OwnedSide.LEFT;

		//Go forward 3 ft.
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 18));

		/*
		- Turn toward owned switch
		- Drive 4.5 ft. forward
		- Turn to face switch
		 */
		if (isLeft) {
			addSequential(new GyroDrive(45, GyroDrive.Direction.dLeft));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 97));
			addParallel(new MoveElevator(MoveElevator.PreSetHeight.Switch));
			addSequential(new GyroDrive(45, GyroDrive.Direction.dRight));
		}
		//Right
		else {
			addSequential(new GyroDrive(45, GyroDrive.Direction.dRight));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 66));
			addParallel(new MoveElevator(MoveElevator.PreSetHeight.Switch));
			addSequential(new GyroDrive(45, GyroDrive.Direction.dLeft));
		}

		//Drive forward 3 ft.
		//addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 36));

		//Turn towards vision target and drive toward it
		/*addSequential(new LightOn());
		addSequential(new ToggleCamera(JetsonPacket.ModePacket.Mode.GOAL));
		addSequential(new CameraTurn());
		//addSequential(new CameraDrive());*/

		//Disable Vision
		//addSequential(new LightOff());
		//addSequential(new ToggleCamera(JetsonPacket.ModePacket.Mode.NONE));

		addSequential(new FlushTurn());

		deliverCube();

		addSequential(new MoveElevator(MoveElevator.PreSetHeight.Home));
		reverse();
	}

	//what happens when robot is positioned on the left
	@Override
	protected void left() {
		Robot.eventLogger.writeToLog("Double Switch Left Autonomous");

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
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 228));
			addParallel(new MoveElevator(MoveElevator.PreSetHeight.Switch));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dRight));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 84));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dRight));
			addSequential(new FlushTurn());

			deliverCube();
		}
	}

	//what happens when robot is positioned on the right
	@Override
	protected void right() {
		Robot.eventLogger.writeToLog("Double Switch Right Autonomous");

		isLeft = super.nearSwitch == MatchData.OwnedSide.LEFT;

		if(isLeft) {
			//go around back
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 216));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 228));
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

			//Second Cube
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, -12));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dRight));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 70));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 40));
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
		//	addSequential(new StraightDrive(StraightDrive.Mode.sonarDrive, ));
		}
	}

	//what happens when robot is positioned in the center
	@Override
	protected void deadReckonCenter() {
		Robot.eventLogger.writeToLog("DEADRECKON Double Switch Center Autonomous");
	}

	//what happens when robot is positioned on the left
	@Override
	protected void deadReckonLeft() {
		Robot.eventLogger.writeToLog("DEADRECKON Double Switch Left Autonomous");
	}

	//what happens when robot is positioned on the right
	@Override
	protected void deadReckonRight() {
		Robot.eventLogger.writeToLog("DEADRECKON Double Switch Right Autonomous");
	}

	private void deliverCube() {
		addSequential(new StraightDrive(StraightDrive.Mode.sonarDrive, 1));
		addParallel(new GrabberRelease());
		addSequential(new DeadReckonDrive(0.6, 0.6, DeadReckonDrive.Direction.dForward));
	}

	private void reverse() {
		//Back up 3ft.
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, -36));

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
