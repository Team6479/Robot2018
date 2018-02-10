package org.usfirst.frc.team6479.robot.autonomous;

import org.usfirst.frc.team6479.robot.autonomous.manager.StartPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

import openrio.powerup.MatchData;
import openrio.powerup.MatchData.GameFeature;
import openrio.powerup.MatchData.OwnedSide;

//base class for autonomous routines
public abstract class BaseAutonomous extends CommandGroup {

	protected StartPosition pos;
	//which side is owned by robots alliance
	protected OwnedSide nearSwitch;
	protected OwnedSide scale;
	protected OwnedSide farSwitch;
	public BaseAutonomous(StartPosition pos) {
		this.pos = pos;
		nearSwitch = MatchData.getOwnedSide(GameFeature.SWITCH_NEAR);
		scale = MatchData.getOwnedSide(GameFeature.SCALE);
		farSwitch = MatchData.getOwnedSide(GameFeature.SWITCH_FAR);
		switch(pos) {
			case s_center:
				center();
				break;
			case s_left:
				left();
				break;
			case s_right:
				right();
				break;
			default:
				break;

		}
	}

	//auto routine if the robot starts in the center
	protected abstract void center();
	protected abstract void left();
	protected abstract void right();

}
