package org.usfirst.frc.team6479.robot.subsystems;

import org.usfirst.frc.team6479.robot.commands.teleop.ToggleWheely;
import org.usfirst.frc.team6479.robot.config.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

//subsystem for the wheely bar
public class WheelyBar extends Subsystem implements SafeSubsystem {

	private DoubleSolenoid dubSol;

	public WheelyBar() {
		dubSol = new DoubleSolenoid(RobotMap.wheelyOnPort, RobotMap.wheelyOffPort);
	}
    @Override
    protected void initDefaultCommand() {
		setDefaultCommand(new ToggleWheely());
    }
    //push the wheely bar out
	public void down() {
		dubSol.set(Value.kForward);
	}
	//pull the wheely bar back in
	public void up() {
		dubSol.set(Value.kReverse);
	}
	//true if currently down
	public boolean isDown() {
		return dubSol.get() == Value.kForward;
	}
	//true if currently up
	public boolean isUp() {
		return !isDown();
	}
	public DoubleSolenoid getDubSolenoid() {
		return dubSol;
	}

	@Override
	public void stop() {
		dubSol.set(Value.kOff);
	}

}
