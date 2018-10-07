/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6479.robot.commands.teleop;

import org.usfirst.frc.team6479.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleGrabberSuck extends Command {
  public ToggleGrabberSuck() {
    // Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(Robot.grabber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
	  boolean isSucking = Robot.grabber.isSucking();
	// //   System.out.println(isSucking);
	//   if(isSucking) {
	// 	  Robot.grabber.suck(0);
	//   }
	//   else {
	// 	  Robot.grabber.suck(0.3);
	//   }
	Robot.grabber.suck(-1);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
