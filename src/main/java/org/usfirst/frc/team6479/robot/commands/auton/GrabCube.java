package org.usfirst.frc.team6479.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team6479.robot.commands.auton.camera.ToggleCamera;
import org.usfirst.frc.team6479.robot.commands.auton.drive.CameraTurn;
import org.usfirst.frc.team6479.robot.commands.auton.drive.StraightDrive;
import org.usfirst.frc.team6479.robot.commands.auton.elevator.GrabberGrab;
import org.usfirst.frc.team6479.robot.commands.auton.elevator.GrabberRelease;
import org.usfirst.frc.team6479.robot.commands.auton.elevator.MoveElevator;
import communication.JetsonPacket.ModePacket;


public class GrabCube extends CommandGroup {

    public GrabCube() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the arm.
		addSequential(new ToggleCamera(ModePacket.Mode.CUBE));
		addSequential(new GrabberRelease());
		addSequential(new CameraTurn());
		CommandGroup driveAndMoveElevator = new CommandGroup();
		driveAndMoveElevator.addSequential(new MoveElevator(MoveElevator.PreSetHeight.Home));
		driveAndMoveElevator.addParallel(new StraightDrive(StraightDrive.Mode.sonarDrive, 4.5));
		//addSequential(driveAndMoveElevator);
		//addSequential(new GrabberGrab());
		//addSequential(new ToggleCamera(ModePacket.Mode.NONE));
    }
}
