package org.usfirst.frc.team6479.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class SafeSubsystem extends Subsystem {

	
	//safe subsystem forces all subclasses to implement a stop function
	public abstract void stop();
}
