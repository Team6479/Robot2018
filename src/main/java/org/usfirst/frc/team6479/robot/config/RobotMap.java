package org.usfirst.frc.team6479.robot.config;

public class RobotMap {

    //driver station
	//controllers
	public static final int xboxPort = 0;

	
	//PWM
	//drivetrain
	public static final int leftBackPort = 0;
	public static final int rightBackPort = 1;
	public static final int leftFrontPort = 2;
	public static final int rightFrontPort = 3;
	 //pulley motors
    public static final int pulley1Port = 4;
    public static final int pulley2Port = 5;
    
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

    //ANALOG
    //sonar
    public static final int sonarPort = 0;
    
    //RELAY
    //spike to turn light on and off
    public static final int lightSpikePort = 0;
	
	//PCM
	//pulley single solenoid
	public static final int pulleySolPort = 0;
	//grabber piston
    public static final int grabberOnPort = 1;
    public static final int grabberOffPort = 2;
    //Pusher Piston
    public static  final int pusherOnPort = 3;
    public static final int pusherOffPort = 4;

	//compressor
	public static final int compressorPort = 0;
}
