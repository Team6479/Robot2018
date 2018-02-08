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
| Name         | Port | Value      | Function       | Location                |
|:------------ |:---- |:---------- |:-------------- |:----------------------- |
| A            | 1    | true/false |                |                         |
| B            | 2    | true/false |                |                         |
| X            | 3    | true/false |                |                         |
| Y            | 4    | true/false |                |                         |
| Left Bumper  | 5    | true/false | Toggle Pusher  | [TogglePusher.java][7]  |
| Right Bumper | 6    | true/false | Toggle Grabber | [ToggleGrabber.java][8] |
| Back         | 7    | true/false |                |                         |
| Start        | 8    | true/false |                |                         |
| Left Stick   | 9    | true/false |                |                         |
| Right Stick  | 10   | true/false |                |                         |

### Axes
| Name          | Port | Value   | Function      | Location              |
|:------------- |:---- |:------- |:------------- |:--------------------- |
| X Left Stick  | 0    | -1 to 1 | Drive Turn    | [RacingDrive.java][6] |
| Y Left Stick  | 1    | -1 to 1 |               |                       |
| Left Trigger  | 2    | 0 to 1  | Drive Reverse | [RacingDrive.java][6] |
| Right Trigger | 3    | 0 to 1  | Drive Forward | [RacingDrive.java][6] |
| X Right Stick | 4    | -1 to 1 |               |                       |
| Y Right Stick | 5    | -1 to 1 |               |                       |


## Autonomous Modes
All of our autonomous routines are handled by [AutonomousManager.java][9]. This is an overview of what the autonomous routine are and what they do.
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
- [x] Solve dead zone issues on controllers
- [x] Implement exponential formula on CameraDrive
- [ ] Improve encoder accuracy
- [x] Add encoders to StraightDrive
- [x] Have StraightDrive use either encoders to go certain distance or use sonar to go until a distance
- [x] Remove SonarDrive
- [x] Add detection for reflective tape
- [ ] Implement autonomous commands into the autonomous manager
- [ ] Improve cube detection
- [ ] Add collision detection
- [ ] Refactor CameraDrive to CameraRotate
- [ ] Implement a new CameraDrive that functions like StraightDrive but uses Camera instead of Gyro



[1]: https://github.com/Team6479/RobotLibrary
[2]: https://github.com/Team6479/Vision2018
[3]: https://github.com/Team6479/JetsonStream
[4]: https://github.com/wpilibsuite/shuffleboard
[5]: https://developers.google.com/protocol-buffers/
[6]: ./src/main/java/org/usfirst/frc/team6479/robot/commands/teleop/RacingDrive.java
[7]: ./src/main/java/org/usfirst/frc/team6479/robot/commands/teleop/TogglePusher.java
[8]: ./src/main/java/org/usfirst/frc/team6479/robot/commands/teleop/ToggleGrabber.java
[9]: ./src/main/java/org/usfirst/frc/team6479/robot/autonomous/manager/AutonomousManager.java
