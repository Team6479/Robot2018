package org.usfirst.frc.team6479.robot.subsystems;

import org.usfirst.frc.team6479.robot.commands.teleop.RacingDrive;
import org.usfirst.frc.team6479.robot.config.RobotMap;
import org.usfirst.frc.team6479.robot.sensors.DoubleUltrasonic;
import org.usfirst.frc.team6479.robot.sensors.DrivetrainEncoder;
import org.usfirst.frc.team6479.robot.sensors.RangeFinder;
import org.usfirst.frc.team6479.robot.util.DifferentialDriveLimiter;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//the drive train of the robot
public class Drivetrain extends Subsystem implements SafeSubsystem {

	private SpeedController leftBack;
	private SpeedController rightBack;
	private SpeedController leftFront;
	private SpeedController rightFront;

	private SpeedController leftSide;
	private SpeedController rightSide;

	private DifferentialDriveLimiter drive;

	private DrivetrainEncoder encoder;

    private ADXRS450_Gyro gyro;

    //private DigitalInput button;

    private DoubleUltrasonic ultrasonic;

    private boolean limiter;
    private boolean hyper;

	public Drivetrain() {
		limiter = false;
		hyper = false;

		leftBack = new Spark(RobotMap.leftBackPort);
		rightBack = new Spark(RobotMap.rightBackPort);
		leftFront = new Spark(RobotMap.leftFrontPort);
		rightFront = new Spark(RobotMap.rightFrontPort);

		leftSide = new SpeedControllerGroup(leftBack, leftFront);
		rightSide = new SpeedControllerGroup(rightBack, rightFront);

		drive = new DifferentialDriveLimiter(leftSide, rightSide);


		//init encoder
		encoder = new DrivetrainEncoder(RobotMap.leftEncoderAPort, RobotMap.leftEncoderBPort,true,
			RobotMap.rightEncoderAPort, RobotMap.rightEncoderBPort, false, Encoder.EncodingType.k4X);
		// set the time until when the robot is considered stopped, set in seconds
		encoder.setMaxPeriod(0.05);
        // set distance per pulse to be 1 inch
        double distancePerPulse = (6 * Math.PI) / 360;
        encoder.setDistancePerPulse(distancePerPulse);


        gyro = new ADXRS450_Gyro();


        ultrasonic = new DoubleUltrasonic(RobotMap.leftInputPing, RobotMap.leftOutputEcho, RobotMap.rightInputPing, RobotMap.rightOutputEcho);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RacingDrive());
	}
	public void racingDrive(double throttle, double turn) {
		drive.arcadeDrive(throttle, turn, false, limiter);
	}
	public void tankDrive(double leftSpeed, double rightSpeed) {
	    drive.tankDrive(leftSpeed, rightSpeed, false, limiter);
    }

    public void setLimiter(boolean limit) {
		limiter = limit;
    }

    public void setHyper(boolean hyper) {
		this.hyper = hyper;
		if(hyper) {
			drive.setMaxSpeed(1);
		}
		else {
			drive.setMaxSpeed(0.65);
		}
    }
    public boolean isHyping() {
		return hyper;
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

    public DifferentialDriveLimiter getDrive() {
        return drive;
    }

    public DrivetrainEncoder getEncoder() {
        return encoder;
    }

    public DoubleUltrasonic getUltrasonic() {
    		return ultrasonic;
    }

    @Override
	public void stop() {
		leftSide.set(0);
		rightSide.set(0);
	}
}
