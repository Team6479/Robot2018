package org.usfirst.frc.team6479.robot.autonomous.manager;

import org.usfirst.frc.team6479.robot.autonomous.BaseAutonomous;
import org.usfirst.frc.team6479.robot.autonomous.Baseline;
import org.usfirst.frc.team6479.robot.autonomous.Scale;
import org.usfirst.frc.team6479.robot.autonomous.Switch;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//controller for all autonomous commands
public class AutonomousManager {

    private SendableChooser<StartPosition> startPosChooser;
    private SendableChooser<AutoGoal> goalChooser;
    
    //init all autonomous routines
    //put chooser on smart dashboard
    public AutonomousManager() {
        
    		//set the start position of the robot
        startPosChooser = new SendableChooser<StartPosition>();
        startPosChooser.addDefault(StartPosition.s_center.getKey(), StartPosition.s_center);
        startPosChooser.addObject(StartPosition.s_left.getKey(), StartPosition.s_left);
        startPosChooser.addObject(StartPosition.s_right.getKey(), StartPosition.s_right);
        SmartDashboard.putData(StartPosition.name, startPosChooser);
        
        //set the goal for autonomous
        goalChooser = new SendableChooser<AutoGoal>();
        goalChooser.addDefault(AutoGoal.a_switch.getKey(), AutoGoal.a_switch);
        goalChooser.addObject(AutoGoal.a_scale.getKey(), AutoGoal.a_scale);
        goalChooser.addObject(AutoGoal.a_baseline.getKey(), AutoGoal.a_baseline);
        SmartDashboard.putData(AutoGoal.name, goalChooser);
    }
    
    //get the auto routine at init
    public BaseAutonomous getAuto() {
    		
    		StartPosition pos = startPosChooser.getSelected();
    		AutoGoal goal = goalChooser.getSelected();
    		
    		//get the data from the choosers
    		switch(goal) {
			case a_baseline:
				return new Baseline(pos);
			case a_scale:
				return new Scale(pos);
			case a_switch:
				return new Switch(pos);
			default:
				return null;
    		}
    }
    
    //start auto with a the auto from the chooser
    public void startAuto() {
    		BaseAutonomous auto = getAuto();
    		startAuto(auto);
    }
    
    //start the auto with an inputed auto
    public void startAuto(BaseAutonomous auto) {
    		auto.start();
    }
}
