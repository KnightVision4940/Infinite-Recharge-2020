/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
// RIP FRC 2020 

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Relay;

public class Deployer extends SubsystemBase {
  // public static Solenoid s1;
  // public static Solenoid s2;
  // Relay spike;
  /**
   * Creates a new Deployer.
   */
  public Deployer() {
    // s1 = new Solenoid(Constants.Solenoid);
    // s2 = new Solenoid(Constants.Solenoid);
    // spike = new Relay(102);
  }

  public void push(){
    // spike.set(Relay.Value.kOn);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
