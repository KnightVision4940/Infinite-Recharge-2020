/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AutoLeft;
import frc.robot.commands.BallSuck;
import frc.robot.commands.OutTake;
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

  private final AutoLeft m_autoCommand = new AutoLeft();



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
    Joystick xbox = new Joystick(Constants.xbox_drive);
    Joystick xbox2 = new Joystick(Constants.xbox_shoot);
    JoystickButton aButton = new JoystickButton(xbox2, 1);
    JoystickButton aButton_drive = new JoystickButton(xbox, 1);
    
    // JoystickButton bButton = new JoystickButton(xbox, 1);
    //Wouldn't it be great just to go insane?
    // JoystickButton yButton = new JoystickButton(xbox, 3);
    aButton.whileHeld(new OutTake());
    aButton_drive.toggleWhenPressed(new BallSuck());
    // aButton.toggleWhenPressed(new OutTake());
  }
  // xButton.toggleWhenPressed(new OutTake());


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
