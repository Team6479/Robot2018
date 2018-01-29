package org.usfirst.frc.team6479.robot.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import communication.JetsonPacket.CameraPacket;
import communication.JetsonPacket.ModePacket;

//Class to control all connections to the jetson
public class JetsonServer {

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
                    System.out.println("Connecting to a new client");
                    // get a connection
                    Socket client = server.accept();
                    if (client == null) {
                        continue;
                    }
                    InputStream in = client.getInputStream();
                    OutputStream out = client.getOutputStream();

                    System.out.println("Connected to client at " + client.getInetAddress());
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
                    }
                }
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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

    private ModePacket dataOutput;
    private CameraPacket dataRecieved;
}
