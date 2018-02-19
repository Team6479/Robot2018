package org.usfirst.frc.team6479.robot.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RobotLogger {

	private File logFile;
	private Date startTime;
	private PrintWriter writer;
	private RobotLogger() {
		startTime = new Date();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy:HH-mm-ss");
		String formattedDate = formatter.format(startTime);
		
		
		logFile = new File("media/sda1/" + formattedDate);
		try 
		{
			writer = new PrintWriter(new FileOutputStream(logFile), true);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static RobotLogger logger = new RobotLogger();
	
	public void log(String information) {
		Date currentTime = new Date();
		long time = currentTime.getTime() - startTime.getTime();
		
		long minutes = time / 60;
		long seconds = time % 60 / 60;
		
		String toBeWritten = String.format("%2d:%2.2d %s", minutes, seconds, information);
		writer.println(toBeWritten);
	}
}
