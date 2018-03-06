package org.usfirst.frc.team6479.robot.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.StringJoiner;
import java.util.Timer;
import java.util.TimerTask;

public class DataLogger {

	private File logFile;
	private PrintWriter writer;
	private long startTimeMilli;
	private long intervalInMilli;
	private Timer logger;
	private TimerTask logTask;

	public DataLogger(long intervalInMilli) {
		this.intervalInMilli = intervalInMilli;
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
			e.printStackTrace();
		}

		startTimeMilli = System.currentTimeMillis();
		logger = new Timer();
		logTask = new TimerTask() {
			@Override
			public void run() {
				//compute how long the logger has been running
				long robotTime = System.currentTimeMillis() - startTimeMilli;
				writer.printf("%08ld:%s\n", robotTime, infoToLog);
			}
		};
	}

	//choose location to log based on wether flashdrive is plugged in
	private void initLogFile(String name) {

		name += ".log";

		File flashdrive = new File("media/sda1/");
		File local = new File("logs/");

		//if flashdrive is plugged in, log here
		if(flashdrive.exists()) {
			logFile = new File(flashdrive.getAbsolutePath() + name);
		}
		//otherwise, log locally
		else {
			if(!local.exists()) {
				local.mkdirs();
			}
			logFile = new File(local.getAbsolutePath() + name);
		}
	}

	//what will be logged by the logger;
	private String infoToLog;
	public synchronized void setLogInfo(LinkedHashMap<String, String> infoToMap) {
		StringJoiner joiner = new StringJoiner(",", "", "");
		infoToMap.forEach((key, value) -> {
			String entry = String.format("%s=%s", key, value);
			joiner.add(entry);
		});
		infoToLog = joiner.toString();
	}
	public void start() {
		logger.scheduleAtFixedRate(logTask, 0, intervalInMilli);
	}
	public void stop() {
		logger.cancel();
		writer.flush();
	}
}
