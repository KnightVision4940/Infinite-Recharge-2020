/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class climb extends SubsystemBase {
  // static TalonSRX mainMotor;

  /**
   * Creates a new Climb.
   */
  public climb() {
    // mainMotor = new TalonSRX(); // motor port?

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void move(double direction) {
    // mainMotor.set(ControlMode.PercentOutput, direction);
  }
}
  
