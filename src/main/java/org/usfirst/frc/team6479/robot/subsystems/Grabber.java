package org.usfirst.frc.team6479.robot.subsystems;

import org.usfirst.frc.team6479.robot.config.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

//subsystem for the cube grabber
public class Grabber extends Subsystem implements SafeSubsystem {

	private DoubleSolenoid sol;
	
	public Grabber() {
		sol = new DoubleSolenoid(RobotMap.grabberOnPort, RobotMap.grabberOffPort); 
		//defsault state is off
		sol.set(Value.kOff);
	}
	
	@Override
	protected void initDefaultCommand() {
		//TODO: set the default command whihc is the button control of the grabber
	}
	public void grab() {
		sol.set(Value.kForward);
	}
	public void release() {
		sol.set(Value.kReverse);
	}
	public boolean isGrabbing() {
		//wether or not it is currently grabbing
		boolean isGrabbing = sol.get() == Value.kForward;
		return isGrabbing;
	}
	public DoubleSolenoid getSolenoid() {
		return sol;
	}
	
	@Override
	public void stop() {
		sol.set(Value.kOff);
	}

}
