package org.usfirst.frc.team6479.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6479.robot.commands.teleop.ElevatorControl;
import org.usfirst.frc.team6479.robot.config.RobotMap;

//the elevator, which uses a winch, this is controlled by two cims and a piston
public class Elevator extends Subsystem implements SafeSubsystem {

	private SpeedController pulley1;
	private SpeedController pulley2;

	private SpeedController winch;
	private Solenoid sol;
	private Encoder encoder;

	public Elevator() {
		pulley1 = new Spark(RobotMap.pulley1Port);
		pulley2 = new Spark(RobotMap.pulley2Port);

		winch = new SpeedControllerGroup(pulley1, pulley2);
		sol = new Solenoid(RobotMap.pulleySolPort);
		encoder = new Encoder(RobotMap.pulleyEncoderAPort, RobotMap.pulleyEncoderBPort);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorControl());
	}
	public void move(double speed) {
		//if the sol is breaking, don't move the cims
		if(!sol.get()) {
			winch.set(speed);
		}
		else {
			winch.set(0);
		}
	}
	public void breakState(boolean shouldBreak) {
		sol.set(shouldBreak);
		//if the sol is breaking, don't move the cims
		if(shouldBreak) {
			winch.set(0);
		}
	}
	public void flipBreak() {
		boolean currentState = sol.get();
		breakState(!currentState);
	}
	public SpeedController getWinch() {
		return winch;
	}
	public Solenoid getStopperSolenoid() {
		return sol;
	}

	public Encoder getEncoder() {
		return encoder;
	}

	@Override
	public void stop() {
		winch.set(0);
		//apply break so elevator doesn't crash down
		sol.set(true);
	}

}
