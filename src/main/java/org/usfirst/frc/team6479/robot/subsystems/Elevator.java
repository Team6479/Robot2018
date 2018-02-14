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
	private Solenoid winchSol;
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
		//if the sol is breaking, don't move the cims
		if(!winchSol.get()) {
			winch.set(speed);
		}
		else {
			winch.set(0);
		}
	}
	public void breakState(boolean shouldBreak) {
		winchSol.set(shouldBreak);
		//if the sol is breaking, don't move the cims
		if(shouldBreak) {
			winch.set(0);
		}
	}
	public void flipBreak() {
		boolean currentState = winchSol.get();
		breakState(!currentState);
	}
	public void gearboxState(boolean shouldBreak) {
		//get the current winch value
		double winchVal = winch.get();
		//stop the winch
		winch.set(0);
		//now shift
		gearboxSol.set(shouldBreak);
		//then restart winch
		winch.set(winchVal);
	}
	public void flipGearbox() {
		boolean currentState = gearboxSol.get();
		gearboxState(!currentState);
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
