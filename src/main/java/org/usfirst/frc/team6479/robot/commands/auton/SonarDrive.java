package org.usfirst.frc.team6479.robot.commands.auton;

import org.usfirst.frc.team6479.robot.Robot;
import org.usfirst.frc.team6479.robot.commands.auton.GyroDrive.Direction;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SonarDrive extends Command {
    public enum Direction {
        dForward, dBackward
    }

    private double sonarDistance;
    private double prevSonarDistance;
    private double distanceGoal;
    private double tmpDistanceGoal;
    private double speed;

    private static final double SPEED_THRESHOLD = 0.40;
    private static final int DISTANCE_DELTA = 8;

    //COT = Change over time
    private double distanceCOT;

    private Direction dir;


    public SonarDrive(double distance, Direction dir) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
        distanceGoal = distance;

        this.dir = dir;
    }

    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        System.out.println("Starting!!!!");
        Robot.drivetrain.getSonar().resetAccumulator();

        tmpDistanceGoal = distanceGoal / DISTANCE_DELTA;

        speed = 0.5;
    }

    /**
     *
     * @param v1 1st supplied distance
     * @param v2 2nd supplied distance
     * @return angle change over time
     */
    private double getDistanceCOT(double v1, double v2) {
        return Math.abs(v1 - v2);
    }

    /**
     *
     * @param dub1 1st Supplied Double
     * @param dub2 2nd Supplied Double
     * @param range to check if doubles are within
     * @return true if two doubles are in provided range
     */
    private boolean isInRange(double d1, double d2, double range) {
        return Math.abs(d1 - d2) <= range;
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        prevSonarDistance = sonarDistance;
        sonarDistance = Math.abs(Robot.drivetrain.getSonar().getDistance());

        System.out.println("Sonar At: " + sonarDistance);
        System.out.println("TMP Distance: " + tmpDistanceGoal);

        distanceCOT = this.getDistanceCOT(prevSonarDistance, sonarDistance);

        
        if (dir == Direction.dForward) {
            Robot.drivetrain.tankDrive(speed, speed);
        }
        else if (dir == Direction.dBackward) {
            Robot.drivetrain.tankDrive(-speed, -speed);
        }

        System.out.println("Speed: " + speed);
        if (this.isInRange(sonarDistance, tmpDistanceGoal, distanceCOT)) {
            //Recalculate Temp Angle Goal
            if (tmpDistanceGoal + (distanceGoal / DISTANCE_DELTA) < distanceGoal) {
                tmpDistanceGoal += (distanceGoal / DISTANCE_DELTA);
            }
            else {
                tmpDistanceGoal = distanceGoal;
            }

            System.out.println("Temp Sonar Goal " + tmpDistanceGoal);
            SmartDashboard.putNumber("tmpSonarGoal", tmpDistanceGoal);

            //Recalculate Motor Speeds
            if (speed * 0.9375 >= SPEED_THRESHOLD) {
                speed *= 0.9375;
                SmartDashboard.putNumber("Speed", speed);
            }
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
        return this.isInRange(sonarDistance, distanceGoal, distanceCOT);
    }


    /**
     * Called once when the command ended peacefully; that is it is called once
     * after {@link #isFinished()} returns true. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    @Override
    protected void end() {
        System.out.println("Stopping!!!");
        Robot.drivetrain.stop();
        System.out.println("Final Sonar Read: " + sonarDistance);
        SmartDashboard.putNumber("Final Sonar Val", sonarDistance);
        System.out.println("Accuracy Rating: " + (Math.abs(distanceGoal - sonarDistance)));
        SmartDashboard.putNumber("Sonar Accuracy", (Math.abs(distanceGoal - sonarDistance)));
    }
}