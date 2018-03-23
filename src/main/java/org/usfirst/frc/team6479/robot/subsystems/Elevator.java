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

	private SpeedController winchMotorTop;
	private SpeedController winchMotorBottom;

	private SpeedController winch;
	//when solenoid is on, the winch is stopped
	private Solenoid winchSol;
	//when the solenoid is on, power will go to the winch
	private Solenoid gearboxSol;
	private Encoder encoder;

	public Elevator() {
		winchMotorTop = new Spark(RobotMap.winchTopPort);
		winchMotorBottom = new Spark(RobotMap.winchBottomPort);

		winch = new SpeedControllerGroup(winchMotorTop, winchMotorBottom);
		winchSol = new Solenoid(RobotMap.winchSolPort);
		gearboxSol = new Solenoid(RobotMap.gearboxSolPort);
		encoder = new Encoder(RobotMap.pulleyEncoderAPort, RobotMap.pulleyEncoderBPort);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorControl());
	}
	public void move(double speed) {
		//if it is locked, do not move
		if(isLocked()) {
			winch.set(0);
			return;
		}
		//if it is on the winch, move at the joystick command
		if(isOnWinch()) {
			winch.set(speed);
			return;
		}
		//if it is on the winch, move at the abs value of the joystick command
		if(isOnClimber()) {
			winch.set(Math.abs(speed));
			return;
		}
		
		winch.set(0);
	}
	public void switchToWinch() {
		gearboxSol.set(true);
	}
	public void switchToClimber() {
		gearboxSol.set(false);
	}
	public boolean isOnWinch() {
		return gearboxSol.get();
	}
	public boolean isOnClimber() {
		return !isOnWinch();
	}
	public void lock() {
		winchSol.set(true);
	}
	public void unlock() {
		winchSol.set(false);
	}
	public boolean isLocked() {
		return winchSol.get();
	}
	public boolean isUnLocked() {
		return !isLocked();
	}
	public SpeedController getWinch() {
		return winch;
	}
	public Solenoid getStopperSolenoid() {
		return winchSol;
	}
	public Solenoid getGearboxSolenoid() {
		return gearboxSol;
	}

	public Encoder getEncoder() {
		return encoder;
	}

	@Override
	public void stop() {
		winch.set(0);
		//apply break so elevator doesn't crash down
		winchSol.set(true);
	}

}
