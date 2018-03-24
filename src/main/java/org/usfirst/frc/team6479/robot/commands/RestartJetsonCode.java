package org.usfirst.frc.team6479.robot.commands;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import edu.wpi.first.wpilibj.command.Command;


public class RestartJetsonCode extends Command {
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
			channelExec.setCommand("sh restartVisionCode.sh");
			channelExec.connect();
		} catch (JSchException e) {
			e.printStackTrace();
		}

	}


    /**
     * <p>
     * Returns whether this command is finished. If it is, then the command will be removed and
     * {@link #end()} will be called.
     * </p><p>
     * It may be useful for a team to reference the {@link #isTimedOut()}
     * method for time-sensitive commands.
     * </p><p>
     * Returning false will result in the command never ending automatically. It may still be
     * cancelled manually or interrupted by another command. Returning true will result in the
     * command executing once and finishing immediately. It is recommended to use
     * {@link edu.wpi.first.wpilibj.command.InstantCommand} (added in 2017) for this.
     * </p>
     *
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }


    /**
     * Called once when the command ended peacefully; that is it is called once
     * after {@link #isFinished()} returns true. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    @Override
    protected void end() {

    }


    /**
     * <p>
     * Called when the command ends because somebody called {@link #cancel()} or
     * another command shared the same requirements as this one, and booted it out. For example,
     * it is called when another command which requires one or more of the same
     * subsystems is scheduled to run.
     * </p><p>
     * This is where you may want to wrap up loose ends, like shutting off a motor that was being
     * used in the command.
     * </p><p>
     * Generally, it is useful to simply call the {@link #end()} method within this
     * method, as done here.
     * </p>
     */
    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
