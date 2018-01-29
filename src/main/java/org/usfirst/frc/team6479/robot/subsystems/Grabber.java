package org.usfirst.frc.team6479.robot.subsystems;

import org.usfirst.frc.team6479.robot.config.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

//subsystem for the cube grabber
public class Grabber extends Subsystem implements SafeSubsystem {

	private DoubleSolenoid dubSol;

	public Grabber() {
		dubSol = new DoubleSolenoid(RobotMap.grabberOnPort, RobotMap.grabberOffPort);
		//default state is off
		dubSol.set(Value.kOff);
	}
    @Override
    protected void initDefaultCommand() {

    }
	public void grab() {
		dubSol.set(Value.kForward);
	}
	public void release() {
		dubSol.set(Value.kReverse);
	}
	public boolean isGrabbing() {
		//whether or not it is currently grabbing
		boolean isGrabbing = dubSol.get() == Value.kForward;
		return isGrabbing;
	}
	public DoubleSolenoid getDubSolenoid() {
		return dubSol;
	}

	@Override
	public void stop() {
		dubSol.set(Value.kOff);
	}

}
