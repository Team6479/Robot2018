/*package org.usfirst.frc.team6479.robot.logger;

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

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
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
		String toBeWritten = String.format("%s", information);
		writer.println(toBeWritten);
	}
}*/
