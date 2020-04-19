/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
// RIP FRC 2020 

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class DriveTurn extends CommandBase {
  int angle;
  int speed = 1;
  public DriveTurn(int angle) {
    this.angle = angle;
    addRequirements(Robot.drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.drive.resetGyro();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    int angle = 0;
    //Checks whether to turn left or right based on speed + or -
    if(speed < 0) { 
      angle = -angle; 
    }
    //The if is here to wait until this function is complete
    if(Robot.drive.turnToAngle(angle, speed)){
      end(true);
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
