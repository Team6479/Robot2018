package org.usfirst.frc.team6479.robot.autonomous;

import org.usfirst.frc.team6479.robot.autonomous.manager.StartPosition;

public class Baseline extends BaseAutonomous {
	
	//nothing should be done
	public Baseline(StartPosition pos) {
		super(pos);
	}

	//what happens when robot is positioned on the center
	@Override
	protected void center() {
		System.out.println("Baseline Center Autonomous");
	}

	//what happens when robot is positioned on the left
	@Override
	protected void left() {
		System.out.println("Baseline Left Autonomous");
	}

	//what happens when robot is positioned on the right
	@Override
	protected void right() {
		System.out.println("Baseline Right Autonomous");
	}
	
}