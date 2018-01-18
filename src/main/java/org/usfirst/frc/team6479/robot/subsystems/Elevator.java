package org.usfirst.frc.team6479.robot.subsystems;

import org.usfirst.frc.team6479.robot.config.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

//the elevator, which uses a winch, this is controlled by two cims and a piston
public class Elevator extends Subsystem implements SafeSubsystem {

	private SpeedController pulley1;
	private SpeedController pulley2;
	
	private SpeedController winch;
	private Solenoid sol;
	
	public Elevator() {
		pulley1 = new Spark(RobotMap.pulley1Port);
		pulley2 = new Spark(RobotMap.pulley2Port);
		
		winch = new SpeedControllerGroup(pulley1, pulley2);
		sol = new Solenoid(RobotMap.pulleySolPort);
	}
	
	@Override
	protected void initDefaultCommand() {
		//TODO: set the default command whihc is the joystick control of the elevator
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
	public Solenoid getStopper() {
		return sol;
	}
	
	@Override
	public void stop() {
		winch.set(0);
		//apply break so elevator doesn't crash down
		sol.set(true);
	}

}