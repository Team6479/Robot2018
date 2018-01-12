package org.usfirst.frc.team6479.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team6479.robot.commands.RacingDrive;
import org.usfirst.frc.team6479.robot.config.RobotMap;

public class Drivetrain extends SafeSubsystem {
	
	private Spark leftMotorFront = new Spark(RobotMap.leftFront);
	private Spark leftMotorBack = new Spark(RobotMap.leftBack);
	private Spark rightMotorFront = new Spark(RobotMap.rightFront);
	private Spark rightMotorBack = new Spark(RobotMap.rightBack);
	
	private SpeedControllerGroup leftSide = new SpeedControllerGroup(leftMotorFront, leftMotorBack);
	private SpeedControllerGroup rightSide = new SpeedControllerGroup(rightMotorFront, rightMotorBack);
	
	private DifferentialDrive drive = new DifferentialDrive(leftSide, rightSide);

	public void initDefaultCommand() {
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
