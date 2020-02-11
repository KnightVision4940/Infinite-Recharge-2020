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

  double startEncoder;
  double curEncoder;

  int StopPos;

  /**
   * Creates a new DriveForward.
   */
  public DriveForward(double turnVal, double speed, int stop) {
    this.speed = speed;
    this.turnSpeed = turnVal;
    this.StopPos = stop;

    addRequirements(Robot.drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.drive.resetGyro();
    startEncoder = Robot.drive.getEncoderLB();
  }

  // Called once the command ends or is interrupted.
  // D@nte Was Here
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
