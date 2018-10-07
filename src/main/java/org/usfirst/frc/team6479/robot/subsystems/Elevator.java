package org.usfirst.frc.team6479.robot.subsystems;

import org.usfirst.frc.team6479.robot.commands.teleop.ElevatorControl;
import org.usfirst.frc.team6479.robot.config.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

//the elevator, which uses a winch, this is controlled by two cims and a piston
public class Elevator extends Subsystem implements SafeSubsystem {

	private SpeedController elevatorMotorFront;
	private SpeedController elevatorMotorBack;

	private SpeedController elevator;
	private Encoder encoder;

	public Elevator() {
		elevatorMotorFront = new Spark(RobotMap.elevatorFrontPort);
		elevatorMotorBack = new Spark(RobotMap.elevatorBackPort);

		elevator = new SpeedControllerGroup(elevatorMotorFront, elevatorMotorBack);
		encoder = new Encoder(RobotMap.elevatorEncoderAPort, RobotMap.elevatorEncoderBPort);
		// unlock();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorControl());
	}
	public void move(double speed) {
		elevator.set(speed);
		// if(isLocked() && isOnWinch()) {
		// 	elevator.set(0.5 * speed);
		// }
		// else if(isLocked() && isOnClimber()) {
		// 	elevator.set(0);
		// }
		// else if(isUnLocked() && isOnWinch()) {
		// 	elevator.set(speed);
		// }
		// else if(isUnLocked() && isOnClimber()) {
		// 	elevator.set(speed);
		// }
		// else {
		// 	elevator.set(0);
		// }
	}

	public SpeedController getElevator() {
		return elevator;
	}

	public Encoder getEncoder() {
		return encoder;
	}

	@Override
	public void stop() {
		elevator.set(0);
	}

}
