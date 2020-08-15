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
  public static Solenoid s1;
  public static Solenoid s2;
  Relay spike;
  Relay spike2;
  /**
   * Creates a new Deployer.
   */
  public Deployer() {
    // s1 = new Solenoid(Constants.Solenoid);
    // s2 = new Solenoid(Constants.Solenoid);
    spike = new Relay(Constants.Spike[0]);
    spike2 = new Relay(Constants.Spike[1]);
  }

  public void push(){
    spike.set(Relay.Value.kOn);
    spike2.set(Relay.Value.kOn);
  }
  public void back(){
    spike.set(Relay.Value.kOff);
    spike2.set(Relay.Value.kOff);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
