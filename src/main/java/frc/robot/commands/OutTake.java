/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OutTake extends CommandBase {
  /**
   * Creates a new OutTake.
   */
  double speed;
  public OutTake(double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    spinMotor();
    SmartDashboard.putNumber("Speed of Mid:", -Robot.sub_outtake.getVelocity());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.sub_outtake.stop();
    Robot.sub_outtake.changeRunningFullToFalse();
    //change P I D values
    // Robot.sub_outtake.movePID(5676, 0, 0, 0, 0.000015);
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public void spinMotor() {
    Robot.sub_outtake.move(-speed,-1.0);
    //you are good at coding!!!!!!!!
  }
}
