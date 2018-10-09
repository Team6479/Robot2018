/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6479.robot.commands.auton.intake;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class GrabberStop extends InstantCommand {
  public GrabberStop() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
	requires(Robot.grabber);
}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
	  Robot.grabber.stop();
  }
}
