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


## Controller Mappings
The mapping of our code to a single Xbox360 Controller.

### Buttons
| Name         | Port | Value      | Function                         | Location                 |
|:------------ |:---- |:---------- |:-------------------------------- |:------------------------ |
| A            | 1    | true/false |                                  |                          |
| B            | 2    | true/false | Toggle Between Winch and Climber | [ToggleShifter.java][11] |
| X            | 3    | true/false | Toggle Winch Lock                | [ToggleStopper.java][12] |
| Y            | 4    | true/false |                                  |                          |
| Left Bumper  | 5    | true/false | Toggle Pusher                    | [TogglePusher.java][7]   |
| Right Bumper | 6    | true/false | Toggle Grabber                   | [ToggleGrabber.java][8]  |
| Back         | 7    | true/false |                                  |                          |
| Start        | 8    | true/false |                                  |                          |
| Left Stick   | 9    | true/false |                                  |                          |
| Right Stick  | 10   | true/false |                                  |                          |

### Axes
| Name          | Port | Value   | Function      | Location                   |
|:------------- |:---- |:------- |:------------- |:-------------------------- |
| X Left Stick  | 0    | -1 to 1 | Drive Turn    | [RacingDrive.java][6]      |
| Y Left Stick  | 1    | -1 to 1 |               |                            |
| Left Trigger  | 2    | 0 to 1  | Drive Reverse | [RacingDrive.java][6]      |
| Right Trigger | 3    | 0 to 1  | Drive Forward | [RacingDrive.java][6]      |
| X Right Stick | 4    | -1 to 1 |               |                            |
| Y Right Stick | 5    | -1 to 1 | Move Elevator | [ElevatorControl.java][10] |


## Autonomous Modes
All of our autonomous routines are handled by [AutonomousManager.java][9]. This is an overview of what the autonomous routine are and what they do. There is also a switch in the driver station to use either sensors or dead reckoning for the autonomous. The default is to use sensors, the only reason for this is as a backup if sensors fail.

| Name            | Function                                                                         |
|:--------------- |:-------------------------------------------------------------------------------- |
| Left Baseline   | Move the robot past the baseline when the robot starts on the left               |
| Right Baseline  | Move the robot past the baseline when the robot starts on the right              |
| Center Baseline | Move the robot past the baseline when the robot starts in the center             |
| Left Switch     | Move the robot to the switch and drop a cube when the robot starts on the left   |
| Right Switch    | Move the robot to the switch and drop a cube when the robot starts on the right  |
| Center Switch   | Move the robot to the switch and drop a cube when the robot starts in the center |
| Left Scale      | Move the robot to the scale and drop a cube when the robot starts on the left    |
| Right Scale     | Move the robot to the scale and drop a cube when the robot starts on the right   |
| Center Scale    | Move the robot to the scale and drop a cube when the robot starts in the center  |


## TODO List
- [ ] Improve encoder accuracy
- [ ] Implement autonomous commands into the autonomous manager
- [ ] Add fancy formula to FlushDrive
- [ ] Add fancy formula to CameraDrive
- [ ] Test all code in safe environment in small tests before testing in large environment
- [ ] Implement camera offset in vision code
- [ ] look into calculating angle offset with camera instead of pixel offset
- [ ] possibly mount camera in center bottom, then have cube be lifted up to reveal camera
- [ ] find distance using camera
- [ ] Reduce acceleration to reduce tipping
- [ ] Look into "Sniper Mode" for increased accuracy but reduced speed.



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
