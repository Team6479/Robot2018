# Robot2018

[![Build Status](https://travis-ci.org/Team6479/Robot2018.svg?branch=master)](https://travis-ci.org/Team6479/Robot2018)

This is Team 6479's code for the 2018 FIRST Robotics Competition season.


## Libraries Used
| Library               | Creator   |
|:--------------------- |:--------- |
| [Robot Library][1]    | Team 6479 |
| [Protocol Buffers][5] | Google    |


## About The Code
Our team uses a Nvidia Jetson TX1 to process camera input and send that data to the roboRIO. Our Jetson also streams images to our driver station. Our team uses the WPI Shuffleboard as its driver station dashboard.

| Repository                         | Creator   |
|:---------------------------------- |:--------- |
| [Vision Processing][2]             | Team 6479 |
| [Driver Station Image Receiver][3] | Team 6479 |
| [Shuffleboard][4]                  | WPI       |


## ![Controller Mappings](https://github.com/Team6479/Robot2018/wiki/Controller-Mappings)

## ![Autonomous Modes](https://github.com/Team6479/Robot2018/wiki/Autonomous-Modes)


## TODO List




[1]: https://github.com/Team6479/RobotLibrary
[2]: https://github.com/Team6479/Vision2018
[3]: https://github.com/Team6479/JetsonStream
[4]: https://github.com/wpilibsuite/shuffleboard
[5]: https://developers.google.com/protocol-buffers/
[6]: ./src/main/java/org/usfirst/frc/team6479/robot/commands/teleop/RacingDrive.java
[7]: ./src/main/java/org/usfirst/frc/team6479/robot/commands/teleop/TogglePusher.java
[8]: ./src/main/java/org/usfirst/frc/team6479/robot/commands/teleop/ToggleGrabber.java
[9]: ./src/main/java/org/usfirst/frc/team6479/robot/autonomous/manager/AutonomousManager.java
[10]: ./src/main/java/org/usfirst/frc/team6479/robot/commands/teleop/ElevatorControl.java
[11]: ./src/main/java/org/usfirst/frc/team6479/robot/commands/teleop/ToggleShifter.java
[12]: ./src/main/java/org/usfirst/frc/team6479/robot/commands/teleop/ToggleStopper.java
[13]: ./src/main/java/org/usfirst/frc/team6479/robot/Robot.java
[14]: ./src/main/java/org/usfirst/frc/team6479/robot/commands/teleop/ToggleWheely.java
