/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class DriveForward extends CommandBase {
  double speed = 0;
  boolean turn;
  boolean drive;
  double turnSpeed;
  /**
   * Creates a new DriveForward.
   */
  public DriveForward(boolean turn ,boolean drive ,double turnVal, double speed) {
    this.speed = speed;
    this.turn = turn;
    this.drive = drive;
    this.turnSpeed = turnVal;

    addRequirements(Robot.drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(turn && drive){
    Robot.drive.drive(speed, turnSpeed);
    } else if(turn && drive == false){
      Robot.drive.drive(0.0, turnSpeed);
    }else if(drive && turn == false){
      Robot.drive.drive(speed, 0.0);
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
