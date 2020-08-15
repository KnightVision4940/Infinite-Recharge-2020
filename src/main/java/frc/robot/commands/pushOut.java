/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
// RIP FRC 2020 

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class pushOut extends CommandBase {
  private static Timer wait = new Timer();
  
  /**
   * Creates a new pushOut.
   */
  public pushOut() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.IntakeDeployer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    wait.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(wait.get()==5.0){
    Robot.IntakeDeployer.push();
    }
  
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.IntakeDeployer.back();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
