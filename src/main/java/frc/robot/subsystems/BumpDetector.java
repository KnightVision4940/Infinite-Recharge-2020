/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class BumpDetector extends CommandBase {
  /**
   * Creates a new BumpDetector.
   */
  static double bumpThreshold = 2; // how fast is a "bump"?

  static int gyroPort = 0;
  static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  public BumpDetector() {

  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // double rate = gyro.getRate();
    if(gyro.getRate() > bumpThreshold) {
      // correct the bump
      
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
