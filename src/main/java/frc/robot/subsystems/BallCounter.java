/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallCounter extends SubsystemBase {
  /**
   * Creates a new BallCounter.
   */
  public DigitalInput intakeLimit  = new DigitalInput(0); 
  public DigitalInput outtakeLimit = new DigitalInput(1); 
  public int balls = 0;
  public BallCounter() {
  }

  @Override
  public void periodic() {
    if(intakeLimit.get())  { balls++; } // ball in
    if(outtakeLimit.get()) { balls--; } // ball out
  }
}
