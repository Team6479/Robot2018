package org.usfirst.frc.team6479.robot.commands.auton;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6479.robot.Robot;

public class GyroDrive extends Command {
    public enum direction{
        dLeft, dRight
    }

    private double gyroAngle;
    private double prevGyroAngle;
    private double angleGoal;
    private double tmpAngleGoal;
    private double leftSpeed;
    private double rightSpeed;

    private static final double SPEED_THRESHOLD = 0.40;
    private static final int ANGLE_DELTA = 8;

    //COT = Change over time
    private double angleCOT;

    private direction dir;


    public GyroDrive(double angle, direction dir) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
        angleGoal = angle;

        this.dir = dir;
    }

    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        System.out.println("Starting!!!!");
        Robot.drivetrain.getGyro().reset();

        if (dir == direction.dLeft) {
            Robot.drivetrain.turnLeft();
        }
        else if (dir == direction.dRight) {
            Robot.drivetrain.turnRight();
        }

        tmpAngleGoal = angleGoal / ANGLE_DELTA;

        leftSpeed = 0.75;
        rightSpeed = 0.75;
    }

    /**
     *
     * @param gVal1 1st supplied angle
     * @param gVal2 2nd supplied angle
     * @return angle change over time
     */
    private double getAngleCOT(double gVal1, double gVal2) {
        return Math.abs(gVal1 - gVal2);
    }

    /**
     *
     * @param dub1 1st Supplied Double
     * @param dub2 2nd Supplied Double
     * @param range to check if doubles are within
     * @return true if two doubles are in provided range
     */
    private boolean isInRange(double dub1, double dub2, double range) {
        return Math.abs(dub1 - dub2) <= range;
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        prevGyroAngle = gyroAngle;
        gyroAngle = Math.abs(Robot.drivetrain.getGyro().getAngle());

        System.out.println("Gyro At: " + gyroAngle);
        System.out.println("TMP Angle: " + tmpAngleGoal);

        angleCOT = this.getAngleCOT(prevGyroAngle, gyroAngle);

        Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);

        System.out.println("Left Speed: " + leftSpeed);
        System.out.println("Right Speed: " + rightSpeed);
        if (this.isInRange(gyroAngle, tmpAngleGoal, angleCOT)) {
            //Recalculate Temp Angle Goal
            if (tmpAngleGoal + (angleGoal / ANGLE_DELTA) < angleGoal) {
                tmpAngleGoal += (angleGoal / ANGLE_DELTA);
            }
            else {
                tmpAngleGoal = angleGoal;
            }

            System.out.println("Temp Angle Goal " + tmpAngleGoal);
            SmartDashboard.putNumber("tmpAngleGoal", tmpAngleGoal);

            //Recalculate Motor Speeds
            if (leftSpeed * 0.9375 >= SPEED_THRESHOLD) {
                leftSpeed *= 0.9375;
                SmartDashboard.putNumber("Left Speed", leftSpeed);
            }
            if (rightSpeed * 0.9375 >= SPEED_THRESHOLD) {
                rightSpeed *= 0.9375;
                SmartDashboard.putNumber("Right Speed", rightSpeed);
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
        return this.isInRange(gyroAngle, angleGoal, angleCOT);
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
        Robot.drivetrain.getLeftSideMotors().set(0);
        Robot.drivetrain.getRightSideMotors().set(0);
        System.out.println("Final Gyro Read: " + gyroAngle);
        SmartDashboard.putNumber("Final Gyro Val", gyroAngle);
        System.out.println("Accuracy Rating: " + (Math.abs(angleGoal - gyroAngle)));
        SmartDashboard.putNumber("Gyro Accuracy", (Math.abs(angleGoal - gyroAngle)));
    }
}
