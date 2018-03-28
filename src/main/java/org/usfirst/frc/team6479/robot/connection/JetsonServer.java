package org.usfirst.frc.team6479.robot.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import org.usfirst.frc.team6479.robot.Robot;
import org.usfirst.frc.team6479.robot.logger.RobotEvent;

import communication.JetsonPacket.CameraPacket;
import communication.JetsonPacket.ModePacket;

//Class to control all connections to the jetson
public class JetsonServer {

	public static final double CUBE_NOT_FOUND = 9999;
	
    private ServerSocket server;
    private Thread thread;

    public JetsonServer(int port) {

        dataRecieved = CameraPacket.getDefaultInstance();

        // start a thread to connect
        thread = new Thread(() -> {
            try {
                server = new ServerSocket(port);

                // loop forever
                while (!thread.isInterrupted()) {
                		Robot.eventLogger.writeToLog(RobotEvent.START_JETSON_CONNECTION);
                    // get a connection
                    Socket client = server.accept();
                    if (client == null) {
                        continue;
                    }
                    InputStream in = client.getInputStream();
                    OutputStream out = client.getOutputStream();

                    Robot.eventLogger.writeToLog(RobotEvent.JETSON_CONNECTED);
                    try {
                        // contstantly check for a new distance and send the current mode
                        while (!client.isClosed()) {
                            // read in from the current buffer
                            dataRecieved = CameraPacket.parseDelimitedFrom(in);
                            // out.write(dataOutput.toByteArray());
                            dataOutput.writeDelimitedTo(out);
                        }
                    }
                    catch (SocketException e) {
                        //socket closed
                    		Robot.eventLogger.writeToLog(RobotEvent.JETSON_DISCONNECTED);
                    }
                }
            }
            catch (IOException e) {
            		Robot.eventLogger.writeToLog(RobotEvent.JETSON_FAILED);
            }

        });
        thread.setDaemon(true);
        thread.start();
    }

    public synchronized Double getDistance() {
        return dataRecieved.getDistance();
    }

    public synchronized void setMode(ModePacket.Mode mode) {
        dataOutput = ModePacket.newBuilder().setMode(mode).build();
    }
    public synchronized ModePacket.Mode getMode() {
    		return dataOutput.getMode();
    }
    
    private ModePacket dataOutput;
    private CameraPacket dataRecieved;
}
