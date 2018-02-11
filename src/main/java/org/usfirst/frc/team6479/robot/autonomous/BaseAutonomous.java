package org.usfirst.frc.team6479.robot.autonomous;

import org.usfirst.frc.team6479.robot.autonomous.manager.StartPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

import openrio.powerup.MatchData;
import openrio.powerup.MatchData.GameFeature;
import openrio.powerup.MatchData.OwnedSide;

//base class for autonomous routines
public abstract class BaseAutonomous extends CommandGroup {

	protected StartPosition pos;
	//wether this should use sensors or dead reckoning
	protected boolean shouldUseSensors;
	//which side is owned by robots alliance
	protected OwnedSide nearSwitch;
	protected OwnedSide scale;
	protected OwnedSide farSwitch;
	public BaseAutonomous(StartPosition pos, boolean shouldUseSensors) {
		this.pos = pos;
		this.shouldUseSensors = shouldUseSensors;
		nearSwitch = MatchData.getOwnedSide(GameFeature.SWITCH_NEAR);
		scale = MatchData.getOwnedSide(GameFeature.SCALE);
		farSwitch = MatchData.getOwnedSide(GameFeature.SWITCH_FAR);
		switch(pos) {
			case s_center:
				if (shouldUseSensors) {
					center();
				}
				else {
					deadReckonCenter();
				}
				break;
			case s_left:
				if (shouldUseSensors) {
					left();
				}
				else {
					deadReckonLeft();
				}
				break;
			case s_right:
				if (shouldUseSensors) {
					right();
				}
				else {
					deadReckonRight();
				}
				break;
			default:
				break;

		}
	}

	//auto routine for different modes
	protected abstract void center();
	protected abstract void left();
	protected abstract void right();
	protected abstract void deadReckonCenter();
	protected abstract void deadReckonLeft();
	protected abstract void deadReckonRight();

}
