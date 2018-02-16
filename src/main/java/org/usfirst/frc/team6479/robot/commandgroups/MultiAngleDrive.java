package org.usfirst.frc.team6479.robot.commandgroups;

import org.usfirst.frc.team6479.robot.commands.auton.drive.GyroDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class MultiAngleDrive extends CommandGroup {

    public MultiAngleDrive() {
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

        addSequential(new GyroDrive(90.0, GyroDrive.Direction.dRight));
        addSequential(new WaitCommand(2));
        addSequential(new GyroDrive(90.0, GyroDrive.Direction.dLeft));
    }
}
