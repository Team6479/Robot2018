package org.usfirst.frc.team6479.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team6479.robot.commands.RacingDrive;
import org.usfirst.frc.team6479.robot.config.RobotMap;

//the drive train of the robot
public class Drivetrain extends Subsystem implements SafeSubsystem {
	
	private SpeedController leftBack;
	private SpeedController rightBack;
	private SpeedController leftFront;
	private SpeedController rightFront;
	
	private SpeedController leftSide;
	private SpeedController rightSide;
	
	private DifferentialDrive drive;

	public Drivetrain() {
		leftBack = new Spark(RobotMap.leftBackPort);
		rightBack = new Spark(RobotMap.rightBackPort);
		leftFront = new Spark(RobotMap.leftFrontPort);
		rightFront = new Spark(RobotMap.rightFrontPort);
		
		leftSide = new SpeedControllerGroup(leftBack, leftFront);
		rightSide = new SpeedControllerGroup(rightBack, rightFront);
		
		drive = new DifferentialDrive(leftSide, rightSide);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RacingDrive());
	}
	public void drive(double throttle, double turn) {
		drive.curvatureDrive(throttle, turn, true);
	}
	public SpeedController getLeftSideMotors() {
		return leftSide;
	}
	public SpeedController getRightSideMotors() {
		return rightSide;
	}
	@Override
	public void stop() {
		leftSide.set(0);
		rightSide.set(0);
	}
}
