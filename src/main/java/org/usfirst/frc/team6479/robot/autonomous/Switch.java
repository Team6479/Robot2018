package org.usfirst.frc.team6479.robot.autonomous;

import org.usfirst.frc.team6479.robot.autonomous.manager.StartPosition;
import org.usfirst.frc.team6479.robot.commands.auton.camera.LightOff;
import org.usfirst.frc.team6479.robot.commands.auton.camera.LightOn;
import org.usfirst.frc.team6479.robot.commands.auton.camera.ToggleCamera;
import org.usfirst.frc.team6479.robot.commands.auton.camera.ToggleLight;
import org.usfirst.frc.team6479.robot.commands.auton.drive.CameraDrive;
import org.usfirst.frc.team6479.robot.commands.auton.drive.CameraTurn;
import org.usfirst.frc.team6479.robot.commands.auton.drive.DeadReckonDrive;
import org.usfirst.frc.team6479.robot.commands.auton.drive.FlushTurn;
import org.usfirst.frc.team6479.robot.commands.auton.drive.GyroDrive;
import org.usfirst.frc.team6479.robot.commands.auton.drive.StraightDrive;
import org.usfirst.frc.team6479.robot.commands.auton.elevator.GrabberRelease;
import org.usfirst.frc.team6479.robot.commands.auton.elevator.MoveElevator;

import communication.JetsonPacket;
import openrio.powerup.MatchData;

public class Switch extends BaseAutonomous {

	private boolean isLeft;

	//nothing should be done
	public Switch(StartPosition pos, boolean shouldUseSensors) {
		super(pos, shouldUseSensors);
		isLeft = super.scale == MatchData.OwnedSide.LEFT;
	}

	//what happens when robot is positioned on the center
	@Override
	protected void center() {
		System.out.println("Switch Center Autonomous");

		//Go forward 3 ft.
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 36));

		/*
		- Turn toward owned switch
		- Drive 4.5 ft. forward
		- Turn to face switch
		 */
		if (isLeft) {
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
			addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 36));
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
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 24));

		//Turn towards vision target and drive toward it
		/*addSequential(new LightOn());
		addSequential(new ToggleCamera(JetsonPacket.ModePacket.Mode.GOAL));
		addSequential(new CameraTurn());
		//addSequential(new CameraDrive());

		//Disable Vision
		addSequential(new LightOff());
		addSequential(new ToggleCamera(JetsonPacket.ModePacket.Mode.NONE));
		*/
		//TODO: Add cube delivery to switch

		//addSequential(new StraightDrive(StraightDrive.Mode.sonarDrive, 1));
		addSequential(new DeadReckonDrive(1,0.6, DeadReckonDrive.Direction.dForward));
		addParallel(new GrabberRelease());
		addSequential(new DeadReckonDrive(1,0.6, DeadReckonDrive.Direction.dForward));

		reverse();
	}

	//what happens when robot is positioned on the left
	@Override
	protected void left() {
		System.out.println("Switch Left Autonomous");

		//Go forward 3 ft.
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 72));

		//Turn towards vision target and drive toward it
		addSequential(new MoveElevator(MoveElevator.PreSetHeight.Switch));
		addSequential(new LightOn());
		addSequential(new ToggleCamera(JetsonPacket.ModePacket.Mode.GOAL));
		addSequential(new CameraTurn());
		addSequential(new CameraDrive());
		addSequential(new FlushTurn());

		reverse();
	}

	//what happens when robot is positioned on the right
	@Override
	protected void right() {
		System.out.println("Switch Right Autonomous");

		//Go forward 5 ft.
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 60));

		//Turn towards vision target and drive toward it
		addSequential(new MoveElevator(MoveElevator.PreSetHeight.Switch));
		addSequential(new LightOn());
		addSequential(new ToggleCamera(JetsonPacket.ModePacket.Mode.GOAL));
		addSequential(new CameraTurn());
		addSequential(new CameraDrive());
		addSequential(new FlushTurn());

		reverse();
	}

	//what happens when robot is positioned in the center
	@Override
	protected void deadReckonCenter() {
		System.out.println("DEADRECKON Switch Center Autonomous");
	}

	//what happens when robot is positioned on the left
	@Override
	protected void deadReckonLeft() {
		System.out.println("DEADRECKON Switch Left Autonomous");
	}

	//what happens when robot is positioned on the right
	@Override
	protected void deadReckonRight() {
		System.out.println("DEADRECKON Switch Right Autonomous");
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

		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 50));

		if (isLeft) {
			addSequential(new GyroDrive(90, GyroDrive.Direction.dRight));
		}
		else {
			addSequential(new GyroDrive(90, GyroDrive.Direction.dLeft));
		}

		//Drive 3 ft across baseline
		addSequential(new StraightDrive(StraightDrive.Mode.encoderDrive, 36));
	}
}
