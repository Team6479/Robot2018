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
		deadZone = 0.2;
	}

	@Override
	public double getRawAxis(int axis) {
		double x;
		double y;

		if (axis == XboxMap.LeftJoystickX || axis == XboxMap.LeftJoystickY) {
			x = super.getRawAxis(XboxMap.LeftJoystickX);
			y = super.getRawAxis(XboxMap.LeftJoystickY);
		}
		else {
			x = super.getRawAxis(XboxMap.RightJoystickX);
			y = super.getRawAxis(XboxMap.RightJoyStickY);
		}

		//Magnitude
		double mag = Math.sqrt((x * x) + (y * y));

		if (mag > deadZone) {
			double range = 1.0 - deadZone;
			double normMag = Math.min(1.0, (mag - deadZone) / range);
			double scale = normMag / mag;
			x = x * scale;
			y = y * scale;
		}

		if (axis == XboxMap.LeftJoystickX || axis == XboxMap.RightJoystickX) {
			return x;
		}
		else {
			return y;
		}
	}

}
