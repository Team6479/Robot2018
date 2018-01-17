package org.usfirst.frc.team6479.robot.jetson;

import edu.wpi.first.networktables.NetworkTableInstance;



//TODO: update with code from learning board
//TODO: make not a singleton so we have more control
//Class to control all connections to the Jetson
public class JetsonConnection {
	
	//create a server network table
	private JetsonConnection() {
		//make a network table instance
		table = NetworkTableInstance.create();
		//listen on any address
		table.startServer("JetsonTable", "", 1182);
		
	}

	//this will keep the table alive
	private NetworkTableInstance table;
	//instance that is available to users
	public static JetsonConnection connection = new JetsonConnection();
	
}
