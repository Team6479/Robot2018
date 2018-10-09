package org.usfirst.frc.team6479.robot.config;

public class RobotMap {

    //DS
	//controllers
	public static final int driverController = 0;
	//END DS


	//PWM
	//drivetrain
	public static final int leftBackPort = 0;
	public static final int rightBackPort = 1;
	public static final int leftFrontPort = 2;
	public static final int rightFrontPort = 3;
	// Grabber Motors
	public static final int grabberLeftPort = 7;
	public static final int grabberRightPort = 6;
	// Elevator motors
    public static final int elevatorFrontPort = 4;
    public static final int elevatorBackPort = 5;
    //END PWM

    //DIO
	//encoders for the drive train
	public static final int leftEncoderAPort = 2;
	public static final int leftEncoderBPort = 3;
	public static final int rightEncoderAPort = 0;
	public static final int rightEncoderBPort = 1;
	//encoder for elevator
    public static final int elevatorEncoderAPort = 4;
    public static final int elevatorEncoderBPort = 5;
    //vex sonar
    public static final int leftInputPing = 6;
    public static final int leftOutputEcho = 7;
    public static final int rightInputPing = 8;
    public static final int rightOutputEcho = 9;
    //END DIO

    //ANALOG

    //END ANALOG

    //RELAY
    //spike to turn light on and off
    public static final int lightSpikePort = 0;
    //END RELAY

    //PCM
    
    //END PCM

	//compressor
	//public static final int compressorPort = 0;
}
