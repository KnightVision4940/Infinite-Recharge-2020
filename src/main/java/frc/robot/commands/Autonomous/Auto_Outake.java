/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by harambe. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
// RIP FRC 2020 

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class Auto_Outake extends CommandBase {
  private int p_toTravel;
  private double currentEncoder = 0;
  private double startEncoder = 0;

  public Auto_Outake(int p_toTravel) {
    addRequirements(Robot.sub_outtake);
    //p to travel is how long the outake will run for
    this.p_toTravel = -p_toTravel;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startEncoder = Robot.sub_outtake.getPosition();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentEncoder = Robot.sub_outtake.getPosition();
    
    // Runs until desired encoder position is met
    if(p_toTravel <= currentEncoder - startEncoder){
      Robot.sub_outtake.move(-0.9,-1.0);
    }else{
      end(true);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.sub_outtake.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
