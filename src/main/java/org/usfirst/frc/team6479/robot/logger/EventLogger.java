package org.usfirst.frc.team6479.robot.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

public class EventLogger {

	private File logFile;
	private PrintWriter writer;
	private long startTimeMilli;

	//wether robot should also log to console
	private boolean shouldConsoleLog;

	public EventLogger() {
		//default is no console log
		this(false);
	}

	public EventLogger(boolean shouldConsoleLog) {
		this.shouldConsoleLog = shouldConsoleLog;
		Date startTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		String formattedDate = formatter.format(startTime);

		//call func to init the logFile
		initLogFile(formattedDate);

		try
		{
			writer = new PrintWriter(new FileOutputStream(logFile), true);
		}
		catch (FileNotFoundException e) {

			//System.exit(1);
			e.printStackTrace();
		}

		startTimeMilli = System.currentTimeMillis();
	}

	//choose location to log based on wether flashdrive is plugged in
	private void initLogFile(String name) {

		name += ".evt";

		//File flashdrive = new File("/media/sda1/");
		File local = new File("/home/lvuser/logs");

		//if flashdrive is plugged in, log here
		/*if(flashdrive.exists()) {
			logFile = new File(flashdrive, name);
		}
		//otherwise, log locally
		else */{
			logFile = new File(local, name);
			local.mkdir();

		}
	}

	//write to log in a way similar to printf
	public void writeToLog(String str, Object... args) {
		String toBeLogged = String.format(str, args);
		writeToLog(toBeLogged);
	}
	//write to log using predefined RobotEvents, such as autonomous start and end, etc
	public void writeToLog(RobotEvent... evts) {
		StringJoiner joiner = new StringJoiner(",", "", "");
		for(RobotEvent evt : evts) {
			joiner.add(evt.getMessage());
		}
		writeToLog(joiner.toString());
	}
	//the general writeToLog, will only be called by this class
	private void writeToLog(String str) {
		//compute how long the logger has been running
		long robotTime = System.currentTimeMillis() - startTimeMilli;
		String log = String.format("%08d:%s\n", robotTime, str);

		//if it should log to the console
		if(shouldConsoleLog) {
			System.out.print(log);
		}
		writer.print(log);
		writer.flush();
	}

	public void shouldConsoleLog(boolean shouldConsoleLog) {
		this.shouldConsoleLog = shouldConsoleLog;
	}
}

