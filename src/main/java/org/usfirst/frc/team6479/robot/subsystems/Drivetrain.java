package org.usfirst.frc.team6479.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team6479.robot.commands.teleop.RacingDrive;
import org.usfirst.frc.team6479.robot.config.RobotMap;
import org.usfirst.frc.team6479.robot.sensors.DoubleUltrasonic;
import org.usfirst.frc.team6479.robot.sensors.DrivetrainEncoder;
import org.usfirst.frc.team6479.robot.sensors.RangeFinder;

//the drive train of the robot
public class Drivetrain extends Subsystem implements SafeSubsystem {

	private SpeedController leftBack;
	private SpeedController rightBack;
	private SpeedController leftFront;
	private SpeedController rightFront;

	private SpeedController leftSide;
	private SpeedController rightSide;

	private DifferentialDrive drive;

	private DrivetrainEncoder encoder;

    private ADXRS450_Gyro gyro;

    private RangeFinder sonar;
    
    private DoubleUltrasonic ultrasonic;

	public Drivetrain() {
		leftBack = new Spark(RobotMap.leftBackPort);
		rightBack = new Spark(RobotMap.rightBackPort);
		leftFront = new Spark(RobotMap.leftFrontPort);
		rightFront = new Spark(RobotMap.rightFrontPort);

		leftSide = new SpeedControllerGroup(leftBack, leftFront);
		rightSide = new SpeedControllerGroup(rightBack, rightFront);

		drive = new DifferentialDrive(leftSide, rightSide);


		//init encoder
		encoder = new DrivetrainEncoder(RobotMap.leftEncoderAPort, RobotMap.leftEncoderBPort,false,
			RobotMap.rightEncoderAPort, RobotMap.rightEncoderBPort, true, Encoder.EncodingType.k4X);
		// set the time until when the robot is considered stopped, set in seconds
		encoder.setMaxPeriod(0.05);
        // set distance per pulse to be 1 inch
        double distancePerPulse = (6 * Math.PI) / 360;
        encoder.setDistancePerPulse(distancePerPulse);

        //TODO: adjust as needed
        encoder.setSamplesToAverage(10);

        gyro = new ADXRS450_Gyro();

        sonar = new RangeFinder(RobotMap.sonarPort);
        
        ultrasonic = new DoubleUltrasonic(RobotMap.leftInputPing, RobotMap.leftOutputEcho, RobotMap.rightInputPing, RobotMap.rightOutputEcho);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RacingDrive());
	}
	public void curveDrive(double throttle, double turn) {
		//drive.curvatureDrive(throttle, turn, true);

		drive.arcadeDrive(throttle, turn, false);
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

    public RangeFinder getSonar() {
        return sonar;
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
