/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
// RIP FRC 2020 

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.BallSuck;
import frc.robot.commands.ControlClimb;
import frc.robot.commands.OutTake;
import frc.robot.commands.pushOut;
import frc.robot.commands.autonomous.A_FurthestFromWall;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final A_FurthestFromWall m_autoCommand = new A_FurthestFromWall();


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    Joystick xboxDrive = new Joystick(Constants.xbox_drive);
    Joystick xboxShoot = new Joystick(Constants.xbox_shoot);
    JoystickButton aButton = new JoystickButton(xboxShoot, Constants.A);
    JoystickButton bButton = new JoystickButton(xboxShoot, Constants.B);
    JoystickButton XButton = new JoystickButton(xboxShoot, Constants.X);
    JoystickButton XDriveButton = new JoystickButton(xboxDrive, Constants.X);
    JoystickButton aButton_drive = new JoystickButton(xboxDrive, Constants.A);
    
    aButton.whileHeld(new OutTake(0.8));
    XButton.whileHeld(new OutTake(0.9));
    bButton.toggleWhenPressed(new ControlClimb(0.5,false));

    XDriveButton.whileHeld(new pushOut());
    
    aButton_drive.toggleWhenPressed(new BallSuck());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
