package org.usfirst.frc.team6479.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6479.robot.config.RobotMap;

public class Pusher extends Subsystem implements SafeSubsystem {
    DoubleSolenoid dubSol;

    public Pusher() {
        dubSol = new DoubleSolenoid(RobotMap.pusherOnPort, RobotMap.pusherOffPort);
    }

    @Override
    protected void initDefaultCommand() {

    }

    //Extends Piston
    public void extend(){
        dubSol.set(DoubleSolenoid.Value.kForward);
    }

    //Retracts Piston
    public void retract() {
        dubSol.set(DoubleSolenoid.Value.kReverse);
    }

    /**
     * @return if the Double Solenoid is extended or not
     */
    public boolean isExtend() {
        boolean status;
        status = dubSol.get() == DoubleSolenoid.Value.kForward;
        return status;
    }

    /**
     * @return DoubleSolenoid object
     */
    public DoubleSolenoid getDubSol() {
        return dubSol;
    }

    @Override
    public void stop() {
        dubSol.set(DoubleSolenoid.Value.kOff);
    }
}

