package org.usfirst.frc.team6479.robot.util;

import edu.wpi.first.wpilibj.XboxController;
import robot.xbox.XboxMap;

public class XboxControllerDeadzone extends XboxController {
	double deadZone;

	/**
	 * Construct an instance of a joystick. The joystick index is the USB port on the drivers
	 * station.
	 *
	 * @param port The port on the Driver Station that the joystick is plugged into.
	 */
	public XboxControllerDeadzone(int port) {
		super(port);
		deadZone = 2.0;
	}

	@Override
	public double getRawAxis(int axis) {
		double rawAxis = super.getRawAxis(axis);
		//double  mag = Math.sq

		if (axis == XboxMap.LeftJoystickX) {

		}
		else if (axis == XboxMap.LeftJoystickY) {

		}
		else if (axis == XboxMap.RightJoystickX) {

		}
		else if (axis == XboxMap.RightJoyStickY) {

		}
		else {

		}
		return rawAxis;
	}

}
