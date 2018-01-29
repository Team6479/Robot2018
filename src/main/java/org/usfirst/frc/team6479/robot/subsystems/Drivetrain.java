package org.usfirst.frc.team6479.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
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

    private ADXRS450_Gyro gyro;

	public Drivetrain() {
		leftBack = new Spark(RobotMap.leftBackPort);
		rightBack = new Spark(RobotMap.rightBackPort);
		leftFront = new Spark(RobotMap.leftFrontPort);
		rightFront = new Spark(RobotMap.rightFrontPort);

		leftSide = new SpeedControllerGroup(leftBack, leftFront);
		rightSide = new SpeedControllerGroup(rightBack, rightFront);

		drive = new DifferentialDrive(leftSide, rightSide);

        gyro = new ADXRS450_Gyro();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RacingDrive());
	}
    public void goForward() {
        leftSide.setInverted(false);
        rightSide.setInverted(false);
    }
    public void goBackward() {
        leftSide.setInverted(true);
        rightSide.setInverted(true);
    }
    public void turnLeft(){
        leftSide.setInverted(true);
        rightSide.setInverted(false);
    }
    public void turnRight(){
        leftSide.setInverted(false);
        rightSide.setInverted(true);
    }
	public void curveDrive(double throttle, double turn) {
		drive.curvatureDrive(throttle, turn, true);
	}
	public void tankDrive(double leftSpeed, double rightSpeed) {
	    drive.tankDrive(leftSpeed, rightSpeed);
    }
	public SpeedController getLeftSideMotors() {
		return leftSide;
	}
	public SpeedController getRightSideMotors() {
		return rightSide;
	}
    public ADXRS450_Gyro getGyro() {
        return gyro;
    }

    public DifferentialDrive getDrive() {
        return drive;
    }
	@Override
	public void stop() {
		leftSide.set(0);
		rightSide.set(0);
	}
}
