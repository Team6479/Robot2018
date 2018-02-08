package org.usfirst.frc.team6479.robot.autonomous;

import org.usfirst.frc.team6479.robot.autonomous.manager.StartPosition;

public class Scale extends BaseAutonomous {

	//nothing should be done
	public Scale(StartPosition pos) {
		super(pos);
	}

	//what happens when robot is positioned on the center
	@Override
	protected void center() {
		System.out.println("Scale Center Autonomous");
	}

	//what happens when robot is positioned on the left
	@Override
	protected void left() {
		System.out.println("Scale Left Autonomous");
	}

	//what happens when robot is positioned on the right
	@Override
	protected void right() {
		System.out.println("Scale Right Autonomous");
	}

}
