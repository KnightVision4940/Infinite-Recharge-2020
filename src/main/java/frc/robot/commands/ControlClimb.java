/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
// RIP FRC 2020 

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.Robot;


public class ControlClimb extends CommandBase {
  /**
   * Creates a new ControlClimb.
   */
  
  Joystick xbox = new Joystick(Constants.xbox_drive);
  JoystickButton rBumper = new JoystickButton(xbox, Constants.RB);
  JoystickButton lBumper = new JoystickButton(xbox, Constants.LB);
  double speed;
  int bottomLimit;
  int topLimit;
  boolean setLimits;
  
  public ControlClimb(double speed, boolean setLimits) {
    this.speed=speed;
    this.setLimits = setLimits;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Robot.Climber.setLimits();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Set limits if this command is run during auto
    if(setLimits){
      Robot.Climber.setLimits();
      end(true);
    // Otherwise the climb will just run
    }else{
      Robot.Climber.move(Robot.getYRight());
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
