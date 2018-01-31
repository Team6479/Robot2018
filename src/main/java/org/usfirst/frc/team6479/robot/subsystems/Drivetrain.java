package org.usfirst.frc.team6479.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6479.robot.commands.teleop.RacingDrive;
import org.usfirst.frc.team6479.robot.config.RobotMap;
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
	
	private Encoder leftEncoder;
	private Encoder rightEncoder;

    private ADXRS450_Gyro gyro;
    
    private RangeFinder sonar;

	public Drivetrain() {
		leftBack = new Spark(RobotMap.leftBackPort);
		rightBack = new Spark(RobotMap.rightBackPort);
		leftFront = new Spark(RobotMap.leftFrontPort);
		rightFront = new Spark(RobotMap.rightFrontPort);

		leftSide = new SpeedControllerGroup(leftBack, leftFront);
		rightSide = new SpeedControllerGroup(rightBack, rightFront);

		drive = new DifferentialDrive(leftSide, rightSide);

		// init the encoders
        leftEncoder = new Encoder(RobotMap.leftEncoderAPort, RobotMap.leftEncoderBPort, false, Encoder.EncodingType.k4X);
        rightEncoder = new Encoder(RobotMap.rightEncoderAPort, RobotMap.rightEncoderBPort, true, Encoder.EncodingType.k4X);
        // set the time until when the robot is considered stopped, set in seconds
        leftEncoder.setMaxPeriod(.05);
        rightEncoder.setMaxPeriod(.05);
        // set distance per pulse to be 1 inch
        double distancePerPulse = (6 * Math.PI) / 360;
        leftEncoder.setDistancePerPulse(distancePerPulse);
        rightEncoder.setDistancePerPulse(distancePerPulse);

		SmartDashboard.putData("LEFT", leftEncoder);
		SmartDashboard.putData("RIGHT", rightEncoder);
        
        gyro = new ADXRS450_Gyro();
        
        sonar = new RangeFinder(0);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RacingDrive());
	}
	public void curveDrive(double throttle, double turn) {
		drive.curvatureDrive(throttle, turn, true);
		
		//drive.arcadeDrive(throttle, turn, true);
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
	@Override
	public void stop() {
		leftSide.set(0);
		rightSide.set(0);
	}
}
