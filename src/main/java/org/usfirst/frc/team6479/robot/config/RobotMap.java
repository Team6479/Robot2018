package org.usfirst.frc.team6479.robot.config;

public class RobotMap {

    //DS
	//controllers
	public static final int driverController = 0;
	public static final int assistantController = 1;
	//END DS


	//PWM
	//drivetrain
	public static final int leftBackPort = 0;
	public static final int rightBackPort = 1;
	public static final int leftFrontPort = 2;
	public static final int rightFrontPort = 3;
	 //pulley motors
    public static final int winchTopPort = 4;
    public static final int winchBottomPort = 5;
    //END PWM

    //DIO
	//encoders for the drive train
	public static final int leftEncoderAPort = 0;
	public static final int leftEncoderBPort = 1;
	public static final int rightEncoderAPort = 2;
	public static final int rightEncoderBPort = 3;
	//encoder for pulley
    public static final int pulleyEncoderAPort = 4;
    public static final int pulleyEncoderBPort = 5;
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
	//pulley single solenoid
	public static final int winchSolPort = 0;
	public static final int gearboxSolPort = 1;
	//grabber piston
    public static final int grabberOnPort = 2;
    public static final int grabberOffPort = 3;
    //Pusher Piston
    public static final int pusherOnPort = 4;
    public static final int pusherOffPort = 5;
    //wheely bar piston
    public static final int wheelyOnPort = 6;
    public static final int wheelyOffPort = 7;
    //END PCM

	//compressor
	//public static final int compressorPort = 0;
}
