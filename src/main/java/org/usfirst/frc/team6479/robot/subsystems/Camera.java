package org.usfirst.frc.team6479.robot.subsystems;

import org.usfirst.frc.team6479.robot.config.RobotMap;
import org.usfirst.frc.team6479.robot.connection.JetsonServer;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

import communication.JetsonPacket.ModePacket.Mode;

//not a true subsystem
//this controls the spike for the light and manages the JetsonServer
public class Camera extends Subsystem implements SafeSubsystem {

    private JetsonServer jetson;
    //spike for the light
    private Relay light;
    public Camera() {

        jetson = new JetsonServer(1182);

        light = new Relay(RobotMap.lightSpikePort);
    }
    @Override
    protected void initDefaultCommand() {

    }
    public void lightOn() {
        light.set(Value.kForward);
    }
    public void lightOff() {
        light.set(Value.kOff);
    }
    public boolean isLightOn() {
        return light.get() != Value.kOff;
    }
    public Mode currentCameraMode() {
    		return jetson.getMode();
    }
    public void setCameraMode(Mode mode) {
    		jetson.setMode(mode);
    }
    public Double getCurrentDistance() {
		return jetson.getDistance();
    }
    @Override
    public void stop() {
        lightOff();
        setCameraMode(Mode.NONE);
    }

}
