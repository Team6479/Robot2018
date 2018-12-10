package org.usfirst.frc.team6479.robot.control;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team6479.robot.drivers.Joystick;
import org.usfirst.frc.team6479.robot.JoystickMap;

public class OI {
    public Joystick stick = new Joystick(0);
    public Button trigger = new JoystickButton(stick, JoystickMap.joystickButton1);
    public Button sideButton = new JoystickButton(stick, JoystickMap.joystickButton2);
	public Button rightButton = new JoystickButton(stick, JoystickMap.joystickButton13);
}
