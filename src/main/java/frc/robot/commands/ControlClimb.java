/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.Robot;
//s


public class ControlClimb extends CommandBase {
  /**
   * Creates a new ControlClimb.
   */
  
  Joystick xbox = new Joystick(Constants.xbox_drive);
  JoystickButton rBumper = new JoystickButton(xbox, Constants.RB);
  JoystickButton lBumper = new JoystickButton(xbox, Constants.LB);
  int speed;
  
  public ControlClimb(int speed) {
    this.speed=speed;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Robot.leftBumper() == true){
      if(Robot.getYRight() > 0){
      Robot.Climber.move(1, 0);  //left side up
      }
      else if(Robot.getYRight() < 0){
        Robot.Climber.move(-1, 0);  //left side down

      }
      else{
        //do nothing
      }
    }
    else if(Robot.rightBumper() == true){
      if(Robot.getYRight() > 0){
        Robot.Climber.move(0, 1);  //right side up
      }
      else if(Robot.getYRight() < 0){
        Robot.Climber.move(0, 1); //right side down
      }
      else{
        //do nothing
      }
    }
    else if(Robot.leftBumper() == true && Robot.rightBumper() == true){
      if(Robot.getYRight() > 0){
        Robot.Climber.move(1, 1);  //both up
      }
      else if(Robot.getYRight() < 0){
        Robot.Climber.move(-1, -1); //both down
      }
      else{
        //do nothing
      }
    }
    else{
      //do nothing
    }
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
