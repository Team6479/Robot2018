package org.usfirst.frc.team6479.robot.commands;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import edu.wpi.first.wpilibj.command.InstantCommand;


public class RestartJetsonCode extends InstantCommand {
	public static final String IP = "10.64.79.10";
	public static final String LOGIN = "nvidia";
	public static final String PASSWORD = "nvidia";

	private JSch jsch;
	private Session session;

	public RestartJetsonCode() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		jsch = new JSch();
		try {
			session = jsch.getSession(LOGIN, IP, 22);
		} catch (JSchException e) {
			e.printStackTrace();
		}
		session.setPassword(PASSWORD);
		session.setConfig("StrictHostKeyChecking", "no");
	}

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
		try {
			session.connect();
		} catch (JSchException e) {
			System.out.println("Connection Failed");
			e.printStackTrace();
		}

		System.out.println("Connection Successful");

		try {
			ChannelExec channelExec = (ChannelExec)session.openChannel("exec");
			channelExec.setCommand("sh /home/nvidia/restartVisionCode.sh");
			channelExec.connect();
		} catch (JSchException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void end() {
    	session.disconnect();
	}
}
